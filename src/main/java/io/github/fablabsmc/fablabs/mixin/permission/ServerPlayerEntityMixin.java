package io.github.fablabsmc.fablabs.mixin.permission;

import io.github.fablabsmc.fablabs.api.permission.v1.PermissionManager;
import io.github.fablabsmc.fablabs.api.permission.v1.actor.PlayerActor;
import io.github.fablabsmc.fablabs.api.permission.v1.context.ContextStack;
import net.fabricmc.fabric.api.util.TriState;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin implements PlayerActor {
	@Override
	public ServerPlayerEntity getEntity() {
		return (ServerPlayerEntity) (Object) this;
	}

	@Override
	public TriState getPermissionValue(ContextStack contextStack, String permission) {
		return PermissionManager.getPermissionValue(this, contextStack, permission);
	}
}
