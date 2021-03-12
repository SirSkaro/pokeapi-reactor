package skaro.pokeapi.resource.pokemon;

import java.util.List;

import skaro.pokeapi.PokeApiResource;
import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.VersionGameIndex;
import skaro.pokeapi.resource.pokemonform.PokemonForm;
import skaro.pokeapi.resource.pokemonspecies.PokemonSpecies;

public class Pokemon implements PokeApiResource {

	private Integer id;
	private String name;
	private Integer baseExperience;
	private Integer height;
	private Boolean isDefault;
	private Integer order;
	private Integer weight;
	private List<PokemonAbility> abilities;
	private List<NamedApiResource<PokemonForm>> forms;
	private List<VersionGameIndex> gameIndices;
	private List<PokemonHeldItem> heldItems;
	private String locationAreaEncounters;
	private List<PokemonMove> moves;
	private PokemonSprites sprites;
	private NamedApiResource<PokemonSpecies> species;
	private List<PokemonStat> stats;
	private List<PokemonType> types;
	
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
	public Integer getBaseExperience() {
		return baseExperience;
	}
	public void setBaseExperience(Integer baseExperience) {
		this.baseExperience = baseExperience;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Boolean getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public List<PokemonAbility> getAbilities() {
		return abilities;
	}
	public void setAbilities(List<PokemonAbility> abilities) {
		this.abilities = abilities;
	}
	public List<NamedApiResource<PokemonForm>> getForms() {
		return forms;
	}
	public void setForms(List<NamedApiResource<PokemonForm>> forms) {
		this.forms = forms;
	}
	public List<VersionGameIndex> getGameIndices() {
		return gameIndices;
	}
	public void setGameIndices(List<VersionGameIndex> gameIndices) {
		this.gameIndices = gameIndices;
	}
	public List<PokemonHeldItem> getHeldItems() {
		return heldItems;
	}
	public void setHeldItems(List<PokemonHeldItem> heldItems) {
		this.heldItems = heldItems;
	}
	public String getLocationAreaEncounters() {
		return locationAreaEncounters;
	}
	public void setLocationAreaEncounters(String locationAreaEncounters) {
		this.locationAreaEncounters = locationAreaEncounters;
	}
	public List<PokemonMove> getMoves() {
		return moves;
	}
	public void setMoves(List<PokemonMove> moves) {
		this.moves = moves;
	}
	public PokemonSprites getSprites() {
		return sprites;
	}
	public void setSprites(PokemonSprites sprites) {
		this.sprites = sprites;
	}
	public NamedApiResource<PokemonSpecies> getSpecies() {
		return species;
	}
	public void setSpecies(NamedApiResource<PokemonSpecies> species) {
		this.species = species;
	}
	public List<PokemonStat> getStats() {
		return stats;
	}
	public void setStats(List<PokemonStat> stats) {
		this.stats = stats;
	}
	public List<PokemonType> getTypes() {
		return types;
	}
	public void setTypes(List<PokemonType> types) {
		this.types = types;
	}
	
}
