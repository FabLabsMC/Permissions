package io.github.fablabsmc.fablabs.api.permission.v1.context;

import com.google.common.reflect.TypeToken;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPointer;

import net.fabricmc.loader.api.ModContainer;

/**
 * An enumeration of all default context keys.
 */
@SuppressWarnings("UnstableApiUsage")
public final class ContextKeys {
	/**
	 * A key which corresponds directly to a position an action occurred at.
	 */
	public static final ContextKey<BlockPointer> ACTED_POSITION = create("acted_position", TypeToken.of(BlockPointer.class));

	/**
	 * A key which corresponds directly to a server world an action occurred in.
	 */
	public static final ContextKey<ServerWorld> ACTED_WORLD = create("acted_world", TypeToken.of(ServerWorld.class));

	/**
	 * A key which corresponds directly to a mod that caused an action.
	 */
	public static final ContextKey<ModContainer> ACTED_MOD = create("acted_mod", TypeToken.of(ModContainer.class));

	/**
	 * A key which corresponds directly to a block entity that caused an action.
	 */
	public static final ContextKey<BlockEntity> ACTED_BLOCK_ENTITY = create("acted_block_entity", TypeToken.of(BlockEntity.class));

	private static <V> ContextKey<V> create(String name, TypeToken<V> type) {
		return ContextKey.getOrCreate(new Identifier("fabric", name), type);
	}

	private ContextKeys() {
	}
}
