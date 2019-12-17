package org.maidavale.aoc2019;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class IntCodeTest {
    @Test
    public void test() throws IOException, URISyntaxException {
        List<Integer> initialProgram = IntCode.loadProgram("day2input");
        IntCode computer = new IntCode();
        int noun = 2;
        int verb = 59;
        System.out.println(String.format("Running for noun %d verb %d", noun, verb));
        List<Integer> program = new ArrayList<>(initialProgram);
        program.set(1, noun);
        program.set(2, verb);
        computer.setProgram(program);
        computer.runProgram();
        if (program.get(0) == 19690720) {
            System.out.println(100 * noun + verb);
        }
    }

    @Test
    public void test2() {
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("1234".getBytes());
        System.setIn(in);

        List<Integer> initialProgram = new ArrayList<>();
        IntCode computer = new IntCode();
        computer.setProgram(IntCode.csvToIntList("3,0,4,0,99"));
        computer.runProgram();

        System.setIn(sysInBackup);
    }

    @Test
    public void test3() {
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("8".getBytes());
        System.setIn(in);

        List<Integer> initialProgram = new ArrayList<>();
        IntCode computer = new IntCode();
        computer.setProgram(IntCode.csvToIntList("3,9,8,9,10,9,4,9,99,-1,8"));
        computer.runProgram();

        System.setIn(sysInBackup);
    }

    @Test
    public void test4() {
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("10".getBytes());
        System.setIn(in);

        List<Integer> initialProgram = new ArrayList<>();
        IntCode computer = new IntCode();
        computer.setProgram(IntCode.csvToIntList("    3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,\n" +
                "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,\n" +
                "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99"));
        computer.runProgram();

        System.setIn(sysInBackup);
    }

    @Test
    public void test5() {
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("8\n15\n".getBytes());
        System.setIn(in);

        List<Integer> initialProgram = new ArrayList<>();
        IntCode computer = new IntCode();
        computer.setProgram(IntCode.csvToIntList("3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,\n" +
                "1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0"));
        computer.runProgram();

        System.setIn(sysInBackup);
    }

}