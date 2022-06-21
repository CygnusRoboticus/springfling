package com.example.springfling.kata;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class StringOrder {
    public static Comparator<String> EmbeddedDigitComparator = new Comparator<String>() {
        @Override
        public int compare(String s1, String s2) {
            var digit1 = Integer.parseInt(s1.replaceAll("\\D", ""));
            var digit2 = Integer.parseInt(s2.replaceAll("\\D", ""));
            return Integer.compare(digit1, digit2);
        }
    };

    public static String order(String sentence) {
        var words = sentence.split(" ");
        return Arrays.stream(words).sorted(StringOrder.EmbeddedDigitComparator).collect(Collectors.joining(" "));
    }
}