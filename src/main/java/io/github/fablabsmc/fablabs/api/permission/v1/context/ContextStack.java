package io.github.fablabsmc.fablabs.api.permission.v1.context;

import java.util.Collections;
import java.util.Optional;

import io.github.fablabsmc.fablabs.api.permission.v1.actor.Actor;
import io.github.fablabsmc.fablabs.impl.permission.ContextStackImpl;

/**
 * Represents a context in which a permission check occurs.
 *
 * <p>Context stack instances should not be cached.
 */
public interface ContextStack extends Iterable<ContextStack.StackEntry<?>> {
	/**
	 * Creates a new context stack.
	 *
	 * @return a new context stack.
	 */
	static ContextStack create() {
		return new ContextStackImpl();
	}

	/**
	 * Gets the current stack entry.
	 *
	 * @return TODO
	 */
	Optional<StackEntry<?>> current();

	/**
	 * Swaps a stack entry of a specific key with a new value.
	 *
	 * <p>If an entry of the specified key is not present, the stack is not mutated.
	 *
	 * @param key the key of the stack entry
	 * @param value the value to replace on the stack entry
	 * @param <V> the type of context
	 * @return this stack for chaining
	 */
	default <V> ContextStack swap(ContextKey<V> key, V value) {
		return this.swap(key, value, Collections.emptyList());
	}

	/**
	 * Swaps a stack entry of a specific key with a new value.
	 *
	 * @param key the key of the stack entry
	 * @param value the value to replace on the stack entry
	 * @param applicableActors any applicable actors to this context
	 * @param <V> the type of context
	 * @return this stack for chaining
	 */
	<V> ContextStack swap(ContextKey<V> key, V value, Iterable<Actor> applicableActors);

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

	/**
	 * Gets a stack entry of a specific key.
	 *
	 * @param key the key of the context
	 * @param <V> the type of context
	 * @return TODO
	 */
	<V> Optional<StackEntry<V>> get(ContextKey<V> key);

	interface StackEntry<V> {
		ContextKey<V> getKey();

		V getValue();

		Iterable<Actor> applicableActors();
	}
}
