package org.maidavale.aoc2019;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day4Part2 {
    public static void main(String[] args) {
        Day4Part2 day4 = new Day4Part2();
        day4.passesRules(246515, 739105);
    }

    public void passesRules(int lowerBound, int upperBound) {
        //check is 6 digits long

        //check within range

        int matchCount = 0;

        for(int i = lowerBound; i < upperBound; i++) {
            if(passesRules(i)) {
                matchCount++;
            }
        }
        System.out.println(matchCount);
    }

    boolean passesRules(int num) {
        //going left to right digits never decrease
        String number = new String(Integer.toString(num));

        char[] numChars = number.toCharArray();

        char[] sortedChars = numChars.clone();
        Arrays.sort(sortedChars);

        for (int i = 0; i < numChars.length; i++) {
            if (numChars[i] != sortedChars[i]) {
//                System.out.println(number + " fail!");
                return false;
            }
        }

        Map<Integer, Integer> uniqueDigits = new HashMap<>();
        for (char numChar : numChars) {
            Integer val = Integer.valueOf(numChar);
            if(uniqueDigits.containsKey(val)) {
                uniqueDigits.put(val, uniqueDigits.get(val) + 1);
            } else {
                uniqueDigits.put(val, 1);
            }
        }

        if(uniqueDigits.size() > 5) {
            System.out.println(number + " more than 6 unique digits");
            return false;
        }

        if(!uniqueDigits.values().stream().anyMatch(v -> v == 2)) {
            System.out.println(number + " subset");
            return false;
        }
        return true;
    }
}
