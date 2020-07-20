package io.github.fablabsmc.fablabs.impl.permission;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import io.github.fablabsmc.fablabs.api.permission.v1.PermissionHandler;
import io.github.fablabsmc.fablabs.api.permission.v1.actor.Actor;
import io.github.fablabsmc.fablabs.api.permission.v1.actor.OfflineActor;
import io.github.fablabsmc.fablabs.api.permission.v1.actor.PlayerActor;
import io.github.fablabsmc.fablabs.api.permission.v1.actor.ServerActor;
import io.github.fablabsmc.fablabs.api.permission.v1.context.ContextStack;
import net.fabricmc.fabric.api.util.TriState;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

public class PermissionManagerImpl {
	private static final Set<PermissionHandler> HANDLERS = new HashSet<>();

	private PermissionManagerImpl() {
	}

	public static void registerHandler(PermissionHandler handler) {
		if (!HANDLERS.add(handler)) {
			throw new IllegalArgumentException((String.format("Tried to register a permissions handler of same id %s", handler.getId())));
		}
	}

	public static TriState getPermissionValue(Actor actor, ContextStack contextStack, String permission) {
		Objects.requireNonNull(actor, "Subject cannot be null");
		Objects.requireNonNull(permission, "Permission cannot be null");

		if (HANDLERS.isEmpty()) {
			return TriState.DEFAULT;
		}

		for (PermissionHandler handler : HANDLERS) {
			TriState triState = handler.getPermissionValue(actor, contextStack, permission);

			if (triState.get()) {
				return triState;
			}
		}

		return TriState.DEFAULT;
	}

	public static PlayerActor getActor(ServerPlayerEntity player) {
		return (PlayerActor) player;
	}

	public static ServerActor getActor(MinecraftServer server) {
		return (ServerActor) server;
	}

	public static OfflineActor getOfflineActor(UUID uuid) {
		return new OfflineActorImpl(uuid);
	}

	static class OfflineActorImpl implements OfflineActor {
		private final UUID uuid;

		private OfflineActorImpl(UUID uuid) {
			this.uuid = uuid;
		}

		@Override
		public UUID getPlayerUuid() {
			return this.uuid;
		}

		@Override
		public TriState getPermissionValue(ContextStack contextStack, String permission) {
			return PermissionManagerImpl.getPermissionValue(this, contextStack, permission);
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
	}
}
