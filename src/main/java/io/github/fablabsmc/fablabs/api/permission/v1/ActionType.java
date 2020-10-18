package io.github.fablabsmc.fablabs.api.permission.v1;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Objects;

import io.github.fablabsmc.fablabs.api.permission.v1.context.UserContext;

import net.minecraft.util.Identifier;

/**
 * Represents a type of action.
 * Unlike other platforms which use a string based key to signify the key of an action, an action type also specifies the type of object which includes specific context about an action.
 *
 * <p>For example, a player actor which is placing a block may have the item and placement block state in the action context.
 * More specific context may be obtained via the {@link UserContext} during the permission check.
 *
 * <p>A {@link PermissionHandler} may choose to ignore the action context but is encouraged to process the action context to make a more informed decision.
 *
 * @param <C> the type of the action context
 */
public final class ActionType<C> {
	private static final Map<Identifier, ActionType<?>> ACTION_TYPES = new IdentityHashMap<>();
	private final Identifier id;
	private final Class<C> contextType;

	public static <C> ActionType<C> of(Identifier id, Class<C> contextType) {
		Objects.requireNonNull(id);
		Objects.requireNonNull(contextType);

		//noinspection unchecked
		return (ActionType<C>) ACTION_TYPES.computeIfAbsent(id, k -> new ActionType<>(k, contextType));
	}

	private ActionType(Identifier id, Class<C> contextType) {
		this.id = id;
		this.contextType = contextType;
	}

	public Identifier getId() {
		return this.id;
	}

	public Class<C> getContextType() {
		return this.contextType;
	}
}
