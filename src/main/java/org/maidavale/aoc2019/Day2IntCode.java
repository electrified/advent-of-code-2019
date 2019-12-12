package org.maidavale.aoc2019;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2IntCode {
    public static void main(String[] args) throws URISyntaxException, IOException {
        String stringProgram = Files.readString(Path.of(ClassLoader.getSystemResource("day2input").toURI()));
        List<Integer> initialProgram = Arrays.stream(stringProgram.split(",")).map(String::trim).map(Integer::parseInt).collect(Collectors.toList());

        for(int noun = 0; noun < 100; noun++) {
            for(int verb = 0; verb < 100; verb++) {
                List<Integer> program = new ArrayList<>(initialProgram);
                program.set(1, noun);
                program.set(2, verb);
                runProgram(program);
                if (program.get(0) == 19690720) {
                    System.out.println(100 * noun + verb);
                }
            }
        }
    }

    private static void runProgram(List<Integer> program) {
        Integer ip = 0;
        boolean endExecution = false;

        while (true) {
            Integer instruction = program.get(ip);
//            System.out.println(ip +" " + instruction);
            switch (instruction) {

                case 1:
                    program.set( program.get(ip + 3), program.get(program.get(ip + 1)) + program.get(program.get(ip + 2)));
                    ip +=4;
                    break;
                case 2:
                    program.set( program.get(ip + 3), program.get(program.get(ip + 1)) * program.get(program.get(ip + 2)));
                    ip +=4;
                    break;
                case 99:
                    endExecution = true;
                    break;
                default:
                    System.out.println("an error has occurred");
                    endExecution = true;
            }

            if (endExecution) {
                break;
            }
        }
    }
}
