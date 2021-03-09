package skaro.pokeapi;

import javax.validation.Valid;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

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
		ObjectMapper mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		ExchangeStrategies strategies = ExchangeStrategies
	            .builder()
	            .codecs(clientDefaultCodecsConfigurer -> {
	            	clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(new ObjectMapper(), MediaType.APPLICATION_JSON));
	                clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(mapper, MediaType.APPLICATION_JSON));
	            }).build();
		
		return WebClient.builder()
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.baseUrl(configurationProperties.getBaseUri().toString())
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.exchangeStrategies(strategies)
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
