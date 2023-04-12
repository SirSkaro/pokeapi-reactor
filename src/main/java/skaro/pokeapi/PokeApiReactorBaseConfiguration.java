package skaro.pokeapi;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import skaro.pokeapi.client.PokeApiEndpointRegistry;
import skaro.pokeapi.client.PokeApiEntityFactory;
import skaro.pokeapi.client.WebClientEntityFactory;

@Configuration
@Import(PokeApiReactorEndpointConfiguration.class)
public class PokeApiReactorBaseConfiguration {
	public static final String CONFIGURATION_PROPERTIES_PREFIX = "skaro.pokeapi";
	public static final String POKEAPI_WEBCLIENT_BEAN = "pokeapiWebClientBean";
	public static final String POKEAPI_JSON_DECODER_BEAN = "pokeapiDecoderBean";
	public static final String POKEAPI_JSON_ENCODER_BEAN = "pokeapiEncoderBean";
	
	@Bean
	@Valid
	@ConfigurationProperties(CONFIGURATION_PROPERTIES_PREFIX)
	public PokeApiConfigurationProperties pokeApiConfigurationProperties() {
		return new PokeApiConfigurationProperties();
	}
	
	@Bean(POKEAPI_JSON_DECODER_BEAN)
	public Jackson2JsonDecoder jsonDecoder() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		return new Jackson2JsonDecoder(mapper, MediaType.APPLICATION_JSON);
	}
	
	@Bean(POKEAPI_JSON_ENCODER_BEAN)
	public Jackson2JsonEncoder jsonEncoder() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		
		return new Jackson2JsonEncoder(mapper, MediaType.APPLICATION_JSON);
	}
	
	@Bean(POKEAPI_WEBCLIENT_BEAN)
	public WebClient webClient(HttpClient httpClient,
			@Qualifier(POKEAPI_JSON_ENCODER_BEAN) Jackson2JsonEncoder encoder, 
			@Qualifier(POKEAPI_JSON_DECODER_BEAN) Jackson2JsonDecoder decoder, 
			PokeApiConfigurationProperties configurationProperties) {
		
		ExchangeStrategies strategies = ExchangeStrategies.builder()
	            .codecs(clientDefaultCodecsConfigurer -> {
	            	clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonEncoder(encoder);
	                clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonDecoder(decoder);
	            }).build();
		
		ExchangeFilterFunction logRequest = ExchangeFilterFunction.ofRequestProcessor(request -> {
			Logger log = LoggerFactory.getLogger(PokeApiEntityFactory.class);
			log.info("{} {}",request.method(), request.url());
			return Mono.just(request);
		});
		
		return WebClient.builder()
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.baseUrl(configurationProperties.getBaseUri().toString())
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.exchangeStrategies(strategies)
				.filter(logRequest)
				.codecs(configurer -> configurer.defaultCodecs()
						.maxInMemorySize(configurationProperties.getMaxBytesToBuffer()))
				.build();
	}
	
	@Bean
	public PokeApiEntityFactory pokeApiEntityFactory(WebClient webClient, PokeApiEndpointRegistry registry) {
		return new WebClientEntityFactory(webClient, registry);
	}
	
}
