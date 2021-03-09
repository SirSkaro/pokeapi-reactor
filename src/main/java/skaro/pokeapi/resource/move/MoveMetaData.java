package skaro.pokeapi.resource.move;

import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.moveailment.MoveAilment;
import skaro.pokeapi.resource.movecategory.MoveCategory;

public class MoveMetaData {

	private NamedApiResource<MoveAilment> ailment;
	private NamedApiResource<MoveCategory> category;
	private Integer minHits;
	private Integer maxHits;
	private Integer minTurns;
	private Integer maxTurns;
	private Integer drain;
	private Integer healing;
	private Integer critRate;
	private Integer ailmentChance;
	private Integer flinchChance;
	private Integer statChance;
	
	public NamedApiResource<MoveAilment> getAilment() {
		return ailment;
	}
	public void setAilment(NamedApiResource<MoveAilment> ailment) {
		this.ailment = ailment;
	}
	public NamedApiResource<MoveCategory> getCategory() {
		return category;
	}
	public void setCategory(NamedApiResource<MoveCategory> category) {
		this.category = category;
	}
	public Integer getMinHits() {
		return minHits;
	}
	public void setMinHits(Integer minHits) {
		this.minHits = minHits;
	}
	public Integer getMaxHits() {
		return maxHits;
	}
	public void setMaxHits(Integer maxHits) {
		this.maxHits = maxHits;
	}
	public Integer getMinTurns() {
		return minTurns;
	}
	public void setMinTurns(Integer minTurns) {
		this.minTurns = minTurns;
	}
	public Integer getMaxTurns() {
		return maxTurns;
	}
	public void setMaxTurns(Integer maxTurns) {
		this.maxTurns = maxTurns;
	}
	public Integer getDrain() {
		return drain;
	}
	public void setDrain(Integer drain) {
		this.drain = drain;
	}
	public Integer getHealing() {
		return healing;
	}
	public void setHealing(Integer healing) {
		this.healing = healing;
	}
	public Integer getCritRate() {
		return critRate;
	}
	public void setCritRate(Integer critRate) {
		this.critRate = critRate;
	}
	public Integer getAilmentChance() {
		return ailmentChance;
	}
	public void setAilmentChance(Integer ailmentChance) {
		this.ailmentChance = ailmentChance;
	}
	public Integer getFlinchChance() {
		return flinchChance;
	}
	public void setFlinchChance(Integer flinchChance) {
		this.flinchChance = flinchChance;
	}
	public Integer getStatChance() {
		return statChance;
	}
	public void setStatChance(Integer statChance) {
		this.statChance = statChance;
	}
	
}
