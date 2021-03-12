package skaro.pokeapi.resource.generation;

import java.util.List;

import skaro.pokeapi.PokeApiResource;
import skaro.pokeapi.resource.Name;
import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.ability.Ability;
import skaro.pokeapi.resource.move.Move;
import skaro.pokeapi.resource.pokemonspecies.PokemonSpecies;
import skaro.pokeapi.resource.type.Type;
import skaro.pokeapi.resource.versiongroup.VersionGroup;

public class Generation implements PokeApiResource {

	private Integer id;
	private String name;
	private NamedApiResource<Ability> abilities;
	private List<Name> names;
	private List<NamedApiResource<Move>> moves;
	private List<NamedApiResource<PokemonSpecies>> pokemonSpecies;
	private List<NamedApiResource<Type>> types;
	private List<NamedApiResource<VersionGroup>> versionGroups;
	
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
	public NamedApiResource<Ability> getAbilities() {
		return abilities;
	}
	public void setAbilities(NamedApiResource<Ability> abilities) {
		this.abilities = abilities;
	}
	public List<Name> getNames() {
		return names;
	}
	public void setNames(List<Name> names) {
		this.names = names;
	}
	public List<NamedApiResource<Move>> getMoves() {
		return moves;
	}
	public void setMoves(List<NamedApiResource<Move>> moves) {
		this.moves = moves;
	}
	public List<NamedApiResource<PokemonSpecies>> getPokemonSpecies() {
		return pokemonSpecies;
	}
	public void setPokemonSpecies(List<NamedApiResource<PokemonSpecies>> pokemonSpecies) {
		this.pokemonSpecies = pokemonSpecies;
	}
	public List<NamedApiResource<Type>> getTypes() {
		return types;
	}
	public void setTypes(List<NamedApiResource<Type>> types) {
		this.types = types;
	}
	public List<NamedApiResource<VersionGroup>> getVersionGroups() {
		return versionGroups;
	}
	public void setVersionGroups(List<NamedApiResource<VersionGroup>> versionGroups) {
		this.versionGroups = versionGroups;
	}
	
}
