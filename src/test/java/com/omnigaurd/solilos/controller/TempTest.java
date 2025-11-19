package com.omnigaurd.solilos.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TempTest {

    @Test
    public void testHealth() {
        Temp temp = new Temp();
        String expected = "ok";
        String actual = temp.health();
        assertEquals(expected, actual);
    }
}