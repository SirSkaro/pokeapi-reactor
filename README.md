# pokeapi-reactor
A non-blocking, reactive API client for [PokeAPI](https://pokeapi.co/) with caching for Spring Boot projects.

### Features
* Simple, single [entry point](../master/src/main/java/skaro/pokeapi/client/PokeApiClient.java) for all client operations.
* Non-blocking HTTP operations and non-blocking caching.
* Fully customizable caching. Supports Spring Boot's generic [CacheManager](https://docs.spring.io/spring-boot/docs/1.3.0.M1/reference/html/boot-features-caching.html#_supported_cache_providers) for caching.
* Caching and non-caching configurations.

## Getting started
### Project Configuration

#### Properties
You can (must) configure the location of the PokeAPI instance you want to use. Add the following property to your `application.properties`:

```
skaro.pokeapi.base-uri=https://pokeapi.co/api/v2/ #or the url of your own instance
```

#### Application Context
Import one of pokeapi-reactor's configurations as well as specify your own [reactor.netty.http.client.HttpClient](https://projectreactor.io/docs/netty/release/api/reactor/netty/http/client/HttpClient.html) bean. Two configurations are available: caching and non-caching. Below is an example of a caching configuration.

```java
@Configuration
@Import(PokeApiReactorCachingConfiguration.class)
@EnableCaching
public class MyPokeApiReactorCachingConfiguration {
	@Bean
	public HttpClient httpClient() {
		return HttpClient.create()
                  .compress(true)
                  .resolver(DefaultAddressResolverGroup.INSTANCE);
	}
}
```
Or, if you'd rather not enable caching:
```java
@Configuration
@Import(PokeApiReactorNonCachingConfiguration.class)
public class MyPokeApiReactorNonCachingConfiguration {
   @Bean
	public HttpClient httpClient() { ... }
}
```
Both the `PokeApiReactorCachingConfiguration` and `PokeApiReactorNonCachingConfiguration` will register the appropriate `PokeApiClient` bean.

### Fetching a resource
Inject the registered `PokeApiClient` into your class and request a resource.
```java
@Autowired
private PokeApiClient pokeApiClient;

...

public void printPokemon() {
   pokeApiClient.getResource(Pokemon.class, "pikachu")
      .map(pokemon -> String.format("%s has %d forms", pokemon.getName(), pokemon.getForms().size()))
      .subscribe(System.out::println);
}
```
If you don't mind blocking, you can simply block for the resource.
```java
public void printPokemon() {
   Pokemon pokemon = pokeApiClient.getResource(Pokemon.class, "pikachu").block();
   String pokemonInfo = String.format("%s has %d forms", pokemon.getName(), pokemon.getForms().size()));
   System.out.println(pokemonInfo);
}
```

### Following a resource
Following links to other resources also has first-class support.

```java
public void printPokemonForms() {
   pokeApiClient.getResource(Pokemon.class, "pikachu")
      .flatMapMany(pokemon -> pokeApiClient.followResources(pokemon::getForms, PokemonForm.class))
      .map(form -> String.format("Pikachu has a form called %s", form.getName()))
      .subscribe(System.out::println);
}
```
Again, with blocking:
```java
public void printPokemonForms() {
   Pokemon pokemon = pokeApiClient.getResource(Pokemon.class, "pikachu").block();
   List<PokemonForm> forms = pokeApiClient.followResources(pokemon::getForms, PokemonForm.class)
      .collectList()
      .block();

   for(PokemonForm form : forms) {
      String formInfo = String.format("Pikachu has a form called %s", form.getName());
      System.out.println(formInfo);
   }
}
```
