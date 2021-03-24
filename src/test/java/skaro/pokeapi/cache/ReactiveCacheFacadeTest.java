package skaro.pokeapi.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;
import reactor.test.StepVerifier;
import skaro.pokeapi.resource.pokemon.Pokemon;

@ExtendWith(SpringExtension.class)
public class ReactiveCacheFacadeTest {

	@Mock
	private CacheManager cacheManager;
	@Mock
	private Cache cache;
	
	private ReactiveCacheManagerCacheFacade facade;
	
	@BeforeEach
	public void setup() {
		this.facade = new ReactiveCacheManagerCacheFacade(cacheManager);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void getTest_cacheMiss() {
		String key = UUID.randomUUID().toString();
		Pokemon value = new Pokemon();
		ArgumentCaptor<Signal<Pokemon>> cachedValueCaptor = ArgumentCaptor.forClass(Signal.class);
		
		when(cacheManager.getCache(value.getClass().getName()))
			.thenReturn(cache);
		when(cache.get(key))
			.thenReturn(new SimpleValueWrapper(null));
		
		CacheSpec<Pokemon> cacheSpec = CacheSpec.of(Pokemon.class, key)
				.orCache(() -> Mono.just(value));
		
		StepVerifier.create(facade.get(cacheSpec))
			.expectNext(value)
			.expectComplete()
			.verify();
	
		verify(cache).put(eq(key), cachedValueCaptor.capture());
		
		Pokemon cachedValue = cachedValueCaptor.getValue().get();
		assertEquals(value, cachedValue);
	}
	
	@Test
	public void getTest_cacheHit() {
		String key = UUID.randomUUID().toString();
		Pokemon value = new Pokemon();

		when(cacheManager.getCache(value.getClass().getName()))
			.thenReturn(cache);
		when(cache.get(key))
			.thenReturn(new SimpleValueWrapper(Signal.next(value)));
		
		CacheSpec<Pokemon> cacheSpec = CacheSpec.of(Pokemon.class, key)
				.orCache(() -> Mono.empty());
		
		StepVerifier.create(facade.get(cacheSpec))
			.expectNext(value)
			.expectComplete()
			.verify();
		
		verify(cache, never()).put(any(), any());
	}
	
	@Test
	public void getTest_cacheDoesNotExist() {
		String key = UUID.randomUUID().toString();
		Pokemon value = new Pokemon();
		
		when(cacheManager.getCache(value.getClass().getName()))
			.thenReturn(null);
		
		CacheSpec<Pokemon> cacheSpec = CacheSpec.of(Pokemon.class, key)
				.orCache(() -> Mono.just(value));
		
		StepVerifier.create(facade.get(cacheSpec))
			.expectNext(value)
			.expectComplete()
			.verify();
	
		verify(cache, never()).put(any(), any());
	}
	
	
}
