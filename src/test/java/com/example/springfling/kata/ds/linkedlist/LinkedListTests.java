package com.example.springfling.kata.ds.linkedlist;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LinkedListTests {
    @Test
    public void LinkedListTests(){
        List<String> singly = new LinkedList<String>();
        assertTrue(singly.isEmpty());
        assertEquals(0, singly.size());

        singly.add("ABC");
        assertFalse(singly.isEmpty());
        assertEquals(1, singly.size());

        singly.add(1, "CBA");
        assertEquals(2, singly.size());
        assertArrayEquals(new Object[] { "ABC", "CBA" }, singly.toArray());
    }
}