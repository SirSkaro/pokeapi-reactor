package skaro.pokeapi.resource.move;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContestComboSets {

	private ContestComboDetail normal;
	private ContestComboDetail superb;
	
	public ContestComboDetail getNormal() {
		return normal;
	}
	public void setNormal(ContestComboDetail normal) {
		this.normal = normal;
	}
	@JsonProperty("super")
	public ContestComboDetail getSuperb() {
		return superb;
	}
	@JsonProperty("super")
	public void setSuperb(ContestComboDetail superb) {
		this.superb = superb;
	}
	
}
