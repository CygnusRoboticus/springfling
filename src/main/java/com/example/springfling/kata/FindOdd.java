package com.example.springfling.kata;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindOdd {
    public static int findIt(int[] integers) {
        Map<Integer, Integer> intMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < integers.length; i++) {
            intMap.put(integers[i], intMap.getOrDefault(integers[i], 0) + 1);
        }
        for (var entry : intMap.entrySet()) {
            var value = entry.getValue();
            if (value % 2 == 1) {
                return entry.getKey();
            }
        }
        return -1;
    }
}