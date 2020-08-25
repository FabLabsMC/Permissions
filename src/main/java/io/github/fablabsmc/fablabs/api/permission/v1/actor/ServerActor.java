package io.github.fablabsmc.fablabs.api.permission.v1.actor;

import net.minecraft.server.MinecraftServer;

/**
 * An actor that represents a Minecraft server.
 */
public interface ServerActor extends Actor {
	MinecraftServer toServer();
}
