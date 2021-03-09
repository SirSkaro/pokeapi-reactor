package skaro.pokeapi.resource.stat;

import java.util.List;

public class MoveStatAffectSets {

	private List<MoveStatAffect> increase;
	private List<MoveStatAffect> decrease;
	
	public List<MoveStatAffect> getIncrease() {
		return increase;
	}
	public void setIncrease(List<MoveStatAffect> increase) {
		this.increase = increase;
	}
	public List<MoveStatAffect> getDecrease() {
		return decrease;
	}
	public void setDecrease(List<MoveStatAffect> decrease) {
		this.decrease = decrease;
	}
	
}
