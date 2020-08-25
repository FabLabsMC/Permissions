package io.github.fablabsmc.fablabs.mixin.permission;

import io.github.fablabsmc.fablabs.api.permission.v1.PermissionManager;
import io.github.fablabsmc.fablabs.api.permission.v1.actor.EntityActor;
import io.github.fablabsmc.fablabs.api.permission.v1.context.UserContext;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.entity.Entity;

import net.fabricmc.fabric.api.util.TriState;

@Mixin(Entity.class)
abstract class EntityMixin implements EntityActor {
	@Override
	public Entity toEntity() {
		return (Entity) (Object) this;
	}

	@Override
	public TriState getPermissionValue(String action, UserContext userContext) {
		return PermissionManager.getPermissionValue(this, action, userContext);
	}
}
