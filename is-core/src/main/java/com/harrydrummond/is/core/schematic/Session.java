package com.harrydrummond.is.core.schematic;

import java.io.File;
import java.io.Serializable;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

public class Session implements Serializable {

    private final UUID id;
    private final File file;
    private SessionResult sessionResult = SessionResult.IDLE;
    private Direction direction = Direction.NORTH;

    public Session(final File file) {
        this.id = UUID.randomUUID();
        this.file = file;
    }

    public void setDirection(Direction direction) {
        this.direction = checkNotNull(direction);
    }

    public void rotateDirection() {
        switch (direction) {
            case NORTH -> setDirection(Direction.EAST);
            case EAST -> setDirection(Direction.SOUTH);
            case SOUTH -> setDirection(Direction.WEST);
            case WEST -> setDirection(Direction.NORTH);
        }
    }

    public void setSessionResult(SessionResult sessionResult) {
        this.sessionResult = checkNotNull(sessionResult);
    }

    public Direction getDirection() {
        return direction;
    }

    public UUID getId() {
        return id;
    }

    public File getFile() {
        return file;
    }

    public SessionResult getSessionResult() {
        return sessionResult;
    }
}