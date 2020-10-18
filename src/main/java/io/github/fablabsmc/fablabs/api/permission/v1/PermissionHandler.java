package io.github.fablabsmc.fablabs.api.permission.v1;

import io.github.fablabsmc.fablabs.api.permission.v1.actor.Actor;
import io.github.fablabsmc.fablabs.api.permission.v1.context.UserContext;

import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.util.TriState;

/**
 * Represents a handler which determines whether an actor can perform an action.
 */
public interface PermissionHandler {
	/**
	 *
	 * @param actor
	 * @param action
	 * @param userContext
	 * @return
	 */
	<C> TriState getPermissionValue(Actor actor, ActionType<C> action, C actionContext, UserContext userContext);

	/**
	 * Gets the id of this permission handler.
	 *
	 * @return the id of this permission handler.
	 */
	Identifier getId();
}
