package io.github.fablabsmc.fablabs.mixin.permission;

import io.github.fablabsmc.fablabs.api.permission.v1.actor.PlayerActor;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(ServerPlayerEntity.class)
abstract class ServerPlayerEntityMixin extends EntityMixin implements PlayerActor {
	@Override
	public ServerPlayerEntity toEntity() {
		return (ServerPlayerEntity) (Object) this;
	}
}
