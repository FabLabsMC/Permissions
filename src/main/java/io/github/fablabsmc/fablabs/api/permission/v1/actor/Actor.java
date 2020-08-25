package io.github.fablabsmc.fablabs.api.permission.v1.actor;

import io.github.fablabsmc.fablabs.api.permission.v1.context.UserContext;

import net.fabricmc.fabric.api.util.TriState;

public interface Actor {
	default boolean hasPermission(UserContext userContext) {
		return false;//return this.getPermissionValue(permission).get();
	}

	TriState getPermissionValue(UserContext userContext);
}
