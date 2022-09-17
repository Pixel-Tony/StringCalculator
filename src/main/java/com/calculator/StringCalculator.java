package com.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) return 0;

        String[] matches;
        if (numbers.startsWith("//")) {
            var mainMatcher = Pattern.compile("^//([^\\[\\]\\d]*?|(?:\\[[^\\[\\]\\d]+]){2,})\\n(.*)$", Pattern.DOTALL).matcher(numbers);
            if (!mainMatcher.matches())
                throw new IllegalArgumentException("Input string doesn't match the general pattern");

            String delimitersLine = mainMatcher.group(1), numbersLine = mainMatcher.group(2);
            String[] delimiters = numbers.startsWith("//[")
                    ? (delimitersLine += "[,]").substring(1, delimitersLine.length() - 1).split("]\\[")
                    : new String[]{delimitersLine, ","};
            matches = numbersLine.split("(?<=\\d)(" + String.join("|", Arrays.stream(delimiters).map(Pattern::quote).toList()) + ")(?=-?\\d)");
        } else matches = numbers.split("(?<=^|\\d)[,\n](?=-\\d|\\d|$)");

        int finalSum = 0;
        ArrayList<Integer> negatives = new ArrayList<>();
        for (String number : matches) {
            try {
                int i = Integer.parseInt(number);
                if (i < 0) negatives.add(i);
                else if (i <= 1000) finalSum += i;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Cannot convert match \"" + number + "\" to integer");
            }
        }
        if (negatives.isEmpty()) return finalSum;
        throw new IllegalArgumentException("Found negative integers inside input: " + Arrays.toString(negatives.toArray()));
    }
}

