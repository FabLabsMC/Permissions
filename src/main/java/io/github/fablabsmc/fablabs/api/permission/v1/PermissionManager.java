package io.github.fablabsmc.fablabs.api.permission.v1;

import java.util.Objects;

import io.github.fablabsmc.fablabs.api.permission.v1.actor.Actor;
import io.github.fablabsmc.fablabs.api.permission.v1.context.ContextStack;
import io.github.fablabsmc.fablabs.impl.permission.PermissionManagerImpl;
import net.fabricmc.fabric.api.util.TriState;

/**
 * A permission manager is used to delegate permission value checks on an actor to {@link PermissionHandler}s provided by implementors.
 */
public final class PermissionManager {
	/**
	 * Registers a permission handler.
	 *
	 * <p>A handler is used to query a permissions implementation to determine if a subject has a permission.
	 *
	 * @param handler the handler
	 * @throws IllegalArgumentException if a handler of the same {@link PermissionHandler#getId() id} is registered.
	 */
	public static void registerHandler(PermissionHandler handler) {
		Objects.requireNonNull(handler, "The handler cannot be null");
		PermissionManagerImpl.registerHandler(handler);
	}

	/**
	 * Checks if a actor has a certain permission.
	 *
	 * <p>The permissions implementation controls whether a player has a permission or not, such as permissions which are granted temporarily or due to a context.
	 *
	 * @param actor the actor
	 * @param permission the permission to check for
	 * @return whether this player has a permission. False if no providers are registered.
	 */
	public static TriState getPermissionValue(Actor actor, ContextStack contextStack, String permission) {
		Objects.requireNonNull(actor, "Actor cannot be null");
		Objects.requireNonNull(permission, "Permission cannot be null");
		return PermissionManagerImpl.getPermissionValue(actor, contextStack, permission);
	}

	private PermissionManager() {
	}
}
