package com.harrydrummond.is.core;

import com.harrydrummond.is.core.schematic.ClipboardControllerHandler;

/**
 * The entry point for a working implementation of InstantStructures.
 *
 * <p>Handles ClipboardController requests, protection plugin requests,
 * </p>
 *
 * <p>An instance of InstantStructures can be gained using the static method
 * {@link InstantStructures#getInstance()}</p>
 */
public final class InstantStructures {

    private static final InstantStructures instance = new InstantStructures();

    private final ClipboardControllerHandler clipboardControllerHandler = new ClipboardControllerHandler();

    private InstantStructures() {}

    public static InstantStructures getInstance() {
        return instance;
    }

    public ClipboardControllerHandler getClipboardControllerHandler() {
        return clipboardControllerHandler;
    }

}