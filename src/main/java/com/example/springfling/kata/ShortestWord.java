package com.example.springfling.kata;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class ShortestWord {
    public static Comparator<String> MinLengthComparator = new Comparator<String>() {
        @Override
        public int compare(String s1, String s2) {
            return Integer.compare(s1.length(), s2.length());
        }
    };

    public static int findShort(String s) {
        var words = s.split(" ");
//        var minLength = words[0].length();
//        for (int i = 1; i < words.length; i++) {
//            minLength = Integer.min(minLength, words[i].length());
//        }
//        return minLength;
//        return Arrays.stream(words).min(ShortestWord.MinLengthComparator).orElseGet(() -> "").length();
        return Stream.of(s.split(" "))
            .mapToInt(String::length)
            .min()
            .getAsInt();
    }
}
