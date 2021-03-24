package skaro.pokeapi.cache;

import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import skaro.pokeapi.resource.PokeApiResource;

public interface CacheFacade {

	<T extends PokeApiResource> Mono<T> get(CacheSpec<T> cacheSpec);
	<T extends PokeApiResource> Flux<T> getMany(List<CacheSpec<T>> cacheSpecs);
	
}
