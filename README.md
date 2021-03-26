# pokeapi-reactor
A non-blocking, reactive API client for [PokeAPI](https://pokeapi.co/) with caching for Spring Boot projects.

### Features
* Simple, single [entry point](../master/src/main/java/skaro/pokeapi/client/PokeApiClient.java) for all client operations.
* Non-blocking HTTP operations and non-blocking caching.
* Fully customizable caching. Supports Spring Boot's generic [CacheManager](https://docs.spring.io/spring-boot/docs/1.3.0.M1/reference/html/boot-features-caching.html#_supported_cache_providers) for caching.
* Caching and non-caching configurations.

## Getting started
