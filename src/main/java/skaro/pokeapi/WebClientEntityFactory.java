package skaro.pokeapi;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class WebClientEntityFactory implements PokeApiEntityFactory {
	private final static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	private WebClient webClient;

	public WebClientEntityFactory(WebClient webClient) {
		this.webClient = webClient;
	}

	@Override
	public <T> Mono<T> getResource(String uri, Class<T> resourceClass) {
		LOG.info("GET {}", uri);
		return webClient.get()
				.uri(uri)
				.retrieve()
				.bodyToMono(resourceClass);
	}
	
}
