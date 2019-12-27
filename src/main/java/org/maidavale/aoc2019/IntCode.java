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

    IntCodeReader codeReader;

    IntCodeWriter codeWriter;

    public IntCode() {
        instructionLengths = new HashMap<>();
        instructionLengths.put(1, 4);
        instructionLengths.put(2, 4);
        instructionLengths.put(3, 2);
        instructionLengths.put(4, 2);
        instructionLengths.put(5, 3);
        instructionLengths.put(6, 3);
        instructionLengths.put(7, 4);
        instructionLengths.put(8, 4);
        instructionLengths.put(99, 1);

        ReadFromStdin rfs = new ReadFromStdin();
        codeReader = rfs::readInteger;
        codeWriter = System.out::println;
    }

    void runProgram() {
        Integer ip = 0;
        boolean endExecution = false;
        boolean jump = false;

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
                    codeWriter.writeInteger(read(paramMode[0],params[0]));
                    break;
                case 5:
                    if (read(paramMode[0], params[0]) > 0) {
                        ip = read(paramMode[1], params[1]);
                        jump = true;
                    }
                    break;
                case 6:
                    if (read(paramMode[0], params[0]) == 0) {
                        ip = read(paramMode[1], params[1]);
                        jump = true;
                    }
                    break;
                case 7:
                    if(read(paramMode[0], params[0]) < read(paramMode[1], params[1])) {
                        write( params[2], 1);
                    } else {
                        write( params[2], 0);
                    }
                    break;
                case 8:
                    if(read(paramMode[0], params[0]) == read(paramMode[1], params[1])) {
                        write( params[2], 1);
                    } else {
                        write( params[2], 0);
                    }
                    break;
                case 99:
//                    System.out.println("Ending execution");
                    endExecution = true;
                    break;
                default:
                    System.out.println(String.format("an error has occurred - unrecognised opcode %d", instruction));
                    endExecution = true;
            }
            if(!jump) {
                ip += instructionLengths.get(instruction);
            }

            jump = false;

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

    Integer readIntegerInput() {
        return codeReader.readInteger();
    }
}
