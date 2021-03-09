package skaro.pokeapi.resource.move;

import java.util.List;

import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.VerboseEffect;
import skaro.pokeapi.resource.type.Type;
import skaro.pokeapi.resource.versiongroup.VersionGroup;

public class PastMoveStatValues {

	private Integer accuracy;
	private Integer effectChance;
	private Integer power;
	private Integer pp;
	private List<VerboseEffect> effectEntries;
	private NamedApiResource<Type> type;
	private NamedApiResource<VersionGroup> versionGroup;
	
	public Integer getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(Integer accuracy) {
		this.accuracy = accuracy;
	}
	public Integer getEffectChance() {
		return effectChance;
	}
	public void setEffectChance(Integer effectChance) {
		this.effectChance = effectChance;
	}
	public Integer getPower() {
		return power;
	}
	public void setPower(Integer power) {
		this.power = power;
	}
	public Integer getPp() {
		return pp;
	}
	public void setPp(Integer pp) {
		this.pp = pp;
	}
	public List<VerboseEffect> getEffectEntries() {
		return effectEntries;
	}
	public void setEffectEntries(List<VerboseEffect> effectEntries) {
		this.effectEntries = effectEntries;
	}
	public NamedApiResource<Type> getType() {
		return type;
	}
	public void setType(NamedApiResource<Type> type) {
		this.type = type;
	}
	public NamedApiResource<VersionGroup> getVersionGroup() {
		return versionGroup;
	}
	public void setVersionGroup(NamedApiResource<VersionGroup> versionGroup) {
		this.versionGroup = versionGroup;
	}
	
}
