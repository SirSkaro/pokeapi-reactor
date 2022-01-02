package skaro.pokeapi.resource.location;

import java.util.List;

import skaro.pokeapi.resource.GenerationGameIndex;
import skaro.pokeapi.resource.Name;
import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.PokeApiResource;
import skaro.pokeapi.resource.locationarea.LocationArea;
import skaro.pokeapi.resource.region.Region;
import skaro.pokeapi.utils.locale.Localizable;

public class Location implements PokeApiResource, Localizable {

	private Integer id;
	private String name;
	private NamedApiResource<Region> region;
	private List<Name> names;
	private List<GenerationGameIndex> gameIndices;
	private List<NamedApiResource<LocationArea>> areas;
	
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
	public NamedApiResource<Region> getRegion() {
		return region;
	}
	public void setRegion(NamedApiResource<Region> region) {
		this.region = region;
	}
	public List<Name> getNames() {
		return names;
	}
	public void setNames(List<Name> names) {
		this.names = names;
	}
	public List<GenerationGameIndex> getGameIndices() {
		return gameIndices;
	}
	public void setGameIndices(List<GenerationGameIndex> gameIndices) {
		this.gameIndices = gameIndices;
	}
	public List<NamedApiResource<LocationArea>> getAreas() {
		return areas;
	}
	public void setAreas(List<NamedApiResource<LocationArea>> areas) {
		this.areas = areas;
	}
	
}
