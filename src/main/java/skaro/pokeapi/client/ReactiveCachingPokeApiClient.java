package skaro.pokeapi.client;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;

import reactor.cache.CacheMono;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;
import skaro.pokeapi.query.PageQuery;
import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.NamedApiResourceList;
import skaro.pokeapi.resource.PokeApiResource;

public class ReactiveCachingPokeApiClient implements PokeApiClient {
	private Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private PokeApiEntityFactory entityFactory;	
	private CacheManager cacheManager;

	public ReactiveCachingPokeApiClient(PokeApiEntityFactory entityFactory, CacheManager cacheManager) {
		this.entityFactory = entityFactory;
		this.cacheManager = cacheManager;
	}

	@Override
	public <T extends PokeApiResource> Mono<T> getResource(Class<T> cls, String idOrName) {
		return getOrCache(cls, idOrName, () -> entityFactory.getResource(cls, idOrName));
	}

	@Override
	public <T extends PokeApiResource> Mono<NamedApiResourceList<T>> getResource(Class<T> cls) {
		return entityFactory.getBaseResource(cls);
	}

	@Override
	public <T extends PokeApiResource> Mono<NamedApiResourceList<T>> getResource(Class<T> cls, PageQuery query) {
		return entityFactory.getBaseResource(cls, query);
	}

	@Override
	public <T extends PokeApiResource> Mono<T> followResource(Supplier<NamedApiResource<T>> resourceSupplier, Class<T> cls) {
		 return Mono.fromSupplier(resourceSupplier)
				.flatMap(resource -> getOrCache(cls, resource.getName(), () -> entityFactory.getNamedResource(resource, cls)));
	}

	@Override
	public <T extends PokeApiResource> Flux<T> followResources(Supplier<List<NamedApiResource<T>>> resourcesSupplier, Class<T> cls) {
		return Mono.fromSupplier(resourcesSupplier)
				.map(resources -> resourcesToCacheMissMap(cls, resources))
				.flatMapMany(cacheInstructions -> getOrCacheMany(cls, cacheInstructions));
	}
	
	private <T extends PokeApiResource> Map<String, Supplier<Mono<T>>> resourcesToCacheMissMap(Class<T> cls, List<NamedApiResource<T>> resources) {
		return resources.stream()
				.collect(Collectors.toMap(NamedApiResource::getName, resource -> () -> entityFactory.getNamedResource(resource, cls)));
	}
	
	private <T extends PokeApiResource> Mono<T> getOrCache(Class<T> cls, String resourceName, Supplier<Mono<T>> onCacheMiss) {
		return CacheMono.lookup(key -> checkCache(cls, key), resourceName) 
				.onCacheMissResume(() -> onCacheMiss.get())
				.andWriteWith((key, value) -> writeToCache(cls, key, value));
	}

	private <T extends PokeApiResource> Flux<T> getOrCacheMany(Class<T> cls, Map<String, Supplier<Mono<T>>> resources) {
		List<Mono<T>> resourceMonos = resources.entrySet().stream()
				.map(entry -> getOrCache(cls, entry.getKey(), entry.getValue()))
				.collect(Collectors.toList());
		
		return Flux.merge(resourceMonos);
	}
	
	private <T extends PokeApiResource> Mono<Signal<? extends T>> checkCache(Class<T> cls, String key) {
		String cacheName = getCacheNameForClassResource(cls);
		Optional<Signal<? extends T>> resourceFromCache = Optional.of(cacheManager.getCache(cacheName))
				.map(cache -> cache.get(key))
				.map(ValueWrapper::get)
				.map(Signal.class::cast);

		return Mono.<Signal<? extends T>>justOrEmpty(resourceFromCache);
	}
	
	private <T extends PokeApiResource> Mono<Void> writeToCache(Class<T> cls, String key, Signal<? extends T> value) {
		String cacheName = getCacheNameForClassResource(cls);
		Consumer<Cache> writeToCache = cache -> cache.put(key, value);
		Runnable logCacheFailure = () -> LOG.warn("Cache '{}' does not exist. Could not cache PokeApi resource. Please ensure cache '{}' exists or allow lazy creation of caches.", 
				cacheName,
				cacheName);

		return Mono.fromRunnable(() -> {
			Optional.ofNullable(cacheManager.getCache(cacheName))
				.ifPresentOrElse(writeToCache, logCacheFailure);
		});
	}

	private <T extends PokeApiResource> String getCacheNameForClassResource(Class<T> resourceClass) {
		return resourceClass.getName();
	}

}
