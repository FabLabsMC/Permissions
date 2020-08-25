package io.github.fablabsmc.fablabs.impl.permission;

import java.util.UUID;

import io.github.fablabsmc.fablabs.api.permission.v1.actor.OfflineActor;
import io.github.fablabsmc.fablabs.api.permission.v1.context.UserContext;

import net.fabricmc.fabric.api.util.TriState;

final class OfflineActorImpl implements OfflineActor {
	private final UUID uuid;

	OfflineActorImpl(UUID uuid) {
		this.uuid = uuid;
	}

	@Override
	public UUID getPlayerUuid() {
		return this.uuid;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OfflineActorImpl)) return false;

		OfflineActorImpl that = (OfflineActorImpl) o;

		return this.uuid.equals(that.uuid);
	}

	@Override
	public int hashCode() {
		return this.uuid.hashCode();
	}

	@Override
	public TriState getPermissionValue(String action, UserContext userContext) {
		return PermissionManagerImpl.getPermissionValue(this, action, userContext);
	}
}
