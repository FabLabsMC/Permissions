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
import io.github.fablabsmc.fablabs.api.permission.v1.context.UserContext;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import net.fabricmc.fabric.api.util.TriState;

public class PermissionManagerImpl {
	private static final Set<PermissionHandler> HANDLERS = new HashSet<>();

	private PermissionManagerImpl() {
	}

	public static void registerHandler(PermissionHandler handler) {
		if (!HANDLERS.add(handler)) {
			throw new IllegalArgumentException((String.format("Tried to register a permissions handler of same id %s", handler.getId())));
		}
	}

	public static TriState getPermissionValue(Actor actor, String action, UserContext userContext) {
		Objects.requireNonNull(actor, "Subject cannot be null");
		Objects.requireNonNull(userContext, "User context cannot be null");

		if (HANDLERS.isEmpty()) {
			return TriState.DEFAULT;
		}

		for (PermissionHandler handler : HANDLERS) {
			TriState triState = handler.getPermissionValue(actor, action, userContext);

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

}
