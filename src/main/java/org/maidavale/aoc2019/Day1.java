package org.maidavale.aoc2019;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day1 {

    public static void main2(String[] args) throws URISyntaxException, IOException {
        System.out.println(Files.lines(Path.of(ClassLoader.getSystemResource("input").toURI())).map(l -> {
            return Math.floor(Double.parseDouble(l) / 3) -2;
        }).reduce(0d, Double::sum));
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        Double answer = Files.lines(Path.of(ClassLoader.getSystemResource("input").toURI())).map(l -> {
            Double fuelForMass =  Math.floor(Double.parseDouble(l) / 3) -2;

            Double totalExtraFuel = 0d;

            Double fuelForFuel = Math.floor(fuelForMass / 3) -2;

            if (fuelForFuel < 0) {
                fuelForFuel = 0d;
            }

            totalExtraFuel += fuelForFuel;

            while (fuelForFuel > 0d) {

                fuelForFuel = Math.floor(fuelForFuel / 3) -2;

                if (fuelForFuel < 0d) {
                    fuelForFuel = 0d;
                }
                totalExtraFuel += fuelForFuel;
                System.out.println(fuelForFuel);
            }
            return fuelForMass + totalExtraFuel;
        }).reduce(0d, Double::sum);



        System.out.println(answer);
    }
}
