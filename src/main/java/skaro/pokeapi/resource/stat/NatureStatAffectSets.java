package skaro.pokeapi.resource.stat;

import java.util.List;

import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.nature.Nature;

public class NatureStatAffectSets {

	private List<NamedApiResource<Nature>> increase;
	private List<NamedApiResource<Nature>> decrease;
	
	public List<NamedApiResource<Nature>> getIncrease() {
		return increase;
	}
	public void setIncrease(List<NamedApiResource<Nature>> increase) {
		this.increase = increase;
	}
	public List<NamedApiResource<Nature>> getDecrease() {
		return decrease;
	}
	public void setDecrease(List<NamedApiResource<Nature>> decrease) {
		this.decrease = decrease;
	}
	
}
