package com.harrydrummond.is.core;

import com.harrydrummond.is.core.data.PlatformController;
import com.harrydrummond.is.core.protection.octree.OctreeHandler;
import com.harrydrummond.is.core.schematic.ClipboardControllerHandler;

/**
 * The entry point for a working implementation of InstantStructures.
 *
 * <p>Handles ClipboardController requests, protection plugin requests,
 * </p>
 *
 * <p>An instance of the Singleton InstantStructures can be gained using the static method
 * {@link InstantStructures#getInstance()}</p>
 */
public final class InstantStructures {

    private static final InstantStructures INSTANCE = new InstantStructures();

    private final ClipboardControllerHandler clipboardControllerHandler = new ClipboardControllerHandler();
    private final OctreeHandler octreeHandler = new OctreeHandler();
    private final PlatformController platformController = new PlatformController();

    private InstantStructures() { }

    public static InstantStructures getInstance() {
        return INSTANCE;
    }

    public PlatformController getPlatformController() {
        return platformController;
    }

    public ClipboardControllerHandler getClipboardControllerHandler() {
        return clipboardControllerHandler;
    }

    public OctreeHandler getOctreeHandler() {
        return octreeHandler;
    }
}