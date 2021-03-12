package skaro.pokeapi.resource.berryfirmness;

import java.util.List;

import skaro.pokeapi.PokeApiResource;
import skaro.pokeapi.resource.Name;
import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.berry.Berry;

public class BerryFirmness implements PokeApiResource {

	private Integer id;
	private String name;
	private List<NamedApiResource<Berry>> berries;
	private List<Name> names;
	
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
	public List<NamedApiResource<Berry>> getBerries() {
		return berries;
	}
	public void setBerries(List<NamedApiResource<Berry>> berries) {
		this.berries = berries;
	}
	public List<Name> getNames() {
		return names;
	}
	public void setNames(List<Name> names) {
		this.names = names;
	}
	
}
