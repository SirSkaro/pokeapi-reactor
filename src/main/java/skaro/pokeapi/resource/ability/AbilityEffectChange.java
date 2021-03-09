package skaro.pokeapi.resource.ability;

import java.util.List;

import skaro.pokeapi.resource.Effect;
import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.versiongroup.VersionGroup;

public class AbilityEffectChange {

	private List<Effect> effectEntries;
	private NamedApiResource<VersionGroup> versionGroup;
	
	public List<Effect> getEffectEntries() {
		return effectEntries;
	}
	public void setEffectEntries(List<Effect> effectEntries) {
		this.effectEntries = effectEntries;
	}
	public NamedApiResource<VersionGroup> getVersionGroup() {
		return versionGroup;
	}
	public void setVersionGroup(NamedApiResource<VersionGroup> versionGroup) {
		this.versionGroup = versionGroup;
	}
	
}
