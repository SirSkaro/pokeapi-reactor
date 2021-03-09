package skaro.pokeapi.resource.pokemon;

import java.util.List;

import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.move.Move;

public class PokemonMove {

	private NamedApiResource<Move> moves;
	private List<PokemonMoveVersion> versionGroupDetails;
	
	public NamedApiResource<Move> getMoves() {
		return moves;
	}
	public void setMoves(NamedApiResource<Move> moves) {
		this.moves = moves;
	}
	public List<PokemonMoveVersion> getVersionGroupDetails() {
		return versionGroupDetails;
	}
	public void setVersionGroupDetails(List<PokemonMoveVersion> versionGroupDetails) {
		this.versionGroupDetails = versionGroupDetails;
	}
	
}
