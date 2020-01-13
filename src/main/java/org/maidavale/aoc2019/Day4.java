package org.maidavale.aoc2019;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Day4 {
    public static void main(String[] args) {
        Day4 day4 = new Day4();
        day4.passesRules(246515, 739105);
    }

    public void passesRules(int lowerBound, int upperBound) {
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
        String number = Integer.toString(num);

        char[] numChars = number.toCharArray();

        char[] sortedChars = numChars.clone();
        Arrays.sort(sortedChars);

        for (int i = 0; i < numChars.length; i++) {
            if (numChars[i] != sortedChars[i]) {
                System.out.println("fail!");
                return false;
            }
        }

        Set<Integer> uniqueDigits = new HashSet<>();
        for (char numChar : numChars) {
            uniqueDigits.add((int) numChar);
        }

        return uniqueDigits.size() <= 5;
    }
}
