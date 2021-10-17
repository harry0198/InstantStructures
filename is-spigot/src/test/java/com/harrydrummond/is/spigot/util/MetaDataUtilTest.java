package com.harrydrummond.is.spigot.util;

import com.harrydrummond.is.core.schematic.Session;
import org.apache.commons.lang.SerializationUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class MetaDataUtilTest {


    @Test
    public void dataTest1() {
        Session session = new Session(new File("example/example.txt"));

        byte[] ok = SerializationUtils.serialize(session);
        System.out.println(ok);
        Object obj = SerializationUtils.deserialize(ok);
        Session session1 = (Session) obj;
        System.out.println(session1.getFile());
    }
}