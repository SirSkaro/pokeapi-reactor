package skaro.pokeapi.resource.stat;

import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.move.Move;

public class MoveStatAffect {

	private Integer change;
	private NamedApiResource<Move> move;
	
	public Integer getChange() {
		return change;
	}
	public void setChange(Integer change) {
		this.change = change;
	}
	public NamedApiResource<Move> getMove() {
		return move;
	}
	public void setMove(NamedApiResource<Move> move) {
		this.move = move;
	}
	
}
