package skaro.pokeapi;

import java.util.List;
import java.util.function.Supplier;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.pokemon.Pokemon;

public interface PokeApiClient {
	
	Mono<Pokemon> getPokemon(String id);
	<T> Mono<T> followResource(Supplier<NamedApiResource<T>> resourceSupplier, Class<T> cls);
	<T> Flux<T> followResources(Supplier<List<NamedApiResource<T>>> resourcesSupplier, Class<T> cls);
	
}
