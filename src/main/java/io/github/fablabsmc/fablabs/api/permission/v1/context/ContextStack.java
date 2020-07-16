package io.github.fablabsmc.fablabs.api.permission.v1.context;

import java.util.Collections;
import java.util.Optional;

import io.github.fablabsmc.fablabs.api.permission.v1.actor.Actor;
import io.github.fablabsmc.fablabs.impl.permission.ContextStackImpl;
import io.github.fablabsmc.fablabs.impl.permission.PermissionManagerImpl;

/**
 * Represents a context in which a permission check occurs.
 *
 * <p>Context stack instances should not be cached.
 */
public interface ContextStack extends Iterable<ContextStack.StackEntry<?>> {
	/**
	 * Gets an empty context.
	 *
	 * @return TODO
	 */
	static ContextStack empty() {
		return PermissionManagerImpl.EMPTY_CONTEXT;
	}

	/**
	 * Creates a new context stack.
	 *
	 * @return a new context stack.
	 */
	static ContextStack create() {
		return new ContextStackImpl();
	}

	/**
	 * Pushes a context to the context stack
	 *
	 * @param key the key of the context
	 * @param value the value of the context
	 * @param <V> the type of context
	 * @return TODO
	 */
	default <V> ContextStack pushContext(ContextKey<V> key, V value) {
		return this.pushContext(key, value, Collections.emptyList());
	}

	/**
	 * Pushes a context to the context stack
	 *
	 * @param key the key of the context
	 * @param value the value of the context
	 * @param applicableActors any applicable actors to this context
	 * @param <V> the type of context
	 * @return TODO
	 */
	<V> ContextStack pushContext(ContextKey<V> key, V value, Iterable<Actor> applicableActors);

	<V> Optional<StackEntry<V>> get(ContextKey<V> key);

	interface StackEntry<V> {
		ContextKey<V> getKey();

		V getValue();

		Iterable<Actor> applicableActors();
	}
}
