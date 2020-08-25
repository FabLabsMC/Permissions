package io.github.fablabsmc.fablabs.mixin.permission;

import io.github.fablabsmc.fablabs.api.permission.v1.PermissionManager;
import io.github.fablabsmc.fablabs.api.permission.v1.actor.ServerActor;
import io.github.fablabsmc.fablabs.api.permission.v1.context.UserContext;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.server.MinecraftServer;

import net.fabricmc.fabric.api.util.TriState;

@Mixin(MinecraftServer.class)
abstract class MinecraftServerMixin implements ServerActor {
	@Override
	public MinecraftServer toServer() {
		return (MinecraftServer) (Object) this;
	}

	@Override
	public TriState getPermissionValue(String action, UserContext userContext) {
		// FIXME: How to handle permissions checks for server
		return PermissionManager.getPermissionValue(this, action, userContext);
	}
}
