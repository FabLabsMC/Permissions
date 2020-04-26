package io.github.fablabsmc.fablabs.api.permission.v1.subject;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.util.TriState;

/**
 * A subject is an object which can be queried for permissions checks.
 *
 * <p>All subjects refer to an existing object.
 * Typically in an implementation, this could be a {@link ServerPlayerEntity player}, a group or the {@link MinecraftServer server}.
 * There is no restriction whether any object can be a subject or not.
 */
public interface Subject {
	/**
	 * Checks if a subject has any listed permission.
	 *
	 * @param permissions the permissions to check
	 * @return true if this subject has any of the inputted permissions. False if no providers are registered.
	 * @apiNote Most implementations expect the {@link Identifier} to be in the format of {@code mymod:permission.child}, though you should consult your implementation to determine how it is handled.
	 */
	default boolean hasAny(Iterable<Identifier> permissions) {
		for (Identifier permission : permissions) {
			if (this.hasPermission(permission)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Checks if a subject has all listed permissions.
	 *
	 * @param permissions the permissions to check
	 * @return true if this subject has all of the inputted permissions. False if no providers are registered.
	 * @apiNote Most implementations expect the {@link Identifier} to be in the format of {@code mymod:permission.child}, though you should consult your implementation to determine how it is handled.
	 */
	default boolean hasAll(Iterable<Identifier> permissions) {
		for (Identifier permission : permissions) {
			if (!this.hasPermission(permission)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Checks if the subject has a permission.
	 *
	 * @param permission the permission
	 * @return whether this subject has the permission.
	 * @apiNote Most implementations expect the {@link Identifier} to be in the format of {@code mymod:permission.child}, though you should consult your implementation to determine how it is handled.
	 */
	default boolean hasPermission(Identifier permission) {
		return this.hasPermissionTriState(permission).get();
	}

	/**
	 * Checks if the subject has a permission.
	 *
	 * @param permission the permission
	 * @return a {@link TriState} representing whether this subject has the permission.
	 * @apiNote In many implementations, {@link TriState#DEFAULT} defines that a subject does not have this permission set.
	 */
	TriState hasPermissionTriState(Identifier permission);

	/**
	 * Gets the object this subject is mapped to.
	 * @return the game object.
	 */
	Object getRepresentedObject();
}
