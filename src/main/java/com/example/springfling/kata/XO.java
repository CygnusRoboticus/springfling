package com.example.springfling.kata;

import java.util.Arrays;

public class XO {
    public static boolean getXO (String str) {
        var letters = str.toLowerCase().split("");
        var ex = Arrays.stream(letters).filter((l) -> l.equals("x")).count();
        var oh = Arrays.stream(letters).filter((l) -> l.equals("o")).count();
        return ex == oh;
    }
}