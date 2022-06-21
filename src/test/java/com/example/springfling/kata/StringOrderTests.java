package com.example.springfling.kata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class StringOrderTests {
    @Test
    public void test1() {
        assertEquals(StringOrder.order("is2 Thi1s T4est 3a"), "Thi1s is2 3a T4est");
    }

    @Test
    public void test2() {
        assertEquals(StringOrder.order("4of Fo1r pe6ople g3ood th5e the2"), "Fo1r the2 g3ood 4of th5e pe6ople");
    }

    @Test
    public void test3() {
        assertEquals(StringOrder.order(""), "");
    }
}