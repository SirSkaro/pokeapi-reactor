package skaro.pokeapi;

import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import skaro.pokeapi.resource.NamedApiResource;

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
	
	@Override
	public <T> Mono<T> getNamedResource(NamedApiResource<T> resource, Class<T> cls) {
		LOG.info("GET {}", resource.getUrl().toString());
		return webClient.get()
				.uri(URI.create(resource.getUrl()))
				.retrieve()
				.bodyToMono(cls);
	}

	@Override
	public <T> Flux<T> getNamedResources(List<NamedApiResource<T>> resources, Class<T> cls) {
		List<Mono<T>> resourceMonos = resources.stream()
				.map(resource -> this.getNamedResource(resource, cls))
				.collect(Collectors.toList());
		
		return Flux.merge(resourceMonos);
	}
	
}
