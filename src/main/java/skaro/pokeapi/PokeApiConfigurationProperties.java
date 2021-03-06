package skaro.pokeapi;

import java.net.URI;

import javax.validation.constraints.NotNull;

public class PokeApiConfigurationProperties {

	@NotNull
	private URI baseUri;

	public URI getBaseUri() {
		return baseUri;
	}

	public void setBaseUri(URI baseUri) {
		this.baseUri = baseUri;
	}
	
}
