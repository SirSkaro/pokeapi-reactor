package skaro.pokeapi.resource;

import skaro.pokeapi.resource.language.Language;

public class VerboseEffect {

	private String effect;
	private String shortEffect;
	private NamedApiResource<Language> language;
	
	public String getEffect() {
		return effect;
	}
	public void setEffect(String effect) {
		this.effect = effect;
	}
	public String getShortEffect() {
		return shortEffect;
	}
	public void setShortEffect(String shortEffect) {
		this.shortEffect = shortEffect;
	}
	public NamedApiResource<Language> getLanguage() {
		return language;
	}
	public void setLanguage(NamedApiResource<Language> language) {
		this.language = language;
	}
	
}
