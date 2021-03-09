package skaro.pokeapi.resource;

import skaro.pokeapi.resource.machine.Machine;
import skaro.pokeapi.resource.versiongroup.VersionGroup;

public class MachineVersionDetail {

	private ApiResource<Machine> machine;
	private NamedApiResource<VersionGroup> versionGroup;
	
	public ApiResource<Machine> getMachine() {
		return machine;
	}
	public void setMachine(ApiResource<Machine> machine) {
		this.machine = machine;
	}
	public NamedApiResource<VersionGroup> getVersionGroup() {
		return versionGroup;
	}
	public void setVersionGroup(NamedApiResource<VersionGroup> versionGroup) {
		this.versionGroup = versionGroup;
	}
	
}
