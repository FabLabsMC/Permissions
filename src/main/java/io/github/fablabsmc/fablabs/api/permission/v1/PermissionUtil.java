package io.github.fablabsmc.fablabs.api.permission.v1;

import io.github.fablabsmc.fablabs.impl.permission.DummyPermissionEngine;

import static com.google.common.base.Preconditions.checkNotNull;

public final class PermissionUtil {
	private PermissionUtil() {
	}

	private static PermissionEngine activeEngine = new DummyPermissionEngine();

	public static PermissionEngine getActiveEngine() {
		return PermissionUtil.activeEngine;
	}

	public static void setEngine(PermissionEngine engine) {
	    checkNotNull(engine, "Permission engine cannot be null");
        PermissionUtil.activeEngine = engine;
    }
}
