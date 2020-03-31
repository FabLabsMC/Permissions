package io.github.fablabsmc.fablabs.impl.permission;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.IdentityHashMap;
import java.util.Map;

import io.github.fablabsmc.fablabs.api.permission.v1.PermissionManager;
import io.github.fablabsmc.fablabs.api.permission.v1.PermissionProvider;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class PermissionManagerImpl implements PermissionManager {
	public static final PermissionManager INSTANCE = new PermissionManagerImpl();

	private final Map<Identifier, PermissionProvider> providers = new IdentityHashMap<>();

	private PermissionManagerImpl() { }

	@Override
	public void registerProvider(Identifier id, PermissionProvider provider) {
		checkNotNull(id, "The id cannot be null");
		checkNotNull(provider, "The provider cannot be null");

		if (this.providers.get(id) != null) {
			// TODO Throw or warn in log about the provider being overwritten?
		}

		this.providers.put(id, provider);
	}

	@Override
	public boolean has(PlayerEntity player, Identifier permission) {
		checkNotNull(player, "Player cannot be null");
		checkNotNull(permission, "Permission cannot be null");

		if (this.providers.isEmpty()) {
			return false;
		}

		for (PermissionProvider permissionProvider : this.providers.values()) {
			if (permissionProvider.has(player, permission)) {
				return true;
			}
		}

		return false;
	}
}
