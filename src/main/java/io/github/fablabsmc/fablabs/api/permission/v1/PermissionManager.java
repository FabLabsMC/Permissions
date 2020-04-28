package io.github.fablabsmc.fablabs.api.permission.v1;

import io.github.fablabsmc.fablabs.api.permission.v1.subject.Subject;
import io.github.fablabsmc.fablabs.impl.permission.PermissionManagerImpl;

import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.util.TriState;

/**
 * Represents the permission manager used to delegate checks if a subject has certain permissions to {@link PermissionProvider}s.
 */
public interface PermissionManager {
	PermissionManager INSTANCE = PermissionManagerImpl.INSTANCE;

	/**
	 * Gets a subject which is mapped to an object.
	 *
	 * @param represented the object mapped by this subject.
	 * @return a subject.
	 */
	Subject asSubject(Object represented);

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
	TriState getPermissionValue(Subject subject, Identifier permission);
}
