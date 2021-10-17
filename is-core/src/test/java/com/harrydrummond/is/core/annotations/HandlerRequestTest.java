package com.harrydrummond.is.core.annotations;

import com.harrydrummond.is.core.protection.Point3D;
import com.harrydrummond.is.core.schematic.ClipboardController;
import com.harrydrummond.is.core.schematic.Session;
import org.junit.jupiter.api.Test;

public class HandlerRequestTest {

    @Test
    public void request1() {

    }

    @HandlerRequest
    public class Request1 implements ClipboardController {

        @Override
        public ClipboardController request() {
            System.out.println("Made the request boyyyy");
            return null;
        }

        @Override
        public Session paste(Session session, Point3D point, String world) {
            return null;
        }

        @Override
        public Session undo(Session session, String worldName) {
            return null;
        }
    }
}