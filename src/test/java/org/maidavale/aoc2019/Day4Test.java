package org.maidavale.aoc2019;

import org.junit.Test;

import static org.junit.Assert.*;

public class Day4Test {

    @Test
    public void passesRules() {
        Day4 ut = new Day4();
        assertFalse(ut.passesRules(234567));

        assertTrue(ut.passesRules(224567));
    }
}