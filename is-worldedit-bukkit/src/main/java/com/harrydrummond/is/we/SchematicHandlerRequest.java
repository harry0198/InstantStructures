package com.harrydrummond.is.we;

import com.harrydrummond.is.core.annotations.HandlerRequest;
import com.harrydrummond.is.core.protection.Point3D;
import com.harrydrummond.is.core.schematic.ClipboardController;
import com.harrydrummond.is.core.schematic.Session;
import com.harrydrummond.is.core.schematic.SessionResult;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@HandlerRequest(priority = 5)
public class SchematicHandlerRequest implements ClipboardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchematicHandlerRequest.class);
    private static final Map<UUID, EditSession> sessionMap = new HashMap<>();

    @Override
    public ClipboardController request() {
        return this;
    }

    @Override
    public Session paste(final Session session, final Point3D point, final String worldName) {
        session.setSessionResult(SessionResult.WAITING);

        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            LOGGER.error("Tried to load schematic in world ({}) but world does not exist!", worldName);
            session.setSessionResult(SessionResult.ERROR);
            return session;
        }

        Clipboard clipboard;

        ClipboardFormat format = ClipboardFormats.findByFile(session.getFile());
        if (format == null) {
            LOGGER.error("Could not format file {}", session.getFile().getName());
            session.setSessionResult(SessionResult.ERROR);
            return session;
        }
        try (ClipboardReader reader = format.getReader(new FileInputStream(session.getFile()))) {
            clipboard = reader.read();
        } catch (IOException io) {
            LOGGER.error("Failed to read file {} to clipboard", session.getFile().getName(), io);
            session.setSessionResult(SessionResult.ERROR);
            return session;
        }

        try (EditSession editSession = WorldEdit.getInstance().newEditSession(new BukkitWorld(world))) {
            Operation operation = new ClipboardHolder(clipboard)
                    .createPaste(editSession)
                    .to(BlockVector3.at(point.x, point.y, point.z))
                    .ignoreAirBlocks(true)
                    .build();
            session.setSessionResult(SessionResult.LOADED);
            Operations.complete(operation);
            sessionMap.put(session.getId(), editSession);
        } catch (WorldEditException e) {
            LOGGER.error("Operation could not be completed!",e);
            session.setSessionResult(SessionResult.ERROR);
            return session;
        }

        session.setSessionResult(SessionResult.PASTED);
        return session;
    }

    @Override
    public Session undo(Session session, String worldName) {
        session.setSessionResult(SessionResult.WAITING);

        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            LOGGER.error("Tried to load in world ({}) but world does not exist!", worldName);
            session.setSessionResult(SessionResult.ERROR);
            return session;
        }
        EditSession editSession = WorldEdit.getInstance().newEditSession(new BukkitWorld(world));
        editSession.undo(sessionMap.get(session.getId()));
        session.setSessionResult(SessionResult.UNDONE);
        return session;
    }
}