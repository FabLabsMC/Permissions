package io.github.fablabsmc.fablabs.impl.permission;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import io.github.fablabsmc.fablabs.api.permission.v1.context.ContextKey;
import io.github.fablabsmc.fablabs.api.permission.v1.context.UserContext;

public final class UserContextImpl implements UserContext {
	private Map<ContextKey<?>, UserContextImpl.DescriptionImpl<?>> context = new HashMap<>();

	@Override
	public <V> Description<V> require(ContextKey<V> key) {
		//noinspection unchecked
		return (Description<V>) this.context.computeIfAbsent(key, k -> {
			throw new IllegalArgumentException(String.format("Context key of %s is not present on the user context.", k));
		});
	}

	@Override
	public <V> Optional<Description<V>> get(ContextKey<V> key) {
		//noinspection unchecked
		return Optional.ofNullable((Description<V>) this.context.get(key));
	}

	@Override
	public <V> UserContext with(ContextKey<V> key, V value) {
		Objects.requireNonNull(key);
		Objects.requireNonNull(value);

		this.context.put(key, new DescriptionImpl<>(value));
		return this;
	}

	@Override
	public boolean isEmpty() {
		return this.context.isEmpty();
	}

	static final class DescriptionImpl<V> implements Description<V> {
		private final V describedObject;

		DescriptionImpl(V describedObject) {
			this.describedObject = describedObject;
		}

		@Override
		public Class<? extends V> getDescribedType() {
			//noinspection unchecked,rawtypes
			return ((Class) this.describedObject.getClass());
		}

		@Override
		public boolean isApplicableTo(Class<?> type) {
			return type.isAssignableFrom(this.describedObject.getClass());
		}

		@Override
		public V get() {
			return this.describedObject;
		}

		@Override
		public <N> N to(Class<N> type) throws ClassCastException {
			return type.cast(this.describedObject);
		}
	}
}
