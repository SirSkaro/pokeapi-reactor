package skaro.pokeapi.client;

import skaro.pokeapi.resource.PokeApiResource;

public interface PokeApiEndpointRegistry {
	
	<T extends PokeApiResource> String getEndpoint(Class<T> resource);
	
}
