package io.github.fablabsmc.fablabs.api.permission.v1.actor;

import java.util.UUID;

/**
 * An actor that represents an offline player.
 */
public interface OfflineActor extends Actor {
	/**
	 * Gets the uuid of the offline player represented by this actor.
	 *
	 * @return the uuid of the offline player
	 */
	UUID getPlayerUuid();
}
