package skaro.pokeapi.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;
import reactor.test.StepVerifier;
import skaro.pokeapi.resource.pokemon.Pokemon;

@ExtendWith(SpringExtension.class)
public class ReactiveCachingPokeApiClientTest {

	@Mock
	private PokeApiEntityFactory entityFactory;	
	@Mock
	private CacheManager cacheManager;
	@Mock
	private Cache cache;
	
	private ReactiveCachingPokeApiClient pokeApiClient;
	
	@BeforeEach
	public void setup() {
		this.pokeApiClient = new ReactiveCachingPokeApiClient(entityFactory, cacheManager);
	}
	
	@Test
	public void getResourceByIdTest_notCached() {
		String resourceId = UUID.randomUUID().toString();
		Pokemon pokemon = new Pokemon();
		ArgumentCaptor<Signal<Pokemon>> cachedValueCaptor = ArgumentCaptor.forClass(Signal.class);
		
		Mockito.when(entityFactory.getResource(Pokemon.class, resourceId))
			.thenReturn( Mono.just(pokemon));
		Mockito.when(cacheManager.getCache(pokemon.getClass().getName()))
			.thenReturn(cache);
		Mockito.when(cache.get(resourceId))
			.thenReturn(new SimpleValueWrapper(null));
		
		StepVerifier.create(pokeApiClient.getResource(Pokemon.class, resourceId))
			.expectNext(pokemon)
			.expectComplete()
			.verify();
		
		Mockito.verify(cacheManager, Mockito.atLeast(2)).getCache(pokemon.getClass().getName());
		Mockito.verify(cache).get(resourceId);
		Mockito.verify(cache).put(ArgumentMatchers.eq(resourceId), cachedValueCaptor.capture());
		
		Pokemon cachedValue = cachedValueCaptor.getValue().get();
		assertEquals(pokemon, cachedValue);
	}
	
}
