package skaro.pokeapi;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import skaro.pokeapi.client.PokeApiClient;
import skaro.pokeapi.client.PokeApiEntityFactory;
import skaro.pokeapi.client.ReactiveCachingPokeApiClient;

@Configuration
@Import(PokeApiReactorBaseConfiguration.class)
public class PokeApiReactorCachingConfiguration {

	@Bean
	public PokeApiClient pokeApiClient(PokeApiEntityFactory entityFactory, CacheManager cacheManager) {
		return new ReactiveCachingPokeApiClient(entityFactory, cacheManager);
	}
	
}
