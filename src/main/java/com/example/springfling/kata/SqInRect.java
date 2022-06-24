package com.example.springfling.kata;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SqInRect {
    public static List<Integer> sqInRect(int length, int width) {
        if (length == width || length <= 0 || width <= 0) {
            return null;
        }

        var longer = Math.max(length, width);
        var shorter = Math.min(length, width);
        longer = longer - shorter;

        var squares = new ArrayList<Integer>();
        squares.add(shorter);
        var next = sqInRect(longer, shorter);
        if (next != null) {
            squares.addAll(next);
        } else if (longer == shorter) {
            squares.add(longer);
        }
        return squares;
    }
}
