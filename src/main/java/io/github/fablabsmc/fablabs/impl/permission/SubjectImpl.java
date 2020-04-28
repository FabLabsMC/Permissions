package io.github.fablabsmc.fablabs.impl.permission;

import io.github.fablabsmc.fablabs.api.permission.v1.subject.Subject;

import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.util.TriState;

final class SubjectImpl implements Subject {
	private final PermissionManagerImpl permissionManager;
	private Object represented;

	SubjectImpl(PermissionManagerImpl permissionManager, Object represented) {
		this.permissionManager = permissionManager;
		this.represented = represented;
	}

	@Override
	public TriState getPermissionValue(Identifier permission) {
		return this.permissionManager.getPermissionValue(this, permission);
	}

	@Override
	public Object getRepresentedObject() {
		return this.represented;
	}
}
