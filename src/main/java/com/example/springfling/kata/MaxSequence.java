package com.example.springfling.kata;

public class MaxSequence {
    public static int sequence(int[] arr) {
        var maxSum = Integer.MIN_VALUE;
        for (int i = 0; i <= arr.length - 1; i++) {
            var current = 0;
            for (int j = i; j <= arr.length - 1; j++) {
                current += arr[j];
                maxSum = Math.max(maxSum, current);
            }
        }
        return Math.max(0, maxSum);
    }
}