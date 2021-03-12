package skaro.pokeapi;

public interface PokeApiEndpointRegistry {
	
	<T extends PokeApiResource> String getEndpoint(Class<T> resource);
	
}
