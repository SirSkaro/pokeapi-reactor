package skaro.pokeapi;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import skaro.pokeapi.resource.NamedApiResource;
import skaro.pokeapi.resource.NamedApiResourceList;

public class WebClientEntityFactory implements PokeApiEntityFactory {
	private WebClient webClient;
	private PokeApiEndpointRegistry endpointRegistry;

	public WebClientEntityFactory(WebClient webClient, PokeApiEndpointRegistry endpointRegistry) {
		this.webClient = webClient;
		this.endpointRegistry = endpointRegistry;
	}

	@Override
	public <T extends PokeApiResource> Mono<T> getResource(Class<T> resourceClass, String nameOrId) {
		String endpoint = endpointRegistry.getEndpoint(resourceClass);
		return webClient.get()
				.uri(uriBuilder -> uriBuilder
						.path(endpoint)
						.path("/{id}")
						.build(nameOrId))
				.retrieve()
				.bodyToMono(resourceClass);
	}
	
	@Override
	public <T extends PokeApiResource> Mono<NamedApiResourceList<T>> getBaseResource(Class<T> resourceClass) {
		String endpoint = endpointRegistry.getEndpoint(resourceClass);
		return webClient.get()
				.uri(uriBuilder -> uriBuilder.path(endpoint).build())
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<NamedApiResourceList<T>>(){});
	}
	
	@Override
	public <T extends PokeApiResource> Mono<NamedApiResourceList<T>> getBaseResource(Class<T> resourceClass, PageQuery query) {
		String endpoint = endpointRegistry.getEndpoint(resourceClass);
		return webClient.get()
				.uri(uriBuilder -> uriBuilder
						.path(endpoint)
						.queryParam("limit", query.getLimit())
						.queryParam("offset", query.getOffset())
						.build())
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<NamedApiResourceList<T>>(){});
	}
	
	@Override
	public <T extends PokeApiResource> Mono<T> getNamedResource(NamedApiResource<T> resource, Class<T> resourceClass) {
		return webClient.get()
				.uri(URI.create(resource.getUrl()))
				.retrieve()
				.bodyToMono(resourceClass);
	}

	@Override
	public <T extends PokeApiResource> Flux<T> getNamedResources(List<NamedApiResource<T>> resources, Class<T> resourceClass) {
		List<Mono<T>> resourceMonos = resources.stream()
				.map(resource -> getNamedResource(resource, resourceClass))
				.collect(Collectors.toList());
		
		return Flux.merge(resourceMonos);
	}

}
