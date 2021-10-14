package com.harrydrummond.is.core.schematic;

import com.harrydrummond.is.core.annotations.PluginRequestForm;

public interface ClipboardController extends PluginRequestForm {

    void load();
    void paste();
}