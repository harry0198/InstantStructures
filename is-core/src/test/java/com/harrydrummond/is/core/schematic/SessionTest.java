package com.harrydrummond.is.core.schematic;

import org.junit.jupiter.api.Test;

import java.io.File;

class SessionTest {

    @Test
    public void canBeSerialized() {
        Session session = new Session(new File("example/example.txt"));

    }

}