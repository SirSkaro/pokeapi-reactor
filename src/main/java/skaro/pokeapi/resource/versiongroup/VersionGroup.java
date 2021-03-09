package skaro.pokeapi.resource.versiongroup;

import java.util.List;

import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.generation.Generation;
import skaro.pokeapi.resource.movelearnmethod.MoveLearnMethod;
import skaro.pokeapi.resource.pokedex.Pokedex;
import skaro.pokeapi.resource.region.Region;
import skaro.pokeapi.resource.version.Version;

public class VersionGroup {

	private String id;
	private String name;
	private Integer order;
	private NamedApiResource<Generation> generation;
	private List<NamedApiResource<MoveLearnMethod>> moveLearnMethods;
	private List<NamedApiResource<Pokedex>> pokedexes;
	private List<NamedApiResource<Region>> regions;
	private List<NamedApiResource<Version>> versions;
	
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
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public NamedApiResource<Generation> getGeneration() {
		return generation;
	}
	public void setGeneration(NamedApiResource<Generation> generation) {
		this.generation = generation;
	}
	public List<NamedApiResource<MoveLearnMethod>> getMoveLearnMethods() {
		return moveLearnMethods;
	}
	public void setMoveLearnMethods(List<NamedApiResource<MoveLearnMethod>> moveLearnMethods) {
		this.moveLearnMethods = moveLearnMethods;
	}
	public List<NamedApiResource<Pokedex>> getPokedexes() {
		return pokedexes;
	}
	public void setPokedexes(List<NamedApiResource<Pokedex>> pokedexes) {
		this.pokedexes = pokedexes;
	}
	public List<NamedApiResource<Region>> getRegions() {
		return regions;
	}
	public void setRegions(List<NamedApiResource<Region>> regions) {
		this.regions = regions;
	}
	public List<NamedApiResource<Version>> getVersions() {
		return versions;
	}
	public void setVersions(List<NamedApiResource<Version>> versions) {
		this.versions = versions;
	}
	
}
