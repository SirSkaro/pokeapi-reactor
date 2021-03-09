package skaro.pokeapi.resource.movedamageclass;

import java.util.List;

import skaro.pokeapi.resource.Description;
import skaro.pokeapi.resource.Name;
import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.move.Move;

public class MoveDamageClass {

	private Integer id;
	private String name;
	private List<Description> descriptions;
	private List<NamedApiResource<Move>> moves;
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
	public List<Description> getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(List<Description> descriptions) {
		this.descriptions = descriptions;
	}
	public List<NamedApiResource<Move>> getMoves() {
		return moves;
	}
	public void setMoves(List<NamedApiResource<Move>> moves) {
		this.moves = moves;
	}
	public List<Name> getNames() {
		return names;
	}
	public void setNames(List<Name> names) {
		this.names = names;
	}
	
}
