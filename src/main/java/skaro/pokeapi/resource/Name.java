package skaro.pokeapi.resource;

import skaro.pokeapi.resource.language.Language;

public class Name {

	private String name;
	private Language language;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	
}
