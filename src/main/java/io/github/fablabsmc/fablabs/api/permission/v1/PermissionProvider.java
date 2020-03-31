package io.github.fablabsmc.fablabs.api.permission.v1;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

/**
 * Represents a provider used to query a permissions implementation to ditermine if a player has a permission.
 *
 * <p>Whether the provider accepts permissions which are granted within a context, temporarily or something else is up to the implementor.
 */
public interface PermissionProvider {
	/**
	 * Checks if a player has a certain permission.
	 *
	 * <p>The permissions implementation controls whether a player has a permission or not, such as permissions which are granted temporarily or due to a context.
	 *
	 * <p>It is expected an implementation will return the result of a permission check immediately, or return false if the provider is not active or the player has not been loaded by the implementation yet.
	 *
	 * @apiNote Most implementations only support permission checks on a {@link ServerPlayerEntity}.
	 *
	 * @param player     the player to check
	 * @param permission the permission to check for
	 * @return true if this player has the permission according to the implementor, otherwise false.
	 */
	boolean has(PlayerEntity player, Identifier permission);

	/**
	 * Gets the id of this provider is registered to.
	 *
	 * @return the id of this provider.
	 */
	Identifier getId();
}
