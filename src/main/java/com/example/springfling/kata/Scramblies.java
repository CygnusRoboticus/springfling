package com.example.springfling.kata;

public class Scramblies {
    public static boolean scramble(String source, String target) {
        var targetLen = target.length();
        for (int i = targetLen - 1; i >= 0; i--) {
            var letter = target.substring(i, i + 1);
            if (source.contains(letter)) {
                source = source.replaceFirst(letter, "");
                target = target.replaceFirst(letter, "");
            }
        }
        return target.length() == 0;
    }
}