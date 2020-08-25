package io.github.fablabsmc.fablabs.api.permission.v1.actor;

import net.minecraft.entity.Entity;

public interface EntityActor extends Actor {
	Entity toEntity();
}
