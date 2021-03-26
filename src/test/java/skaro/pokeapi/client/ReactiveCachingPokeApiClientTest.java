package skaro.pokeapi.client;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import skaro.pokeapi.cache.CacheFacade;
import skaro.pokeapi.cache.CacheSpec;
import skaro.pokeapi.query.PageQuery;
import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.NamedApiResourceList;
import skaro.pokeapi.resource.PokeApiResource;
import skaro.pokeapi.resource.pokemon.Pokemon;

@ExtendWith(SpringExtension.class)
public class ReactiveCachingPokeApiClientTest {

	@Mock
	private PokeApiEntityFactory entityFactory;	
	@Mock
	private CacheFacade cacheFacade;
	@Captor
	private ArgumentCaptor<CacheSpec<PokeApiResource>> cacheSpecCaptor;
	
	private ReactiveCachingPokeApiClient pokeApiClient;
	
	@BeforeEach
	public void setup() {
		this.pokeApiClient = new ReactiveCachingPokeApiClient(entityFactory, cacheFacade);
	}
	
	@Test
	public void getResourceByIdTest() {
		String resourceId = UUID.randomUUID().toString();
		Pokemon resource = Mockito.mock(Pokemon.class);
		
		Mockito.when(cacheFacade.get(cacheSpecCaptor.capture()))
			.thenReturn(Mono.just(resource));
		Mockito.when(entityFactory.getResource(Pokemon.class, resourceId))
			.thenReturn(Mono.just(resource));
		
		StepVerifier.create(pokeApiClient.getResource(Pokemon.class, resourceId))
			.expectNext(resource)
			.expectComplete()
			.verify();
		
		CacheSpec<PokeApiResource> usedCacheSpec = cacheSpecCaptor.getValue();
		StepVerifier.create(usedCacheSpec.getMonoSupplier().get())
			.expectNext(resource)
			.expectComplete()
			.verify();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getResourceTest() {
		NamedApiResourceList<Pokemon> resource = Mockito.mock(NamedApiResourceList.class);
		
		Mockito.when(cacheFacade.get(cacheSpecCaptor.capture()))
			.thenReturn(Mono.just(resource));
		Mockito.when(entityFactory.getBaseResource(Pokemon.class))
			.thenReturn(Mono.just(resource));
		
		StepVerifier.create(pokeApiClient.getResource(Pokemon.class))
			.expectNext(resource)
			.expectComplete()
			.verify();
		
		CacheSpec<PokeApiResource> usedCacheSpec = cacheSpecCaptor.getValue();
		StepVerifier.create(usedCacheSpec.getMonoSupplier().get())
			.expectNext(resource)
			.expectComplete()
			.verify();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getResourceTest_withPageQuery() {
		NamedApiResourceList<Pokemon> resource = Mockito.mock(NamedApiResourceList.class);
		PageQuery query = new PageQuery(1, 20);
		
		Mockito.when(cacheFacade.get(cacheSpecCaptor.capture()))
			.thenReturn(Mono.just(resource));
		Mockito.when(entityFactory.getBaseResource(Pokemon.class, query))
			.thenReturn(Mono.just(resource));
		
		StepVerifier.create(pokeApiClient.getResource(Pokemon.class, query))
			.expectNext(resource)
			.expectComplete()
			.verify();
		
		CacheSpec<PokeApiResource> usedCacheSpec = cacheSpecCaptor.getValue();
		StepVerifier.create(usedCacheSpec.getMonoSupplier().get())
			.expectNext(resource)
			.expectComplete()
			.verify();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void followResourceTest() {
		Pokemon resource = Mockito.mock(Pokemon.class);
		NamedApiResource<Pokemon> namedResource = Mockito.mock(NamedApiResource.class);
		
		Mockito.when(cacheFacade.get(cacheSpecCaptor.capture()))
			.thenReturn(Mono.just(resource));
		Mockito.when(entityFactory.getNamedResource(namedResource, Pokemon.class))
			.thenReturn(Mono.just(resource));
		
		StepVerifier.create(pokeApiClient.followResource(() -> namedResource, Pokemon.class))
			.expectNext(resource)
			.expectComplete()
			.verify();
		
		CacheSpec<PokeApiResource> usedCacheSpec = cacheSpecCaptor.getValue();
		StepVerifier.create(usedCacheSpec.getMonoSupplier().get())
			.expectNext(resource)
			.expectComplete()
			.verify();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void followResourcesTest() {
		Pokemon resource = Mockito.mock(Pokemon.class);
		NamedApiResource<Pokemon> namedResource = Mockito.mock(NamedApiResource.class);
		List<NamedApiResource<Pokemon>> namedResources = List.of(namedResource, namedResource);
		ArgumentCaptor<List<CacheSpec<Pokemon>>> cacheSpecListCaptor = ArgumentCaptor.forClass(List.class);
		
		Mockito.when(cacheFacade.getMany(cacheSpecListCaptor.capture()))
			.thenReturn(Flux.just(resource, resource));
		Mockito.when(entityFactory.getNamedResource(namedResource, Pokemon.class))
			.thenReturn(Mono.just(resource));
		
		StepVerifier.create(pokeApiClient.followResources(() -> namedResources, Pokemon.class))
			.expectNext(resource)
			.expectNext(resource)
			.expectComplete()
			.verify();
		
		List<CacheSpec<Pokemon>> usedCacheSpecs = cacheSpecListCaptor.getValue();
		List<Mono<Pokemon>> resourceMonos = usedCacheSpecs.stream()
				.map(CacheSpec::getMonoSupplier)
				.map(Supplier::get)
				.collect(Collectors.toList());
		
		StepVerifier.create(Flux.merge(resourceMonos))
			.expectNext(resource)
			.expectNext(resource)
			.expectComplete()
			.verify();
	}
	
}
