package org.maidavale.aoc2019;

import org.junit.Test;

import static org.junit.Assert.*;

public class Day4Part2Test {

    @Test
    public void passesRules() {
        Day4Part2 ut = new Day4Part2();
        assertFalse(ut.passesRules(123444));

        assertTrue(ut.passesRules(112233));
        assertTrue(ut.passesRules(111122));

    }
}