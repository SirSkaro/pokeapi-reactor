package skaro.pokeapi.client;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;
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
import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.NamedApiResourceList;
import skaro.pokeapi.resource.PokeApiResource;
import skaro.pokeapi.resource.move.Move;
import skaro.pokeapi.resource.pokemon.Pokemon;

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
				.baseUrl(String.format("http://localhost:%s", mockPokeApiServer.getPort()))
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
