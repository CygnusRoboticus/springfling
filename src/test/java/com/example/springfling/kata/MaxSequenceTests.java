package com.example.springfling.kata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MaxSequenceTests {
    @Test
    public void testEmptyArray() throws Exception {
        assertEquals(0, MaxSequence.sequence(new int[]{}));
    }
    @Test
    public void testExampleArray() throws Exception {
        assertEquals(6, MaxSequence.sequence(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}