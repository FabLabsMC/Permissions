package io.github.fablabsmc.fablabs.impl.permission;

import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;

import io.github.fablabsmc.fablabs.api.permission.v1.actor.Actor;
import io.github.fablabsmc.fablabs.api.permission.v1.context.ContextKey;
import io.github.fablabsmc.fablabs.api.permission.v1.context.ContextStack;

final class EmptyContextStack implements ContextStack {
	EmptyContextStack() {
	}

	@Override
	public <V> ContextStack pushContext(ContextKey<V> key, V value, Iterable<Actor> applicableActors) {
		return this;
	}

	@Override
	public <V> Optional<StackEntry<V>> get(ContextKey<V> key) {
		return Optional.empty();
	}

	@Override
	public Iterator<StackEntry<?>> iterator() {
		return Collections.emptyIterator();
	}
}
