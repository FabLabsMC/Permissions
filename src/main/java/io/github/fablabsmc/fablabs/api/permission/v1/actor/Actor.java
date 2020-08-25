package io.github.fablabsmc.fablabs.api.permission.v1.actor;

import io.github.fablabsmc.fablabs.api.permission.v1.context.UserContext;

import net.fabricmc.fabric.api.util.TriState;

public interface Actor {
	default boolean hasPermission(String action, UserContext userContext) {
		return this.getPermissionValue(action, userContext).get();
	}

	TriState getPermissionValue(String action, UserContext userContext);
}
