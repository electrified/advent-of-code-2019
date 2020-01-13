package org.maidavale.aoc2019;

import com.google.common.collect.Collections2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class Day7 {
    public Day7() throws IOException, URISyntaxException {
        List<Integer> prog = IntCode.loadProgram("day7input");
//        List<Integer> prog = IntCode.csvToIntList("3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0");

        List<Integer> initialPhases = new ArrayList<>();
        initialPhases.add(4);
        initialPhases.add(3);
        initialPhases.add(2);
        initialPhases.add(1);
        initialPhases.add(0);

        Integer max = Collections2.orderedPermutations(initialPhases).stream().map(p -> {
            Amplifier output = new Amplifier(prog, p.get(4), new Amplifier(prog, p.get(3), new Amplifier(prog, p.get(2), new Amplifier(prog, p.get(1), new Amplifier(prog, p.get(0), null)))));
            return new AbstractMap.SimpleEntry<>(output.output, p);
        }).max(Entry.comparingByKey()).get().getKey();

        System.out.println(max);
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        new Day7();
    }

    static class Amplifier {
        List<Integer> inputs = new ArrayList<>();

        Integer output;

        public Amplifier(List<Integer> initialProgram, Integer phase, Amplifier input) {
            inputs.add(phase);
            inputs.add(input == null ? 0 : input.output);

            IntCode computer = new IntCode();
            List<Integer> program = new ArrayList<>(initialProgram);
            computer.setProgram(program);
            computer.codeReader = this::readInteger;
            computer.codeWriter = this::writeOutput;
            computer.runProgram();
        }

        Integer readInteger() {
            return inputs.remove(0);
        }

        void writeOutput(Integer i) {
            this.output = i;
        }
    }
}
