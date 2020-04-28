package io.github.fablabsmc.fablabs.impl.permission;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashSet;
import java.util.Set;

import io.github.fablabsmc.fablabs.api.permission.v1.PermissionManager;
import io.github.fablabsmc.fablabs.api.permission.v1.PermissionProvider;
import io.github.fablabsmc.fablabs.api.permission.v1.subject.Subject;

import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.util.TriState;

public class PermissionManagerImpl implements PermissionManager {
	public static final PermissionManager INSTANCE = new PermissionManagerImpl();

	private final Set<PermissionProvider> providers = new HashSet<>();

	private PermissionManagerImpl() {
	}

	@Override
	public Subject asSubject(Object represented) {
		return new SubjectImpl(this, represented);
	}

	@Override
	public void registerProvider(PermissionProvider provider) {
		checkNotNull(provider, "The provider cannot be null");

		this.providers.add(provider);
	}

	@Override
	public TriState getPermissionValue(Subject subject, Identifier permission) {
		checkNotNull(subject, "Subject cannot be null");
		checkNotNull(permission, "Permission cannot be null");

		if (this.providers.isEmpty()) {
			return TriState.DEFAULT;
		}

		for (PermissionProvider permissionProvider : this.providers) {
			TriState triState = permissionProvider.getPermissionValue(subject, permission);

			if (triState.get()) {
				return triState;
			}
		}

		return TriState.FALSE;
	}
}
