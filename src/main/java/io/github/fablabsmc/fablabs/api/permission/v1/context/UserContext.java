package io.github.fablabsmc.fablabs.api.permission.v1.context;

import net.minecraft.inventory.Inventory;

/**
 * A context that supplies extra information about the user(s) that may have caused an action to occur.
 *
 * <p>It is highly discouraged to store the user context.
 */
public interface UserContext {
	<V> ContextDescription<V> get(ContextKey<V> key);

	/**
	 * Represents a description of an element within a {@link UserContext}.
	 *
	 * <p>A context description describes an object within a user context at the minimum type constraint.
	 *
	 * @param <V>
	 */
	interface ContextDescription<V> {
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
		 * One example of this is block entities which implement {@link Inventory}.
		 *
		 * <p>If the provided {@code type} is equal to the value of {@link ContextDescription#getDescribedType() the described type}, this will return true.
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
		<N> N getAs(Class<N> type);
	}
}
