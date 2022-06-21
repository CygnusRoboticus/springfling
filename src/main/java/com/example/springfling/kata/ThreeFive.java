package com.example.springfling.kata;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ThreeFive {
    public int solution(int number) {
        var sum = 0;
        for (int i = number - 1; i > 0; i--) {
            if (i % 5 == 0 || i % 3 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}