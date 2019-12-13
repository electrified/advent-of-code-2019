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
}