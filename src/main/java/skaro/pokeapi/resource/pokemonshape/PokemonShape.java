package skaro.pokeapi.resource.pokemonshape;

import java.util.List;

import skaro.pokeapi.resource.Name;
import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.PokeApiResource;
import skaro.pokeapi.resource.pokemonspecies.PokemonSpecies;

public class PokemonShape implements PokeApiResource {

	private Integer id;
	private String name;
	private List<AwesomeName> awesomeNames;
	private List<Name> names;
	private List<NamedApiResource<PokemonSpecies>> pokemonSpecies;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<AwesomeName> getAwesomeNames() {
		return awesomeNames;
	}
	public void setAwesomeNames(List<AwesomeName> awesomeNames) {
		this.awesomeNames = awesomeNames;
	}
	public List<Name> getNames() {
		return names;
	}
	public void setNames(List<Name> names) {
		this.names = names;
	}
	public List<NamedApiResource<PokemonSpecies>> getPokemonSpecies() {
		return pokemonSpecies;
	}
	public void setPokemonSpecies(List<NamedApiResource<PokemonSpecies>> pokemonSpecies) {
		this.pokemonSpecies = pokemonSpecies;
	}
	
}
