package skaro.pokeapi;

import javax.validation.Valid;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;

@Configuration
public class PokeApiReactorConfiguration {
	public static final String CONFIGURATION_PROPERTIES_PREFIX = "skaro.pokeapi";
	public static final String POKEAPI_WEBCLIENT_BEAN = "pokeapiWebClientBean";
	
	@Bean
	@Valid
	@ConfigurationProperties(CONFIGURATION_PROPERTIES_PREFIX)
	public PokeApiConfigurationProperties pokeApiConfigurationProperties() {
		return new PokeApiConfigurationProperties();
	}
	
	@Bean(POKEAPI_WEBCLIENT_BEAN)
	public WebClient webClient(HttpClient httpClient, PokeApiConfigurationProperties configurationProperties) {
		return WebClient.builder()
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.baseUrl(configurationProperties.getBaseUri().toString())
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
				.build();
	}
	
	@Bean
	public PokeApiEntityFactory entityFactory(WebClient webClient) {
		return new WebClientEntityFactory(webClient);
	}
	
	@Bean
	public PokeApiClient pokeApiClient(PokeApiEntityFactory entityFactory) {
		return new ReactorPokeApiClient(entityFactory);
	}
	
}
