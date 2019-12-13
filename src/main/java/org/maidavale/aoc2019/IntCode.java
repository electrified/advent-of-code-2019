package org.maidavale.aoc2019;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class IntCode {
    Map<Integer, Integer> instructionLengths;

    public void setProgram(List<Integer> program) {
        this.program = program;
    }

    List<Integer> program;

    public IntCode() {
        instructionLengths = new HashMap<>();
        instructionLengths.put(1, 4);
        instructionLengths.put(2, 4);
        instructionLengths.put(3, 2);
        instructionLengths.put(4, 2);
        instructionLengths.put(99, 1);
    }

    void runProgram() {
        Integer ip = 0;
        boolean endExecution = false;

        while (true) {
            Integer rawInstruction = program.get(ip);
//            System.out.println(ip +" " + instruction);
            // decode the addressing modes
            String strInstruction = String.format("%05d", rawInstruction);
            String addressingMode = strInstruction.substring(0,3);
            Integer instruction = Integer.parseInt(strInstruction.substring(3,5));

            boolean[] paramMode = new boolean[3];

            paramMode[0] = addressingMode.charAt(2) == '1';
            paramMode[1] = addressingMode.charAt(1) == '1';
            paramMode[2] = addressingMode.charAt(0) == '1';

            Integer[] params = new Integer[3];
//            System.out.println(instruction);
            if(!instructionLengths.containsKey(instruction)) {
                System.out.println("invalid instruction");
                break;
            }

            for (int i = 0; i < instructionLengths.get(instruction) - 1; i++) {
                params[i] = program.get(ip + i + 1);
            }

            switch (instruction) {

                case 1:
                    write( params[2], read(paramMode[0], params[0]) + read(paramMode[1], params[1]));
                    break;
                case 2:
                    write( params[2], read(paramMode[0], params[0]) * read(paramMode[1], params[1]));
                    break;
                case 3:
                    write( params[0], readIntegerInput());
                    break;
                case 4:
                    System.out.println(read(paramMode[0],params[0]));
                    break;
                case 99:
//                    System.out.println("Ending execution");
                    endExecution = true;
                    break;
                default:
                    System.out.println("an error has occurred");
                    endExecution = true;
            }
            ip += instructionLengths.get(instruction);

            if (endExecution) {
                break;
            }
        }
    }

    public int read(boolean mode, int address) {
        return mode ? address : program.get(address);
    }

    public void write(int address, int value) {
        program.set(address, value);
    }

    static List<Integer> loadProgram(final String programName) throws IOException, URISyntaxException {
        String stringProgram = Files.readString(Path.of(ClassLoader.getSystemResource(programName).toURI()));
        return csvToIntList(stringProgram);
    }

    static List<Integer> csvToIntList(String stringProgram) {
        return Arrays.stream(stringProgram.split(",")).map(String::trim).map(Integer::parseInt).collect(Collectors.toList());
    }

    static Integer readIntegerInput() {
        System.out.println("Enter input:");
        Scanner in = new Scanner(System.in);
        Integer enteredValue = in.nextInt();
        System.out.println(String.format("Entered value %d", enteredValue));
        return enteredValue;
    }
}
