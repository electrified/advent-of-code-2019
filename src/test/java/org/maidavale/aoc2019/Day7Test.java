package org.maidavale.aoc2019;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Day7Test {
    public static void main(String[] args) {
//       InputStream sysInBackup = System.in; // backup System.in to restore it later
//        ByteArrayInputStream in = new ByteArrayInputStream("78\n9\n\n".getBytes());
//        System.setIn(in);

        List<Integer> initialProgram = new ArrayList<>();
        IntCode computer = new IntCode();
        computer.setProgram(IntCode.csvToIntList("3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0"));
        computer.runProgram();

//        System.setIn(sysInBackup);
    }
}
