package io.github.fablabsmc.fablabs.api.permission.v1.context;

import java.util.Optional;

import io.github.fablabsmc.fablabs.impl.permission.UserContextImpl;

/**
 * A context that supplies extra information about the user(s) that may have caused an action to occur.
 *
 * <p>It is highly discouraged to store the user context as the context may change frequently.
 */
public interface UserContext {
	/**
	 * Creates an empty user context.
	 *
	 * @return a new user context
	 */
	static UserContext create() {
		return new UserContextImpl();
	}

	/**
	 * Gets the description of an entry in the user context, throwing an exception if no value was mapped to the context key.
	 *
	 * @param key the key
	 * @param <V> the type of value
	 * @return a description of the type mapped to the context
	 */
	<V> Description<V> require(ContextKey<V> key);

	/**
	 * Gets the description of an entry in the user context.
	 *
	 * @param key the key
	 * @param <V> the type of value
	 * @return a description of the type mapped to the context or {@link Optional#empty()} if the context does not have a value mapped to the specified context key.
	 */
	<V> Optional<Description<V>> get(ContextKey<V> key);

	/**
	 * Adds a new value to the context.
	 *
	 * @param key the key of the value
	 * @param value the value
	 * @param <V> the type of value
	 * @return this context
	 */
	<V> UserContext with(ContextKey<V> key, V value);

	/**
	 * Checks if the user context is empty.
	 *
	 * @return true if the context is empty
	 */
	boolean isEmpty();

	/**
	 * Represents a description of an element within a {@link UserContext}.
	 *
	 * <p>A context description describes an object within a user context at the minimum type constraint.
	 *
	 * @param <V> the minimum type constraint of the described object
	 */
	interface Description<V> {
		/**
		 * Gets the class that represents the minimum type constraint of the object being described.
		 *
		 * @return the minimum class type constraint of the object.
		 */
		Class<? extends V> getDescribedType();

		/**
		 * Checks if the the described object is applicable to a certain class type.
		 *
		 * <p>The wildcards on the {@code type} parameter are intentionally the minimum type constraint allow testing of types that are mixed into an object or do not directly extend the type.
		 * One use of this check is checking if a block entity which implements {@code AnInterface}:
		 * <blockquote><pre>
		 * UserContext.Description&lt;BlockEntity&gt; contextDescription = [...]
		 *
		 * if (contextDescription.isApplicableTo(AnInterface.class)) {
		 * 	contextDescription.to(AnInterface.class)...
		 * }
		 * </pre></blockquote>
		 *
		 * <p>If the provided {@code type} is equal to the value of {@link Description#getDescribedType() the described type}, this will return true.
		 *
		 * @param type the type
		 * @return true if the provided type is applicable to the described object.
		 */
		boolean isApplicableTo(Class<?> type);

		/**
		 * Gets the described object as the minimum type constraint.
		 *
		 * @return the described object of the minimum type constraint.
		 */
		V get();

		/**
		 * Gets the described object as another applicable type.
		 *
		 * @param type the applicable type
		 * @param <N> the applicable object type
		 * @return the object being described of the type {@code N}.
		 * @throws ClassCastException if the applicable type is not compatable with the class type
		 */
		<N> N to(Class<N> type);
	}
}
