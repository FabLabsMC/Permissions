package io.github.fablabsmc.fablabs.test.permissions;

import java.util.UUID;

import io.github.fablabsmc.fablabs.api.permission.v1.actor.Actor;
import io.github.fablabsmc.fablabs.api.permission.v1.actor.ActorFactory;
import io.github.fablabsmc.fablabs.api.permission.v1.context.ContextKeys;
import io.github.fablabsmc.fablabs.api.permission.v1.context.ContextStack;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPointerImpl;

public class Test extends BlockEntity implements Tickable {
	private boolean doingThing;

	public Test(BlockEntityType<?> type) {
		super(type);
	}

	@Override
	public void tick() {
		//noinspection ConstantConditions
		if (this.hasWorld() && !this.getWorld().isClient()) {
			this.serverTick();
		}
	}

	private void serverTick() {
		if (this.doingThing) {
			Actor actor;

			//noinspection ConstantConditions
			final ServerPlayerEntity owner = this.getWorld().getServer().getPlayerManager().getPlayer(this.getOwner());

			if (owner != null) {
				actor = ActorFactory.getActor(owner);
			} else {
				actor = ActorFactory.getOfflineActor(this.getOwner());
			}

			final ContextStack contextStack = ContextStack.create();
			contextStack.pushContext(ContextKeys.ACTED_WORLD, (ServerWorld) this.getWorld());
			contextStack.pushContext(ContextKeys.ACTED_POSITION, this.getActingLocation());
			contextStack.pushContext(ContextKeys.ACTED_BLOCK_ENTITY, this);

			if (actor.hasPermission(contextStack, "mod1.blockentity.test.dothing")) {
				this.doThing();
			}
		}
	}

	private void doThing() {
	}

	public UUID getOwner() {
		return UUID.randomUUID();
	}

	private BlockPointer getActingLocation() {
		return new BlockPointerImpl(this.getWorld(), this.getPos());
	}
}
