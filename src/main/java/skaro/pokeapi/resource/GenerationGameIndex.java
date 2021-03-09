package skaro.pokeapi.resource;

import skaro.pokeapi.resource.generation.Generation;

public class GenerationGameIndex {

	private Integer gameIndex;
	private NamedApiResource<Generation> generation;
	
	public Integer getGameIndex() {
		return gameIndex;
	}
	public void setGameIndex(Integer gameIndex) {
		this.gameIndex = gameIndex;
	}
	public NamedApiResource<Generation> getGeneration() {
		return generation;
	}
	public void setGeneration(NamedApiResource<Generation> generation) {
		this.generation = generation;
	}
	
}
