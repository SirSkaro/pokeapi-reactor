package skaro.pokeapi.client;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import skaro.pokeapi.query.PageQuery;
import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.NamedApiResourceList;
import skaro.pokeapi.resource.pokemon.Pokemon;
import skaro.pokeapi.resource.pokemonform.PokemonForm;
import skaro.pokeapi.resource.pokemonspecies.PokemonSpecies;

@ExtendWith(SpringExtension.class)
public class ReactiveNonCachingPokeApiClientTest {

	@Mock
	private PokeApiEntityFactory entityFactory;
	private ReactiveNonCachingPokeApiClient pokeApiClient;
	
	@BeforeEach
	public void setup() {
		pokeApiClient = new ReactiveNonCachingPokeApiClient(entityFactory);
	}
	
	@Test
	public void testGetResource() {
		NamedApiResourceList<Pokemon> resourceList = new NamedApiResourceList<>();

		Mockito.when(entityFactory.getBaseResource(Pokemon.class))
			.thenReturn(Mono.just(resourceList));
		
		StepVerifier.create(pokeApiClient.getResource(Pokemon.class))
			.expectNext(resourceList)
			.expectComplete()
			.verify();
	}
	
	@Test
	public void testGetResourceById() {
		String resourceId = UUID.randomUUID().toString();
		Pokemon pokemon = new Pokemon();
		
		Mockito.when(entityFactory.getResource(Pokemon.class, resourceId))
			.thenReturn(Mono.just(pokemon));
		
		StepVerifier.create(pokeApiClient.getResource(Pokemon.class, resourceId))
			.expectNext(pokemon)
			.expectComplete()
			.verify();
	}
	
	@Test
	public void testGetResourceWithQuery() {
		NamedApiResourceList<Pokemon> resourceList = new NamedApiResourceList<>();
		PageQuery query = new PageQuery(1, 1);

		Mockito.when(entityFactory.getBaseResource(Pokemon.class, query))
			.thenReturn(Mono.just(resourceList));
		
		StepVerifier.create(pokeApiClient.getResource(Pokemon.class, query))
			.expectNext(resourceList)
			.expectComplete()
			.verify();
	}
	
	@Test
	public void testFollowResource() {
		NamedApiResource<PokemonSpecies> speciesResource = new NamedApiResource<>(); 
		PokemonSpecies species = new PokemonSpecies();
		Pokemon pokemon = new Pokemon();
		pokemon.setSpecies(speciesResource);
		
		Mockito.when(entityFactory.getNamedResource(speciesResource, PokemonSpecies.class))
			.thenReturn(Mono.just(species));
		
		StepVerifier.create(pokeApiClient.followResource(pokemon::getSpecies, PokemonSpecies.class))
			.expectNext(species)
			.expectComplete()
			.verify();
	}
	
	@Test
	public void testFollowResources() {
		NamedApiResource<PokemonForm> formResource1 = new NamedApiResource<>(); 
		NamedApiResource<PokemonForm> formResource2 = new NamedApiResource<>();
		List<NamedApiResource<PokemonForm>> resourcesList = List.of(formResource1, formResource2);
		PokemonForm form1 = new PokemonForm();
		PokemonForm form2 = new PokemonForm();
		Pokemon pokemon = new Pokemon();
		pokemon.setForms(resourcesList);
		
		Mockito.when(entityFactory.getNamedResources(resourcesList, PokemonForm.class))
			.thenReturn(Flux.fromIterable(List.of(form1, form2)));
		
		StepVerifier.create(pokeApiClient.followResources(pokemon::getForms, PokemonForm.class))
			.expectNext(form1)
			.expectNext(form2)
			.expectComplete()
			.verify();
	}
	
	
	
}
