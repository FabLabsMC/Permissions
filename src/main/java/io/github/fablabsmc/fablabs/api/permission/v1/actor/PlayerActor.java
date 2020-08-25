package io.github.fablabsmc.fablabs.api.permission.v1.actor;

import net.minecraft.server.network.ServerPlayerEntity;

/**
 * Represents an actor that is an online player.
 */
// TODO: Fake player actor?
public interface PlayerActor extends EntityActor {
	@Override
	ServerPlayerEntity toEntity();
}
