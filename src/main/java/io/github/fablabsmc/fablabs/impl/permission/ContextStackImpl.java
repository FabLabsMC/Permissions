package io.github.fablabsmc.fablabs.impl.permission;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import io.github.fablabsmc.fablabs.api.permission.v1.actor.Actor;
import io.github.fablabsmc.fablabs.api.permission.v1.context.ContextKey;
import io.github.fablabsmc.fablabs.api.permission.v1.context.ContextStack;

public class ContextStackImpl implements ContextStack {
	// Allocate some stack space to start
	private StackEntryImpl<?>[] entries = new StackEntryImpl[16];
	private final Map<ContextKey<?>, Integer> trackedKeys = new HashMap<>();
	private int currentPos = -1;

	@Override
	public Optional<StackEntry<?>> current() {
		if (this.currentPos == -1) {
			return Optional.empty();
		}

		return Optional.of(this.entries[this.currentPos]);
	}

	@Override
	public <V> ContextStack swap(ContextKey<V> key, V value, Iterable<Actor> applicableActors) {
		final Integer trackedPos = this.trackedKeys.get(key);

		if (trackedPos != null) {
			// Something is horribly wrong if our trackedPos bigger than the current pos
			if (trackedPos > this.currentPos) {
				throw new IllegalStateException(); // TODO: Explain
			}

			this.entries[trackedPos] = new ContextStackImpl.StackEntryImpl<>(key, value, applicableActors);
		}

		return this;
	}

	@Override
	public <V> ContextStack pushContext(ContextKey<V> key, V value, Iterable<Actor> applicableActors) {
		if (this.trackedKeys.containsKey(key)) {
			throw new IllegalArgumentException(); // TODO: No duplicates
		}

		this.currentPos++;
		this.trackedKeys.put(key, this.currentPos);
		this.entries[this.currentPos] = new ContextStackImpl.StackEntryImpl<>(key, value, applicableActors);

		// Expand the stack array if we happen to exceed
		if (this.entries.length == this.currentPos + 1) {
			final StackEntryImpl<?>[] stackEntries = new StackEntryImpl[this.entries.length + 16];
			System.arraycopy(this.entries, 0, stackEntries, 0, this.entries.length);
			this.entries = stackEntries;
		}

		return this;
	}

	@Override
	public <V> Optional<StackEntry<V>> get(ContextKey<V> key) {
		final Integer trackedPos = this.trackedKeys.get(key);

		if (trackedPos != null) {
			//noinspection unchecked
			return Optional.of((StackEntry<V>) this.entries[trackedPos]);
		}

		return Optional.empty();
	}

	@Override
	public Iterator<StackEntry<?>> iterator() {
		return new Iterator<StackEntry<?>>() {
			private int pos = 0;

			@Override
			public boolean hasNext() {
				return ContextStackImpl.this.entries.length > this.pos;
			}

			@Override
			public StackEntry<?> next() {
				if (!this.hasNext()) {
					throw new UnsupportedOperationException("");
				}

				final StackEntry<?> entry = ContextStackImpl.this.entries[this.pos];
				this.pos++;

				return entry;
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
