package skaro.pokeapi.resource.pokemon;

import java.util.List;

public class Pokemon {

	private String id;
	private String name;
	private Integer baseExperience;
	private Integer height;
	private Boolean isDefault;
	private Integer order;
	private Integer weight;
	private List<PokemonAbility> abilities;
//	private List<NamedApiResource<PokemonForm>> forms;
//	private List<VersionGameIndex> gameIndices;
//	private List<PokemonHeldItem> heldItems;
//	private String locationAreaEncounters;
//	private List<PokemonMove> moves;
//	private PokemonSprites sprites;
//	private NamedApiResource<PokemonSpecies> species;
//	private List<PokemonStat> stats;
//	private List<PokemonType> types;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	
}
