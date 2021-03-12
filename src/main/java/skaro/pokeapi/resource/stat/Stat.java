package skaro.pokeapi.resource.stat;

import java.util.List;

import skaro.pokeapi.PokeApiResource;
import skaro.pokeapi.resource.ApiResource;
import skaro.pokeapi.resource.Name;
import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.characteristic.Characteristic;
import skaro.pokeapi.resource.movedamageclass.MoveDamageClass;

public class Stat implements PokeApiResource {

	private Integer id;
	private String name;
	private Integer gameIndex;
	private Boolean isBattleOnly;
	private MoveStatAffectSets affectingMoves;
	private NatureStatAffectSets affectingNatures;
	private List<ApiResource<Characteristic>> characteristics;
	private NamedApiResource<MoveDamageClass> moveDamageClass;
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
	public Integer getGameIndex() {
		return gameIndex;
	}
	public void setGameIndex(Integer gameIndex) {
		this.gameIndex = gameIndex;
	}
	public Boolean getIsBattleOnly() {
		return isBattleOnly;
	}
	public void setIsBattleOnly(Boolean isBattleOnly) {
		this.isBattleOnly = isBattleOnly;
	}
	public MoveStatAffectSets getAffectingMoves() {
		return affectingMoves;
	}
	public void setAffectingMoves(MoveStatAffectSets affectingMoves) {
		this.affectingMoves = affectingMoves;
	}
	public NatureStatAffectSets getAffectingNatures() {
		return affectingNatures;
	}
	public void setAffectingNatures(NatureStatAffectSets affectingNatures) {
		this.affectingNatures = affectingNatures;
	}
	public List<ApiResource<Characteristic>> getCharacteristics() {
		return characteristics;
	}
	public void setCharacteristics(List<ApiResource<Characteristic>> characteristics) {
		this.characteristics = characteristics;
	}
	public NamedApiResource<MoveDamageClass> getMoveDamageClass() {
		return moveDamageClass;
	}
	public void setMoveDamageClass(NamedApiResource<MoveDamageClass> moveDamageClass) {
		this.moveDamageClass = moveDamageClass;
	}
	public List<Name> getNames() {
		return names;
	}
	public void setNames(List<Name> names) {
		this.names = names;
	}
	
}
