package com.harrydrummond.is.core.annotations;

import com.harrydrummond.is.core.schematic.ClipboardController;
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
        public void load() {

        }

        @Override
        public void paste() {

        }
    }
}