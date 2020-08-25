package io.github.fablabsmc.fablabs.api.permission.v1.actor;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.mojang.authlib.GameProfile;
import io.github.fablabsmc.fablabs.impl.permission.PermissionManagerImpl;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

/**
 * A utility class which provides helper methods to get actors from objects present on a typical Minecraft server.
 */
public final class Actors {
	/**
	 * Gets the actor from a Minecraft server.
	 *
	 * @param server the server
	 * @return a server actor
	 */
	public static ServerActor getServerActor(MinecraftServer server) {
		Objects.requireNonNull(server, "Server cannot be null");
		return PermissionManagerImpl.getActor(server);
	}

	/**
	 * Gets an actor from a player on a server
	 *
	 * @param player the player
	 * @return a player actor
	 */
	public static PlayerActor getPlayerActor(ServerPlayerEntity player) {
		Objects.requireNonNull(player, "ServerPlayerEntity cannot be null");
		return PermissionManagerImpl.getActor(player);
	}

	/**
	 * Gets an actor of an offline player from a game profile.
	 *
	 * @param profile the game profile, must have a {@link GameProfile#getId() uuid}
	 * @return an offline actor
	 */
	public static OfflineActor getOfflineActor(GameProfile profile) {
		Objects.requireNonNull(profile, "Profile cannot be null");
		return Actors.getOfflineActor(profile.getId());
	}

	/**
	 * Gets an actor of an offline player.
	 *
	 * @param uuid the uuid of the player
	 * @return an offline actor
	 */
	public static OfflineActor getOfflineActor(UUID uuid) {
		Objects.requireNonNull(uuid, "UUID cannot be null");
		return PermissionManagerImpl.getOfflineActor(uuid);
	}

	private Actors() {
	}
}
