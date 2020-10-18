package io.github.fablabsmc.fablabs.api.permission.v1.actor;

import io.github.fablabsmc.fablabs.api.permission.v1.ActionType;
import io.github.fablabsmc.fablabs.api.permission.v1.context.UserContext;

import net.fabricmc.fabric.api.util.TriState;

public interface Actor {
	default <C> boolean hasPermission(ActionType<C> action, C actionContext, UserContext userContext) {
		return this.getPermissionValue(action, actionContext, userContext).get();
	}

	<C> TriState getPermissionValue(ActionType<C> action, C actionContext, UserContext userContext);
}
