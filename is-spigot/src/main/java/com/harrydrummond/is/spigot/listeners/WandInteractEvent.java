package com.harrydrummond.is.spigot.listeners;

import com.harrydrummond.is.core.InstantStructures;
import com.harrydrummond.is.core.protection.Point3D;
import com.harrydrummond.is.core.schematic.ClipboardController;
import com.harrydrummond.is.core.schematic.Session;
import com.harrydrummond.is.core.schematic.SessionResult;
import com.harrydrummond.is.spigot.util.MetaDataUtil;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class WandInteractEvent implements Listener {

    private final InstantStructures is;

    public WandInteractEvent(final InstantStructures is) {
        this.is = is;
    }

    @EventHandler
    public void onWandInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item == null || event.getClickedBlock() == null) return;
        Session session = MetaDataUtil.getSessionFromMeta(item);

        if (session == null) return;

        ClipboardController clipboardController = is.getClipboardControllerHandler().requestPrimaryClipboardController();

        if (clipboardController == null) {
            //send message
            return;
        }

        Location location = event.getClickedBlock().getLocation();
        if (location.getWorld() == null) {
            //error LOG
            return;
        }

        switch (event.getAction()) {
            case LEFT_CLICK_AIR:
            case LEFT_CLICK_BLOCK:
                session.rotateDirection();
                //todo message
                break;
            case RIGHT_CLICK_BLOCK:
                // TODO check if clicked on pasted block, if wasn't run following else undo
                if (session.getSessionResult().equals(SessionResult.PASTED)) {
                    // send message already pasted
                    return;
                }
                paste(session,location,clipboardController);
                break;
            default:
                return;
        }

        MetaDataUtil.setSessionOnItem(item, session);

        if (session.getSessionResult().equals(SessionResult.ERROR)) {
            // failede to do xyz
            return;
        } else if (session.getSessionResult().equals(SessionResult.PASTED)) {
            // send fake block
            return;
        }
    }

    private void paste(final Session session, final Location location, final ClipboardController clipboardController) {
        //paste and will update settings
        clipboardController.paste(
                session,
                new Point3D(location.getBlockX(), location.getBlockY(), location.getBlockZ()),
                location.getWorld().getName()
        );
    }

    private void undo(final Session session, final Location location, final ClipboardController clipboardController) {
        clipboardController.undo(session,location.getWorld().getName());
    }
}