package com.example.springfling.kata;

import java.util.HashMap;

public class DuplicateEncoder {
    static String encode(String word) {
        var letters = word.toLowerCase().toCharArray();
        HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
        for (int i = 0; i < letters.length; i++) {
            charMap.put(letters[i], charMap.getOrDefault(letters[i], 0) + 1);
        }
        var sb = new StringBuilder();
        for (int i = 0; i < letters.length; i++) {
            if (charMap.get(letters[i]) == 1) {
                sb.append("(");
            } else {
                sb.append(")");
            }
        }
        return sb.toString();
    }
}
