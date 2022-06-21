package com.example.springfling.kata;

public class GoodOldFizzBuzz {
    public String fizzBuzzRange(int end) {
        var sb = new StringBuilder();
        for (int i = 1; i <= end; i++) {
            sb.append(fizzBuzzSingle(i));
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    public String fizzBuzzSingle(int n) {
        var divThree = n % 3 == 0;
        var divFive = n % 5 == 0;
        if (divThree && divFive) {
            return "FizzBuzz";
        } else if (divThree) {
            return "Fizz";
        } else if (divFive) {
            return "Buzz";
        }
        return String.valueOf(n);
    }
}
