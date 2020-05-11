package io.github.fablabsmc.fablabs.api.permission.v1.subject;

import io.github.fablabsmc.fablabs.impl.permission.PermissionContextImpl;

public interface PermissionContext {
	static PermissionContext none() {
		return PermissionContext.builder().build();
	}

	static PermissionContext.Builder builder() {
		return new Builder();
	}

	final class Builder {
		private Builder() {
		}

		public PermissionContext build() {
			return new PermissionContextImpl();
		}
	}
}
