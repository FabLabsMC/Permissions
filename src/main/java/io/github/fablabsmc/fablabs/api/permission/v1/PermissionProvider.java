package io.github.fablabsmc.fablabs.api.permission.v1;

import io.github.fablabsmc.fablabs.api.permission.v1.subject.Subject;

import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.util.TriState;

/**
 * Represents a provider used to query a permissions implementation to determine if a subject has a permission.
 *
 * <p>Whether the provider accepts permissions which are granted within a context, temporarily or something else is up to the implementor.
 */
public interface PermissionProvider {
	/**
	 * Checks if a subject has a certain permission.
	 *
	 * <p>The permissions implementation controls whether a player has a permission or not, such as permissions which are granted temporarily or due to a context.
	 *
	 * @param subject     the subject to check
	 * @param permission the permission to check for
	 * @return whether this player has a permission. False if no providers are registered.
	 * @apiNote Most implementations expect the {@link Identifier} to be in the format of {@code mymod:permission.child}, though you should consult your implementation to determine how it is handled.
	 */
	TriState getPermissionValue(Subject subject, Identifier permission);

	/**
	 * Gets the id of this provider is registered to.
	 *
	 * @return the id of this provider.
	 */
	Identifier getId();
}
