package skaro.pokeapi.client;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import skaro.pokeapi.cache.CacheFacade;
import skaro.pokeapi.cache.CacheSpec;
import skaro.pokeapi.query.PageQuery;
import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.NamedApiResourceList;
import skaro.pokeapi.resource.PokeApiResource;

public class ReactiveCachingPokeApiClient implements PokeApiClient {
	private PokeApiEntityFactory entityFactory;	
	private CacheFacade cacheFacade;

	public ReactiveCachingPokeApiClient(PokeApiEntityFactory entityFactory, CacheFacade cacheFacade) {
		this.entityFactory = entityFactory;
		this.cacheFacade = cacheFacade;
	}

	@Override
	public <T extends PokeApiResource> Mono<T> getResource(Class<T> cls, String idOrName) {
		CacheSpec<T> cacheSpec = CacheSpec.get(cls, idOrName)
				.orCache(() -> entityFactory.getResource(cls, idOrName));
		
		return cacheFacade.get(cacheSpec);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends PokeApiResource> Mono<NamedApiResourceList<T>> getResource(Class<T> cls) {
		Class<NamedApiResourceList<T>> collectionResourceCalss = (Class<NamedApiResourceList<T>>)(Class<?>)NamedApiResourceList.class;
		CacheSpec<NamedApiResourceList<T>> cacheSpec = CacheSpec.get(collectionResourceCalss, "collection")
				.orCache(() -> entityFactory.getBaseResource(cls));
		
		return cacheFacade.get(cacheSpec);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends PokeApiResource> Mono<NamedApiResourceList<T>> getResource(Class<T> cls, PageQuery query) {
		Class<NamedApiResourceList<T>> collectionResourceCalss = (Class<NamedApiResourceList<T>>)(Class<?>)NamedApiResourceList.class;
		String key = String.format("collection-offset%d-limit%d", query.getOffset(), query.getLimit());
		CacheSpec<NamedApiResourceList<T>> cacheSpec = CacheSpec.get(collectionResourceCalss, key)
				.orCache(() -> entityFactory.getBaseResource(cls, query));
		
		return cacheFacade.get(cacheSpec);
	}

	@Override
	public <T extends PokeApiResource> Mono<T> followResource(Supplier<NamedApiResource<T>> resourceSupplier, Class<T> cls) {
		return Mono.fromSupplier(resourceSupplier)
			.map(resource -> resourceToCacheSpec(resource, cls))
			.flatMap(cacheFacade::get);
	}

	@Override
	public <T extends PokeApiResource> Flux<T> followResources(Supplier<List<NamedApiResource<T>>> resourcesSupplier, Class<T> cls) {
		return Mono.fromSupplier(resourcesSupplier)
			.map(resources -> resourcesToCacheSpecs(resources, cls))
			.flatMapMany(cacheFacade::getMany);
	}
	
	private <T extends PokeApiResource> CacheSpec<T> resourceToCacheSpec(NamedApiResource<T> resource, Class<T> cls) {
		return CacheSpec.get(cls, resource.getName())
				.orCache(() -> entityFactory.getNamedResource(resource, cls));
	}
	
	private <T extends PokeApiResource> List<CacheSpec<T>> resourcesToCacheSpecs(List<NamedApiResource<T>> resources, Class<T> cls) {
		return resources.stream()
				.map(resource -> resourceToCacheSpec(resource, cls))
				.collect(Collectors.toList());
	}
	
}
