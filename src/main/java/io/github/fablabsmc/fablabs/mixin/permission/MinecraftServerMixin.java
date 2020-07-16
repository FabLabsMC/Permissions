package io.github.fablabsmc.fablabs.mixin.permission;

import io.github.fablabsmc.fablabs.api.permission.v1.actor.ServerActor;
import io.github.fablabsmc.fablabs.api.permission.v1.context.ContextStack;
import net.fabricmc.fabric.api.util.TriState;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.server.MinecraftServer;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin implements ServerActor {
	@Override
	public MinecraftServer getServer() {
		return (MinecraftServer) (Object) this;
	}

	@Override
	public TriState getPermissionValue(ContextStack contextStack, String permission) {
		return TriState.TRUE; // Server should always be able to act
	}
}
