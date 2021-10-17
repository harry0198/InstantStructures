package com.harrydrummond.is.core.schematic;

import com.harrydrummond.is.core.annotations.PluginRequestForm;
import com.harrydrummond.is.core.protection.Point3D;

public interface ClipboardController extends PluginRequestForm {

    /**
     * Pastes schematic from session at point and world
     * @param session {@link Session} session with file to paste
     * @param point {@link Point3D} to originate paste from
     * @param world WorldName to paste schematic on
     * @return Session object
     */
    Session paste(final Session session, final Point3D point, final String world);

    /**
     * Undo session schematic
     * @param session {@link Session} to undo
     * @param worldName WorldName schematic originated from
     * @return Session object
     */
    Session undo(final Session session, final String worldName);
}