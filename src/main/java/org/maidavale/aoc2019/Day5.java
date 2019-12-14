package org.maidavale.aoc2019;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Day5 {
    public static void main(String[] args) throws IOException, URISyntaxException {
        List<Integer> initialProgram = IntCode.loadProgram("day5input");
        IntCode computer = new IntCode();
        computer.setProgram(initialProgram);
        computer.runProgram();
    }
}
