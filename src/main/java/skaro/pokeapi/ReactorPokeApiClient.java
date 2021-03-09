package skaro.pokeapi;

import java.util.List;
import java.util.function.Supplier;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.pokemon.Pokemon;

public class ReactorPokeApiClient implements PokeApiClient {

	private PokeApiEntityFactory entityFactory;	
	
	public ReactorPokeApiClient(PokeApiEntityFactory entityFactory) {
		this.entityFactory = entityFactory;
	}
	
	public Mono<Pokemon> getPokemon(String id) {
		String endpoint = "/pokemon/" + id;
		return entityFactory.getResource(endpoint, Pokemon.class);
	}
	
	public <T> Mono<T> followResource(Supplier<NamedApiResource<T>> resourceSupplier, Class<T> cls) {
		return Mono.fromSupplier(resourceSupplier)
				.flatMap(resource -> entityFactory.getNamedResource(resource, cls));
	}

	@Override
	public <T> Flux<T> followResources(Supplier<List<NamedApiResource<T>>> resourcesSupplier, Class<T> cls) {
		return Mono.fromSupplier(resourcesSupplier)
				.flatMapMany(resources -> entityFactory.getNamedResources(resources, cls));
	}
	
}
