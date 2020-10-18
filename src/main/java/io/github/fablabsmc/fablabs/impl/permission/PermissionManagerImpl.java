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
import io.github.fablabsmc.fablabs.api.permission.v1.ActionType;
import io.github.fablabsmc.fablabs.api.permission.v1.context.UserContext;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import net.fabricmc.fabric.api.util.TriState;

public final class PermissionManagerImpl {
	private static final Set<PermissionHandler> HANDLERS = new HashSet<>();

	private PermissionManagerImpl() {
	}

	public static void registerHandler(PermissionHandler handler) {
		// Verify we are not replacing handlers
		for (PermissionHandler permissionHandler : HANDLERS) {
			if (permissionHandler.getId().equals(handler.getId())) {
				throw new IllegalArgumentException((String.format("Tried to register a permissions handler of same id %s", handler.getId())));
			}
		}

		HANDLERS.add(handler);
	}

	public static <C> TriState getPermissionValue(Actor actor, ActionType<C> action, C actionContext, UserContext userContext) {
		// TODO: Should failure take precedence over success?
		//  i5: Leaning towards yes since you may want to stop a break from blocking regardless of whether a mod wants to move forward.
		Objects.requireNonNull(actor, "Subject cannot be null");
		Objects.requireNonNull(userContext, "User context cannot be null");

		if (HANDLERS.isEmpty()) {
			return TriState.DEFAULT;
		}

		for (PermissionHandler handler : HANDLERS) {
			final TriState triState = handler.getPermissionValue(actor, action, actionContext, userContext);

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
