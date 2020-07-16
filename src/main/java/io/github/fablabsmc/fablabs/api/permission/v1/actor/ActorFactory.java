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

public final class ActorFactory {

	/**
	 * Gets the actor from a Minecraft server.
	 *
	 * @param server the server
	 * @return a server actor
	 */
	public static ServerActor getActor(MinecraftServer server) {
		Objects.requireNonNull(server, "Server cannot be null");
		return PermissionManagerImpl.getActor(server);
	}

	/**
	 * Gets an actor from a player on a server
	 *
	 * @param player the player
	 * @return a player actor
	 */
	public static PlayerActor getActor(ServerPlayerEntity player) {
		Objects.requireNonNull(player, "ServerPlayerEntity cannot be null");
		return PermissionManagerImpl.getActor(player);
	}

	/**
	 * Gets an actor from a player.
	 *
	 * @param player the player
	 * @return a player actor, or empty if the player is an {@link AbstractClientPlayerEntity player on a client}.
	 */
	public static Optional<PlayerActor> getActor(PlayerEntity player) {
		if (player instanceof ServerPlayerEntity) {
			return Optional.of(getActor((ServerPlayerEntity) player));
		}

		return Optional.empty();
	}

	/**
	 * Gets an actor of an offline player from a game profile.
	 *
	 * @param profile the game profile, must have a {@link GameProfile#getId() uuid}
	 * @return an offline actor
	 */
	public static OfflineActor getOfflineActor(GameProfile profile) {
		Objects.requireNonNull(profile, "Profile cannot be null");
		return getOfflineActor(profile.getId());
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

	private ActorFactory() {
	}
}
