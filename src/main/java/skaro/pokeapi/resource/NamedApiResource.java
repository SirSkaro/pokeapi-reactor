package skaro.pokeapi.resource;

import skaro.pokeapi.PokeApiResource;

public class NamedApiResource<T extends PokeApiResource> {

	private String name;
	private String url;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
