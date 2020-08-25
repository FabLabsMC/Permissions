package io.github.fablabsmc.fablabs.api.permission.v1;

import io.github.fablabsmc.fablabs.api.permission.v1.actor.Actor;
import io.github.fablabsmc.fablabs.api.permission.v1.context.UserContext;

import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.util.TriState;

public interface PermissionHandler {
	TriState getPermissionValue(Actor actor, UserContext userContext);

	Identifier getId();
}
