package io.github.fablabsmc.fablabs.api.permission.v1;

import io.github.fablabsmc.fablabs.api.permission.v1.subject.Subject;

import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.util.TriState;

/**
 * Represents a provider used to query a permissions implementation to determine if a subject has a permission.
 *
 * <p>Whether the provider accepts permissions which are granted within a context, temporarily or something else is up to the implementor.
 */
public interface PermissionProvider {
	TriState hasPermissionTriState(Subject subject, Identifier permission);

	/**
	 * Gets the id of this provider is registered to.
	 *
	 * @return the id of this provider.
	 */
	Identifier getId();
}
