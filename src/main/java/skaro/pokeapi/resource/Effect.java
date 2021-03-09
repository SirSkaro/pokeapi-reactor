package skaro.pokeapi.resource;

import skaro.pokeapi.resource.language.Language;

public class Effect {

	private String effect;
	private NamedApiResource<Language> language;
	
	public String getEffect() {
		return effect;
	}
	public void setEffect(String effect) {
		this.effect = effect;
	}
	public NamedApiResource<Language> getLanguage() {
		return language;
	}
	public void setLanguage(NamedApiResource<Language> language) {
		this.language = language;
	}
	
}
