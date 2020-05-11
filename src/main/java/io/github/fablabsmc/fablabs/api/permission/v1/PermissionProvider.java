package io.github.fablabsmc.fablabs.api.permission.v1;

import io.github.fablabsmc.fablabs.api.permission.v1.subject.PermissionSubject;

import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.util.TriState;

/**
 * Represents a provider used to query a permissions implementation to determine if a subject has a permission.
 *
 * <p>Whether the provider accepts permissions which are granted within a context, temporarily or something else is up to the implementor.
 *
 * @apiNote Most implementations expect the {@link Identifier} used in permissions checks to be in the format of {@code mymod:permission.child}, though you should consult your implementation to determine how this is handled.
 */
public interface PermissionProvider {
	/**
	 * Checks if a subject has a certain permission.
	 *
	 * <p>The permissions implementation controls whether a player has a permission or not, such as permissions which are granted temporarily or due to a context.
	 *
	 * @param subject the subject to check
	 * @param permission the permission to check for
	 * @return whether this subject has a permission. False if no providers are registered.
	 * @implNote If a permissions implementation is not available, it is advise to return {@link TriState#DEFAULT}.
	 */
	TriState getPermissionValue(PermissionSubject subject, Identifier permission);

	/**
	 * Gets the id of this provider is registered to.
	 *
	 * @return the id of this provider.
	 */
	Identifier getId();
}
