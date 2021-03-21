package skaro.pokeapi.client;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import reactor.test.StepVerifier;
import skaro.pokeapi.query.PageQuery;
import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.NamedApiResourceList;
import skaro.pokeapi.resource.PokeApiResource;
import skaro.pokeapi.resource.ability.Ability;
import skaro.pokeapi.resource.item.Item;
import skaro.pokeapi.resource.move.Move;
import skaro.pokeapi.resource.pokemon.Pokemon;
import skaro.pokeapi.resource.stat.Stat;

@ExtendWith(SpringExtension.class)
public class WebClientEntityFactoryTest {

	private static MockWebServer mockPokeApiServer;
	private PokeApiEndpointRegistry registry;
	private WebClient webClient;
	private ObjectMapper objectMapper;
	
	private WebClientEntityFactory factory;
	
	@BeforeAll
	public static void startMockPokeApiServer() throws IOException {
		mockPokeApiServer = new MockWebServer();
		mockPokeApiServer.start();
	}
	
	@AfterAll
    static void tearDown() throws IOException {
		mockPokeApiServer.shutdown();
    }
	
	@BeforeEach
	public void setup() {
		objectMapper = new ObjectMapper();
		registry = Mockito.mock(PokeApiEndpointRegistry.class);
		webClient = WebClient.builder()
				.baseUrl(getMockPokeApiServerBaseUrl())
				.build();
		
		factory = new WebClientEntityFactory(webClient, registry); 
	}
	
	@Test
	public void testGetResource() throws JsonProcessingException, InterruptedException {
		String resourceEndpoint = "/pokemon";
		Pokemon pokemon = new Pokemon();
		pokemon.setName("Mienfoobar");
		
		Mockito.when(registry.getEndpoint(Pokemon.class))
			.thenReturn(resourceEndpoint);
		mockPokeApiServer.enqueue(createMockResponseWithBody(pokemon));
		
		StepVerifier.create(factory.getResource(Pokemon.class, pokemon.getName()))
			.expectNextMatches(resource -> pokemon.getName().equals(resource.getName()))
			.expectComplete()
			.verify();
		
		RecordedRequest recordedRequest = mockPokeApiServer.takeRequest();
		
		String expectedEndpoint = String.format("%s/%s", resourceEndpoint, pokemon.getName());
		assertEquals(HttpMethod.GET.toString(), recordedRequest.getMethod());
		assertEquals(expectedEndpoint, recordedRequest.getPath());
	}
	
	@Test
	public void testGetBaseResource() throws JsonProcessingException, InterruptedException {
		String resourceEndpoint = "/move";
		String move1 = "Tackle";
		String move2 = "Splash";
		NamedApiResourceList<PokeApiResource> resourceListResponse = createResourceList(List.of(move1, move2));
		
		Mockito.when(registry.getEndpoint(Move.class))
			.thenReturn(resourceEndpoint);
		mockPokeApiServer.enqueue(createMockResponseWithBody(resourceListResponse));
		
		Consumer<NamedApiResourceList<Move>> assertListHasNames = resourceList -> {
			List<NamedApiResource<Move>> resources = resourceList.getResults();
			assertEquals(2, resources.size());
			
			Set<String> resourceNames = resources.stream()
					.map(NamedApiResource::getName)
					.collect(Collectors.toSet());
			assertTrue(resourceNames.contains(move1));
			assertTrue(resourceNames.contains(move2));
		};
		
		StepVerifier.create(factory.getBaseResource(Move.class))
			.assertNext(assertListHasNames)
			.expectComplete()
			.verify();
		
		RecordedRequest recordedRequest = mockPokeApiServer.takeRequest();
		assertEquals(HttpMethod.GET.toString(), recordedRequest.getMethod());
		assertEquals(resourceEndpoint, recordedRequest.getPath());
	}
	
	@Test
	public void testGetBaseResourceWithQuery() throws JsonProcessingException, InterruptedException {
		String resourceEndpoint = "ability";
		String ability1 = "Levitate";
		String ability2 = "Pressure";
		NamedApiResourceList<PokeApiResource> resourceListResponse = createResourceList(List.of(ability1, ability2));
		PageQuery query = new PageQuery(5, 10);
		
		Mockito.when(registry.getEndpoint(Ability.class))
			.thenReturn("/" + resourceEndpoint);
		mockPokeApiServer.enqueue(createMockResponseWithBody(resourceListResponse));
		
		Consumer<NamedApiResourceList<Ability>> assertListHasNames = resourceList -> {
			List<NamedApiResource<Ability>> resources = resourceList.getResults();
			assertEquals(2, resources.size());
			
			Set<String> resourceNames = resources.stream()
					.map(NamedApiResource::getName)
					.collect(Collectors.toSet());
			assertTrue(resourceNames.contains(ability1));
			assertTrue(resourceNames.contains(ability2));
		};
		
		StepVerifier.create(factory.getBaseResource(Ability.class, query))
			.assertNext(assertListHasNames)
			.expectComplete()
			.verify();
		
		RecordedRequest recordedRequest = mockPokeApiServer.takeRequest();
		assertEquals(HttpMethod.GET.toString(), recordedRequest.getMethod());
		assertEquals(resourceEndpoint, recordedRequest.getRequestUrl().pathSegments().get(0));
		assertEquals(2, recordedRequest.getRequestUrl().querySize());
		assertEquals(query.getLimit().toString(), recordedRequest.getRequestUrl().queryParameter("limit"));
		assertEquals(query.getOffset().toString(), recordedRequest.getRequestUrl().queryParameter("offset"));
	}
	
