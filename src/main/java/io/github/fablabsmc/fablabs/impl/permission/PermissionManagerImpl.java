package io.github.fablabsmc.fablabs.impl.permission;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashSet;
import java.util.Set;

import io.github.fablabsmc.fablabs.api.permission.v1.PermissionManager;
import io.github.fablabsmc.fablabs.api.permission.v1.PermissionProvider;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class PermissionManagerImpl implements PermissionManager {
	public static final PermissionManager INSTANCE = new PermissionManagerImpl();

	private final Set<PermissionProvider> providers = new HashSet<>();

	private PermissionManagerImpl() { }

	@Override
	public void registerProvider(PermissionProvider provider) {
		checkNotNull(provider, "The provider cannot be null");

		this.providers.add(provider);
	}

	@Override
	public boolean has(PlayerEntity player, Identifier permission) {
		checkNotNull(player, "Player cannot be null");
		checkNotNull(permission, "Permission cannot be null");

		if (this.providers.isEmpty()) {
			return false;
		}

		for (PermissionProvider permissionProvider : this.providers) {
			if (permissionProvider.has(player, permission)) {
				return true;
			}
		}

		return false;
	}
}
