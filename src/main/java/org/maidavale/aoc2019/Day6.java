package org.maidavale.aoc2019;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day6 {
    public static void main(String[] args) throws IOException, URISyntaxException {
        new Day6();
    }

    public Day6() throws URISyntaxException, IOException {
        Map<String, Satellite> satellites = new HashMap<>();


        Files.lines(Path.of(ClassLoader.getSystemResource("day6input").toURI())).forEach(line -> {
            String[] lineparts = line.split("\\)");

            satellites.putIfAbsent(lineparts[0], new Satellite(lineparts[0]));

            Satellite orbited = satellites.get(lineparts[0]);

            Satellite previousOrbiting = satellites.get(lineparts[1]);

            if (previousOrbiting != null) {
                previousOrbiting.orbits = orbited;
            } else {
                satellites.put(lineparts[1], new Satellite(lineparts[1], orbited));
            }

        });

        Integer count = satellites.entrySet().stream().mapToInt(satellite -> {
            int orbitCount = 0;
            Satellite theSatellite = satellite.getValue();
            while (theSatellite.orbits != null) {
                orbitCount++;
                theSatellite = theSatellite.orbits;
            }
//            System.out.println(orbitCount);
            return orbitCount;
        }).reduce(0, Integer::sum);
        System.out.println(count);

        Satellite you = satellites.get("YOU");
        Satellite san = satellites.get("SAN");

        Set<Satellite> orbitingYou = getOrbiting(you);
        Set<Satellite> orbitingSan = getOrbiting(san);
        orbitingSan.retainAll(orbitingYou);

        int countToGetToCommonAncestor = getCountToGetToCommonAncestor(orbitingSan, you);
        countToGetToCommonAncestor += getCountToGetToCommonAncestor(orbitingSan, san);
        System.out.println(countToGetToCommonAncestor);
    }

    private int getCountToGetToCommonAncestor(Set<Satellite> orbitingSan, Satellite temp) {
        int count = 0;
        while(temp.orbits != null && !orbitingSan.contains(temp.orbits)) {
            temp = temp.orbits;
            count++;
        }
        return count;
    }

    static Set<Satellite> getOrbiting(Satellite you) {
        Set<Satellite> youParents = new HashSet<>();

        Satellite tempSatellite = you;
        while (tempSatellite.orbits != null) {
            youParents.add(tempSatellite.orbits);
            tempSatellite = tempSatellite.orbits;
        }
        return youParents;
    }

    static class Satellite {
        String name;
        Satellite orbits;

        public Satellite(String name) {
            this.name = name;
        }

        public Satellite(String name, Satellite orbits) {
            this.name = name;
            this.orbits = orbits;
        }
    }
}
