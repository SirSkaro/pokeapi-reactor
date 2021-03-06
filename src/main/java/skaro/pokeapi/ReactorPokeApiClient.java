package skaro.pokeapi;

import reactor.core.publisher.Mono;
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
	
}
