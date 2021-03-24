package skaro.pokeapi.cache;

import java.util.function.Supplier;

import reactor.core.publisher.Mono;

public class CacheSpec<T> {

	private String key;
	private Class<T> type;
	private Supplier<Mono<T>> monoSupplier;

	public String getKey() {
		return key;
	}
	public Class<T> getType() {
		return type;
	}
	public Supplier<Mono<T>> getMonoSupplier() {
		return monoSupplier;
	}
	
	public static <T> CacheSpecBuilder<T> of(Class<T> type, String key) {
		CacheSpecBuilder<T> builder = new CacheSpecBuilder<>();
		builder.spec = new CacheSpec<>();
		builder.spec.key = key;
		builder.spec.type = type;

		return builder;
	}

	public static class CacheSpecBuilder<T> {
		private CacheSpec<T> spec;
		
		private CacheSpecBuilder() {
			
		}

		public CacheSpec<T> orCache(Supplier<Mono<T>> monoSupplier) {
			this.spec.monoSupplier = monoSupplier;
			return this.spec;
		}

	}

}
