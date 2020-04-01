package io.github.fablabsmc.fablabs.api.permission.v1;

import io.github.fablabsmc.fablabs.impl.permission.PermissionManagerImpl;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

/**
 * Represents the permission manager used to check if a player has certain permissions.
 */
public interface PermissionManager {
	PermissionManager INSTANCE = PermissionManagerImpl.INSTANCE;

	/**
	 * Registers a permission provider.
	 *
	 * <p>A provider is used to query a permissions implementation to ditermine if a player has a permission.
	 *
	 * @param provider the provider
	 */
	void registerProvider(PermissionProvider provider);

	/**
	 * Checks if a player has a certain permission.
	 *
	 * <p>The permissions implementation controls whether a player has a permission or not, such as permissions which are granted temporarily or due to a context.
	 *
	 * @param player     the player to check
	 * @param permission the permission to check for
	 * @return whether this player has a permission. False if no providers are registered.
	 * @apiNote Most implementations only support permission checks on a {@link net.minecraft.server.network.ServerPlayerEntity}.
	 */
	boolean has(PlayerEntity player, Identifier permission);

	/**
	 * Checks if a player has any listed permission.
	 *
	 * @param player      the player to check
	 * @param permissions the permissions to check
	 * @return true if this player has any of the inputted permissions. False if no providers are registered.
	 * @apiNote Most implementations only support permission checks on a {@link net.minecraft.server.network.ServerPlayerEntity}.
	 */
	default boolean hasAny(PlayerEntity player, Identifier... permissions) {
		for (Identifier permission : permissions) {
			if (this.has(player, permission)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Checks if a player has any listed permission.
	 *
	 * @param player      the player to check
	 * @param permissions the permissions to check
	 * @return true if this player has any of the inputted permissions. False if no providers are registered.
	 * @apiNote Most implementations only support permission checks on a {@link net.minecraft.server.network.ServerPlayerEntity}.
	 */
	default boolean hasAny(PlayerEntity player, Iterable<Identifier> permissions) {
		for (Identifier permission : permissions) {
			if (this.has(player, permission)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Checks if a player has all listed permissions.
	 *
	 * @param player      the player to check
	 * @param permissions the permissions to check
	 * @return true if this player has all of the inputted permissions. False if no providers are registered.
	 * @apiNote Most implementations only support permission checks on a {@link net.minecraft.server.network.ServerPlayerEntity}.
	 */
	default boolean hasAll(PlayerEntity player, Identifier... permissions) {
		for (Identifier permission : permissions) {
			if (!this.has(player, permission)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Checks if a player has all listed permissions.
	 *
	 * @param player      the player to check
	 * @param permissions the permissions to check
	 * @return true if this player has all of the inputted permissions. False if no providers are registered.
	 * @apiNote Most implementations only support permission checks on a {@link net.minecraft.server.network.ServerPlayerEntity}.
	 */
	default boolean hasAll(PlayerEntity player, Iterable<Identifier> permissions) {
		for (Identifier permission : permissions) {
			if (!this.has(player, permission)) {
				return false;
			}
		}

		return true;
	}
}
