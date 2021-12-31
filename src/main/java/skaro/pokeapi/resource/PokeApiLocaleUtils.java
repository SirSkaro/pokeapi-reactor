package skaro.pokeapi.resource;

import java.util.Optional;

public class PokeApiLocaleUtils {

	public static Optional<Name> getInLocale(Localizable localizable, String locale) {
		return localizable.getNames().stream()
				.filter(name -> name.getLanguage().getName().equalsIgnoreCase(locale))
				.findFirst();
	}
	
}
