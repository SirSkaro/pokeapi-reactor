package skaro.pokeapi.resource.pokedex;

import java.util.List;

import skaro.pokeapi.resource.Description;
import skaro.pokeapi.resource.Name;
import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.region.Region;
import skaro.pokeapi.resource.versiongroup.VersionGroup;

public class Pokedex {

	private Integer id;
	private String name;
	private Boolean isMainSeries;
	private List<Description> descriptions;
	private List<Name> names;
	private List<PokemonEntry> pokemonEntries;
	private NamedApiResource<Region> region;
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
	public Boolean getIsMainSeries() {
		return isMainSeries;
	}
	public void setIsMainSeries(Boolean isMainSeries) {
		this.isMainSeries = isMainSeries;
	}
	public List<Description> getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(List<Description> descriptions) {
		this.descriptions = descriptions;
	}
	public List<Name> getNames() {
		return names;
	}
	public void setNames(List<Name> names) {
		this.names = names;
	}
	public List<PokemonEntry> getPokemonEntries() {
		return pokemonEntries;
	}
	public void setPokemonEntries(List<PokemonEntry> pokemonEntries) {
		this.pokemonEntries = pokemonEntries;
	}
	public NamedApiResource<Region> getRegion() {
		return region;
	}
	public void setRegion(NamedApiResource<Region> region) {
		this.region = region;
	}
	public List<NamedApiResource<VersionGroup>> getVersionGroups() {
		return versionGroups;
	}
	public void setVersionGroups(List<NamedApiResource<VersionGroup>> versionGroups) {
		this.versionGroups = versionGroups;
	}
	
}
