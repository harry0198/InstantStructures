package com.harrydrummond.is.spigot.util;

import com.harrydrummond.is.core.schematic.Session;
import com.harrydrummond.is.spigot.InstantStructuresPlugin;
import org.apache.commons.lang.SerializationException;
import org.apache.commons.lang.SerializationUtils;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;

public class MetaDataUtil {

    public static final String KEY = "instantstructures";
    private static final Logger LOGGER = LoggerFactory.getLogger(MetaDataUtil.class);

    public static boolean hasMetaData(final ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;

        return meta.getPersistentDataContainer().get(InstantStructuresPlugin.getNamespacedKey(KEY), PersistentDataType.BYTE_ARRAY) != null;
    }

    /**
     * Gets Session object handle from stored itemstack. Will return null if
     * item has no metadata, deserialization failed or deserialized object was
     * not of type {@link Session}
     * @param item Item to get session object from
     * @return Session from metadata. Null if item has no metadata, deserialization failed or deserialized object was
     * not of type {@link Session}
     */
    public static Session getSessionFromMeta(final ItemStack item) {
        if (!hasMetaData(checkNotNull(item))) return null;

        ItemMeta meta = item.getItemMeta();

        // Wont be null, checks done in hasMetaData col
        byte[] data = meta.getPersistentDataContainer().get(InstantStructuresPlugin.getNamespacedKey(KEY), PersistentDataType.BYTE_ARRAY);
        Object obj;
        try {
            obj = SerializationUtils.deserialize(data);
        } catch (SerializationException ex) {
            LOGGER.debug("Tried deserializing object on item {}, but failed! Data corrupted! byte[{}]", item.getType(), data);
            return null;
        }

        if (!(obj instanceof Session)) {
            LOGGER.debug("Tried deserializing object but was not of type Session. obj({})", obj);
            return null;
        }

        return (Session) obj;

    }

    /**
     * Serializes and sets session object into item's persistent data container. Outputs errors
     * to DEBUG logger
     * @param item Item to set session object to
     * @param session Session object to put in persistent meta data
     * @return True if successfully added to item metadata, false if failed.
     */
    public static boolean setSessionOnItem(final ItemStack item, final Session session) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            LOGGER.debug("Item had no metadata! Cannot assign session");
            return false;
        }
        byte[] obj;
        try {
            obj = SerializationUtils.serialize(session);
        } catch (SerializationException ex) {
            LOGGER.debug("Failed to serialize session object {} on item type {}!", session.toString(), item.getType());
            return false;
        }
        meta.getPersistentDataContainer().set(InstantStructuresPlugin.getNamespacedKey(KEY), PersistentDataType.BYTE_ARRAY, obj);
        return true;
    }
}