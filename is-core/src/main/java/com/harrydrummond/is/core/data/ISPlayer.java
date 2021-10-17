package com.harrydrummond.is.core.data;

import java.util.UUID;

public class ISPlayer {

    private final UUID uuid;

    public ISPlayer(final UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Gets UUID of ISPlayer (Player) object. Usernames not supported.
     * @return UUID of ISPlayer (Player) object.
     */
    public UUID getUuid() {
        return uuid;
    }
}