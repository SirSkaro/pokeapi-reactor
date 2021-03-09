package skaro.pokeapi.resource;

import skaro.pokeapi.resource.version.Version;

public class VersionGameIndex {

	private Integer gameIndex;
	private NamedApiResource<Version> version;
	
	public Integer getGameIndex() {
		return gameIndex;
	}
	public void setGameIndex(Integer gameIndex) {
		this.gameIndex = gameIndex;
	}
	public NamedApiResource<Version> getVersion() {
		return version;
	}
	public void setVersion(NamedApiResource<Version> version) {
		this.version = version;
	}
	
}
