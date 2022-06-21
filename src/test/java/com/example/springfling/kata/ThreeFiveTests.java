package com.example.springfling.kata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ThreeFiveTests {
    @Test
    public void test() {
        assertEquals(23, new ThreeFive().solution(10));
    }
}