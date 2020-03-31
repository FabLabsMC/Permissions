package io.github.fablabsmc.fablabs.impl.permission;

import io.github.fablabsmc.fablabs.api.permission.v1.PermissionEngine;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public final class DummyPermissionEngine implements PermissionEngine {
	@Override
	public boolean has(PlayerEntity player, Identifier permission) {
		return false;
	}

	@Override
	public boolean hasAny(PlayerEntity player, Identifier... permissions) {
		return false;
	}

	@Override
	public boolean hasAny(PlayerEntity player, Iterable<Identifier> permissions) {
		return false;
	}

	@Override
	public boolean hasAll(PlayerEntity player, Identifier... permissions) {
		return false;
	}

	@Override
	public boolean hasAll(PlayerEntity player, Iterable<Identifier> permissions) {
		return false;
	}

	@Override
	public String getName() {
		return "dummy";
	}

	@Override
	public boolean isDummy() {
		return true;
	}
}
