package io.github.fablabsmc.fablabs.api.permission.v1;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

/**
 * Represents a wrapper to check if a player has certain permission(s) based on an implementation.
 */
public interface PermissionEngine {
	/**
	 * Checks if a player has a certain permission.
	 *
	 * <p>The permissions implementation controls whether a player has a permission or not, such as permissions which are granted temporarily or due to a context.
	 *
	 * @param player     the player to check
	 * @param permission the permission to check for
	 * @return whether this player has a permission
	 * @apiNote Most implementations only support permission checks on a {@link net.minecraft.server.network.ServerPlayerEntity}.
	 */
	boolean has(PlayerEntity player, Identifier permission);

	/**
	 * Checks if a player has any listed permission.
	 *
	 * @param player      the player to check
	 * @param permissions the permissions to check
	 * @return true if this player has any of the inputted permissions.
	 * @apiNote Most implementations only support permission checks on a {@link net.minecraft.server.network.ServerPlayerEntity}.
	 */
	boolean hasAny(PlayerEntity player, Identifier... permissions);

	/**
	 * Checks if a player has any listed permission.
	 *
	 * @param player      the player to check
	 * @param permissions the permissions to check
	 * @return true if this player has any of the inputted permissions.
	 * @apiNote Most implementations only support permission checks on a {@link net.minecraft.server.network.ServerPlayerEntity}.
	 */
	boolean hasAny(PlayerEntity player, Iterable<Identifier> permissions);

	/**
	 * Checks if a player has all listed permissions.
	 *
	 * @param player      the player to check
	 * @param permissions the permissions to check
	 * @return true if this player has all of the inputted permissions.
	 * @apiNote Most implementations only support permission checks on a {@link net.minecraft.server.network.ServerPlayerEntity}.
	 */
	boolean hasAll(PlayerEntity player, Identifier... permissions);

	/**
	 * Checks if a player has all listed permissions.
	 *
	 * @param player      the player to check
	 * @param permissions the permissions to check
	 * @return true if this player has all of the inputted permissions.
	 * @apiNote Most implementations only support permission checks on a {@link net.minecraft.server.network.ServerPlayerEntity}.
	 */
	boolean hasAll(PlayerEntity player, Iterable<Identifier> permissions);

	/**
	 * Gets the name of this permissions implementation.
	 *
	 * @return the name of this implementation
	 */
	String getName();

	/**
	 * Whether this engine is a dummy implementation.
	 *
	 * @return true if this is a dummy implementation
	 * @apiNote If there is no implementation, this would return true.
	 */
	default boolean isDummy() {
		return false;
	}
}
