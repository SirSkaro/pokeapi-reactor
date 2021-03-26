package skaro.pokeapi.cache;

import java.lang.invoke.MethodHandles;
import java.util.List;
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
import skaro.pokeapi.resource.PokeApiResource;

public class ReactiveCacheManagerCacheFacade implements CacheFacade {
	
	private Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	private CacheManager cacheManager;
	
	public ReactiveCacheManagerCacheFacade(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public <T extends PokeApiResource> Mono<T> get(CacheSpec<T> cacheSpec) {
		return getOrCache(cacheSpec.getType(), cacheSpec.getKey(), cacheSpec.getMonoSupplier());
	}

	@Override
	public <T extends PokeApiResource> Flux<T> getMany(List<CacheSpec<T>> cacheSpecs) {
		List<Mono<T>> resourceMonos = cacheSpecs.stream()
				.map(this::get)
				.collect(Collectors.toList());
		
		return Flux.merge(resourceMonos);
	}
	
	private <T extends PokeApiResource> Mono<T> getOrCache(Class<T> cls, String resourceName, Supplier<Mono<T>> onCacheMiss) {
		return CacheMono.lookup(key -> checkCache(cls, key), resourceName) 
				.onCacheMissResume(onCacheMiss)
				.andWriteWith((key, value) -> writeToCache(cls, key, value));
	}
	
	private <T extends PokeApiResource> Mono<Signal<? extends T>> checkCache(Class<T> cls, String key) {
		String cacheName = getCacheNameForClassResource(cls);
		Optional<Signal<? extends T>> resourceFromCache = Optional.ofNullable(cacheManager.getCache(cacheName))
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
