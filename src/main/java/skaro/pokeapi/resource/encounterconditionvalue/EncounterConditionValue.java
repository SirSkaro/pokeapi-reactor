package skaro.pokeapi.resource.encounterconditionvalue;

import java.util.List;

import skaro.pokeapi.resource.Name;
import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.PokeApiResource;
import skaro.pokeapi.resource.encountercondition.EncounterCondition;

public class EncounterConditionValue implements PokeApiResource {

	private Integer id;
	private String name;
	private NamedApiResource<EncounterCondition> condition;
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
	public NamedApiResource<EncounterCondition> getCondition() {
		return condition;
	}
	public void setCondition(NamedApiResource<EncounterCondition> condition) {
		this.condition = condition;
	}
	public List<Name> getNames() {
		return names;
	}
	public void setNames(List<Name> names) {
		this.names = names;
	}
	
}
