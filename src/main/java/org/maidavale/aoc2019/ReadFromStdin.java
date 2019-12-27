package org.maidavale.aoc2019;

import java.util.Scanner;

public class ReadFromStdin {
    public Integer readInteger() {
        System.out.println("Enter input:");
        Scanner in = new Scanner(System.in);
        Integer enteredValue = in.nextInt();
        System.out.println(String.format("Entered value %d", enteredValue));
        return enteredValue;
    }
}
