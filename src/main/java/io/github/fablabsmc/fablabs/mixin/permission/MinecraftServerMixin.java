package io.github.fablabsmc.fablabs.mixin.permission;

import io.github.fablabsmc.fablabs.api.permission.v1.PermissionManager;
import io.github.fablabsmc.fablabs.api.permission.v1.actor.ServerActor;
import io.github.fablabsmc.fablabs.api.permission.v1.ActionType;
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
	public <C> TriState getPermissionValue(ActionType<C> action, C actionContext, UserContext userContext) {
		// FIXME: How to handle permissions checks for server
		return PermissionManager.getPermissionValue(this, action, actionContext, userContext);
	}
}
