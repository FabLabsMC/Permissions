package io.github.fablabsmc.fablabs.api.permission.v1.actor;

import io.github.fablabsmc.fablabs.api.permission.v1.context.ContextStack;
import net.fabricmc.fabric.api.util.TriState;

public interface Actor {
	default boolean hasPermission(ContextStack contextStack, String permission) {
		return this.getPermissionValue(contextStack, permission).get();
	}

	default boolean hasAny(ContextStack contextStack, Iterable<String> permissions) {
		for (String permission : permissions) {
			if (this.hasPermission(contextStack, permission)) return true;
		}

		return false;
	}

	default boolean hasAll(ContextStack contextStack, Iterable<String> permissions) {
		for (String permission : permissions) {
			if (!this.hasPermission(contextStack, permission)) return false;
		}

		return true;
	}

	TriState getPermissionValue(ContextStack contextStack, String permission);
}
