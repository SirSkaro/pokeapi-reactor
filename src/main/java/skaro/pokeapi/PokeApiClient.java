package skaro.pokeapi;

import reactor.core.publisher.Mono;
import skaro.pokeapi.resource.pokemon.Pokemon;

public interface PokeApiClient {
	
	Mono<Pokemon> getPokemon(String id);
	
}
