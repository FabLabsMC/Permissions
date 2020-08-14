package io.github.fablabsmc.fablabs.api.permission.v1;

import io.github.fablabsmc.fablabs.api.permission.v1.actor.Actor;

import net.minecraft.util.Identifier;

/**
 * Represents a handler used to query a permissions implementation to determine if a subject has a permission.
 *
 * <p>Whether the handler accepts permissions which are granted within a context, temporarily or something else is up to the implementor.
 */
public interface PermissionHandler {
	/**
	 * Checks if an actor has a certain permission.
	 *
	 * <p>The permissions implementation controls whether a player has a permission or not, such as permissions which are granted temporarily or due to a context.
	 *
	 * @param actor the subject to check
	 * @param permission the permission to check for
	 * @return whether this subject has a permission. False if no providers are registered.
	 * @implNote If a permissions implementation is not available, it is advised to return {@link TriState#DEFAULT}.
	 */
	TriState getPermissionValue(Actor actor, String permission);

	/**
	 * Gets the id of this provider is registered as.
	 *
	 * @return the id of this provider.
	 */
	Identifier getId();
}
