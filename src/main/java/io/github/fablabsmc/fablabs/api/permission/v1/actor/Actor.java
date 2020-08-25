package io.github.fablabsmc.fablabs.api.permission.v1.actor;

import io.github.fablabsmc.fablabs.api.permission.v1.context.UserContext;

import net.fabricmc.fabric.api.util.TriState;

public interface Actor {
	default boolean hasPermission(UserContext userContext) {
		return this.getPermissionValue(userContext).get();
	}

	TriState getPermissionValue(UserContext userContext);
}
