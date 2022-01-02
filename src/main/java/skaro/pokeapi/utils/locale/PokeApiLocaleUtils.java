package skaro.pokeapi.utils.locale;

import java.util.Optional;

import skaro.pokeapi.resource.Name;

public class PokeApiLocaleUtils {

	public static Optional<Name> getInLocale(Localizable localizable, String locale) {
		return localizable.getNames().stream()
				.filter(name -> name.getLanguage().getName().equalsIgnoreCase(locale))
				.findFirst();
	}
	
}
