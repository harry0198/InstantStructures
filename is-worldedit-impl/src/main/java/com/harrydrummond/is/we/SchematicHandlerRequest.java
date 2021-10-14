package com.harrydrummond.is.we;

import com.harrydrummond.is.core.annotations.HandlerRequest;
import com.harrydrummond.is.core.schematic.ClipboardController;

@HandlerRequest(priority = 5)
public class SchematicHandlerRequest implements ClipboardController {

    @Override
    public ClipboardController request() {
        return null;
    }

    @Override
    public void load() {

    }

    @Override
    public void paste() {

    }
}