package com.example.springfling.kata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class GoodOldFizzBuzzTests {
    @Test
    public void testFizzBuzz() {
        GoodOldFizzBuzz buzzer = new GoodOldFizzBuzz();
        assertEquals(
            "1 2 Fizz 4 Buzz Fizz 7 8 Fizz Buzz 11 Fizz 13 14 FizzBuzz",
            buzzer.fizzBuzzRange(15)
        );
    }
}