package skaro.pokeapi;

import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import skaro.pokeapi.resource.NamedApiResource;

public interface PokeApiEntityFactory {

	<T> Mono<T> getResource(String uri, Class<T> resourceClass);
	<T> Mono<T> getNamedResource(NamedApiResource<T> resource, Class<T> cls);
	<T> Flux<T> getNamedResources(List<NamedApiResource<T>> resources, Class<T> cls);
	
}
