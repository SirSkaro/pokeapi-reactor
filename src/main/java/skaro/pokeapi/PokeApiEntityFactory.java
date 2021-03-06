package skaro.pokeapi;

import reactor.core.publisher.Mono;

public interface PokeApiEntityFactory {

	<T> Mono<T> getResource(String uri, Class<T> resourceClass);
	
}
