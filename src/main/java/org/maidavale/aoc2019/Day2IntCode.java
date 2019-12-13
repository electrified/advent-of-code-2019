package org.maidavale.aoc2019;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Day2IntCode {
    public static void main(String[] args) throws URISyntaxException, IOException {
        List<Integer> initialProgram = IntCode.loadProgram("day2input");
        IntCode computer = new IntCode();
        for(int noun = 0; noun < 100; noun++) {
            for(int verb = 0; verb < 100; verb++) {
//                System.out.println(String.format("Running for noun %d verb %d", noun, verb));
                List<Integer> program = new ArrayList<>(initialProgram);
                program.set(1, noun);
                program.set(2, verb);

                computer.setProgram(program);
                computer.runProgram();
                if (program.get(0) == 19690720) {
                    System.out.println(100 * noun + verb);
                }
            }
        }
    }

}
