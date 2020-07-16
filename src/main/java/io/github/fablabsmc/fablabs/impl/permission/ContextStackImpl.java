package io.github.fablabsmc.fablabs.impl.permission;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Optional;

import io.github.fablabsmc.fablabs.api.permission.v1.actor.Actor;
import io.github.fablabsmc.fablabs.api.permission.v1.context.ContextKey;
import io.github.fablabsmc.fablabs.api.permission.v1.context.ContextStack;

public class ContextStackImpl implements ContextStack {
	private final Deque<StackEntryImpl<?>> entries = new ArrayDeque<>();

	@Override
	public <V> ContextStack pushContext(ContextKey<V> key, V value, Iterable<Actor> applicableActors) {
		for (StackEntryImpl<?> entry : this.entries) {
			if (entry.getKey().equals(key)) {
				throw new IllegalArgumentException(String.format("A ContextStack cannot contain two contexts with same key %s", key));
			}
		}

		this.entries.push(new StackEntryImpl<>(key, value, applicableActors));

		return this;
	}

	@Override
	public <V> Optional<StackEntry<V>> get(ContextKey<V> key) {
		for (StackEntryImpl<?> entry : this.entries) {
			if (entry.getKey().equals(key)) {
				//noinspection unchecked
				return Optional.of((StackEntry<V>) entry);
			}
		}

		return Optional.empty();
	}

	@Override
	public Iterator<StackEntry<?>> iterator() {
		return new Iterator<StackEntry<?>>() {
			@Override
			public boolean hasNext() {
				return ContextStackImpl.this.entries.iterator().hasNext();
			}

			@Override
			public StackEntry<?> next() {
				return ContextStackImpl.this.entries.iterator().next();
			}
		};
	}

	static class StackEntryImpl<V> implements StackEntry<V> {
		private final ContextKey<V> key;
		private final V object;
		private final Iterable<Actor> actors;

		StackEntryImpl(ContextKey<V> key, V object, Iterable<Actor> actors) {
			this.key = key;
			this.object = object;
			this.actors = actors;
		}

		@Override
		public ContextKey<V> getKey() {
			return this.key;
		}

		@Override
		public V getValue() {
			return this.object;
		}

		@Override
		public Iterable<Actor> applicableActors() {
			return this.actors;
		}
	}
}
