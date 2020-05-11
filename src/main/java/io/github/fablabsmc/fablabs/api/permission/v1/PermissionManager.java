package io.github.fablabsmc.fablabs.api.permission.v1;

import io.github.fablabsmc.fablabs.api.permission.v1.subject.PermissionSubject;
import io.github.fablabsmc.fablabs.impl.permission.PermissionManagerImpl;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.util.TriState;

/**
 * Represents the permission manager used to delegate checks if a subject has certain permissions to {@link PermissionProvider}s.
 */
public interface PermissionManager {
	PermissionManager INSTANCE = PermissionManagerImpl.INSTANCE;

	/**
	 * Gets the subject that represents a player.
	 *
	 * @param playerEntity the player entity.
	 * @return the player's subject, if the player is a ServerPlayerEntity. Otherwise an empty subject.
	 * @apiNote Implementations only support querying a subject from a ServerPlayerEntity. This method exists as a helper.
	 */
	default PermissionSubject getSubject(PlayerEntity playerEntity) {
		if (playerEntity instanceof ServerPlayerEntity) {
			return this.getSubject((ServerPlayerEntity) playerEntity);
		}

		return PermissionSubject.EMPTY;
	}

	/**
	 * Gets the subject that represents a player.
	 *
	 * @param playerEntity the server player entity
	 * @return the player's subject
	 */
	PermissionSubject getSubject(ServerPlayerEntity playerEntity);

	/**
	 * Gets the subject that represents the server.
	 *
	 * @param server the server to act as the subject
	 * @return the server's subject
	 */
	PermissionSubject getSubject(MinecraftServer server);

	/**
	 * Attempts to get the most relevant subject which represents this object.
	 *
	 * <p>If the {@code object} implements {@link PermissionSubject.Provider}, the subject will be be returned from the provider.
	 *
	 * <p>If there are no relevant subjects, an empty subject will be returned.
	 *
	 * @param object the object to find the subject for
	 * @return a subject.
	 */
	PermissionSubject getSubject(Object object);

	/**
	 * Registers a permission provider.
	 *
	 * <p>A provider is used to query a permissions implementation to ditermine if a subject has a permission.
	 *
	 * @param provider the provider
	 */
	void registerProvider(PermissionProvider provider);

	/**
	 * Checks if a subject has a certain permission.
	 *
	 * <p>The permissions implementation controls whether a player has a permission or not, such as permissions which are granted temporarily or due to a context.
	 *
	 * @param subject     the subject to check
	 * @param permission the permission to check for
	 * @return whether this player has a permission. False if no providers are registered.
	 * @apiNote Most implementations expect the {@link Identifier} to be in the format of {@code mymod:permission.child}, though you should consult your implementation to determine how it is handled.
	 */
	TriState getPermissionValue(PermissionSubject subject, Identifier permission);
}
