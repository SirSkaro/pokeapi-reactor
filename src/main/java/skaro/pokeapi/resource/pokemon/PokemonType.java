package skaro.pokeapi.resource.pokemon;

import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.type.Type;

public class PokemonType {

	private Integer slot;
	private NamedApiResource<Type> type;
	
	public Integer getSlot() {
		return slot;
	}
	public void setSlot(Integer slot) {
		this.slot = slot;
	}
	public NamedApiResource<Type> getType() {
		return type;
	}
	public void setType(NamedApiResource<Type> type) {
		this.type = type;
	}
	
}
