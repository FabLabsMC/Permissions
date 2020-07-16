package io.github.fablabsmc.fablabs.api.permission.v1.actor;

import java.util.UUID;

/**
 * An actor that represents an offline player.
 */
public interface OfflineActor extends Actor {
	UUID getPlayerUuid();
}