	@Test
	public void getNamedResourceTest() throws JsonProcessingException, InterruptedException {
		String namedResourceEndpoint = "item";
		Item item = new Item();
		item.setName("Leftovers");
		NamedApiResource<Item> namedResource = new NamedApiResource<>();
		namedResource.setUrl(String.format("%s/%s/%s", getMockPokeApiServerBaseUrl(), namedResourceEndpoint, item.getName()));
		
		mockPokeApiServer.enqueue(createMockResponseWithBody(item));
		
		StepVerifier.create(factory.getNamedResource(namedResource, Item.class))
			.expectNextMatches(resource -> item.getName().equals(resource.getName()))
			.expectComplete()
			.verify();
		
		String expectedEndpoint = String.format("/%s/%s", namedResourceEndpoint, item.getName());
		RecordedRequest recordedRequest = mockPokeApiServer.takeRequest();
		assertEquals(HttpMethod.GET.toString(), recordedRequest.getMethod());
		assertEquals(expectedEndpoint, recordedRequest.getPath());
	}
	
	@Test
	public void getNamedResourcesTest() throws JsonProcessingException, InterruptedException {
		String namedResourceEndpoint = "stat";
		Stat stat1 = new Stat();
		stat1.setName("attack");
		Stat stat2 = new Stat();
		stat2.setName("defense");
		
		NamedApiResource<Stat> namedResource1 = new NamedApiResource<>();
		namedResource1.setUrl(String.format("%s/%s/%s", getMockPokeApiServerBaseUrl(), namedResourceEndpoint, stat1.getName()));
		NamedApiResource<Stat> namedResource2 = new NamedApiResource<>();
		namedResource2.setUrl(String.format("%s/%s/%s", getMockPokeApiServerBaseUrl(), namedResourceEndpoint, stat2.getName()));
		
		mockPokeApiServer.enqueue(createMockResponseWithBody(stat1));
		mockPokeApiServer.enqueue(createMockResponseWithBody(stat2));
		
		StepVerifier.create(factory.getNamedResources(List.of(namedResource1, namedResource2), Stat.class))
			.expectNextMatches(resource -> stat1.getName().equals(resource.getName()))
			.expectNextMatches(resource -> stat2.getName().equals(resource.getName()))
			.expectComplete()
			.verify();
		
		String expectedEndpoint1 = String.format("/%s/%s", namedResourceEndpoint, stat1.getName());
		RecordedRequest recordedRequest1 = mockPokeApiServer.takeRequest();
		assertEquals(HttpMethod.GET.toString(), recordedRequest1.getMethod());
		assertEquals(expectedEndpoint1, recordedRequest1.getPath());
		
		String expectedEndpoint2 = String.format("/%s/%s", namedResourceEndpoint, stat2.getName());
		RecordedRequest recordedRequest2 = mockPokeApiServer.takeRequest();
		assertEquals(HttpMethod.GET.toString(), recordedRequest2.getMethod());
		assertEquals(expectedEndpoint2, recordedRequest2.getPath());
	}
	
	private String getMockPokeApiServerBaseUrl() {
		return String.format("http://localhost:%s", mockPokeApiServer.getPort());
	}
	
	private MockResponse createMockResponseWithBody(Object body) throws JsonProcessingException {
		return new MockResponse()
				.setBody(objectMapper.writeValueAsString(body))
				.addHeader("Content-Type", "application/json"); 
	}
	
	private NamedApiResourceList<PokeApiResource> createResourceList(List<String> names) {
		List<NamedApiResource<PokeApiResource>> resources = names.stream()
				.map(name -> {
					NamedApiResource<PokeApiResource> resource = new NamedApiResource<>();
					resource.setName(name);
					return resource;
				}).collect(Collectors.toList());
		
		
		NamedApiResourceList<PokeApiResource> moveResources = new NamedApiResourceList<>();
		moveResources.setResults(resources);
		
		return moveResources;
	}
	
}
