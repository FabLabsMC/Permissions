package io.github.fablabsmc.fablabs.api.permission.v1.context;

import java.util.IdentityHashMap;
import java.util.Map;

import com.google.common.reflect.TypeToken;

import net.minecraft.util.Identifier;

@SuppressWarnings("UnstableApiUsage")
public final class ContextKey<V> {
	private static final Map<Identifier, ContextKey<?>> KEYS = new IdentityHashMap<>();
	private final Identifier id;
	private final TypeToken<V> type;

	public static <V> ContextKey<V> getOrCreate(Identifier id, TypeToken<V> type) {
		if (KEYS.containsKey(id)) {
			//noinspection unchecked
			return (ContextKey<V>) KEYS.get(id);
		}

		final ContextKey<V> key = new ContextKey<>(id, type);
		KEYS.put(id, key);

		return key;
	}

	public static <V> ContextKey<V> get(Identifier id) {
		//noinspection unchecked
		return (ContextKey<V>) KEYS.get(id);
	}

	private ContextKey(Identifier id, TypeToken<V> type) {
		this.id = id;
		this.type = type;
	}

	public Identifier getId() {
		return this.id;
	}

	public TypeToken<V> getType() {
		return this.type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ContextKey)) return false;

		ContextKey<?> that = (ContextKey<?>) o;

		if (!this.getId().equals(that.getId())) return false;
		return this.getType().equals(that.getType());
	}

	@Override
	public int hashCode() {
		int result = this.getId().hashCode();
		result = 31 * result + this.getType().hashCode();
		return result;
	}
}
