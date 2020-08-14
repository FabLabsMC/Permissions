package io.github.fablabsmc.fablabs.api.permission.v1.actor;

import io.github.fablabsmc.fablabs.api.permission.v1.TriState;

public interface Actor {
	default boolean hasPermission(String permission) {
		return false;//return this.getPermissionValue(permission).get();
	}

	default boolean hasAny(Iterable<String> permissions) {
		for (String permission : permissions) {
			if (this.hasPermission(permission)) return true;
		}

		return false;
	}

	default boolean hasAll(Iterable<String> permissions) {
		for (String permission : permissions) {
			if (!this.hasPermission(permission)) return false;
		}

		return true;
	}

	TriState getPermissionValue(String permission);
}
