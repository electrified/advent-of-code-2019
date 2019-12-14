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
        Day6 d6 = new Day6();
    }

    public Day6() throws URISyntaxException, IOException {
        Map<String, Satelite> satellites = new HashMap<>();


        Files.lines(Path.of(ClassLoader.getSystemResource("day6input").toURI())).forEach(line -> {
            String[] lineparts = line.split("\\)");

            satellites.putIfAbsent(lineparts[0], new Satelite(lineparts[0]));

            Satelite orbited = satellites.get(lineparts[0]);

            Satelite previousOrbiting = satellites.get(lineparts[1]);

            if (previousOrbiting != null) {
                previousOrbiting.orbits = orbited;
            } else {
                satellites.put(lineparts[1], new Satelite(lineparts[1], orbited));
            }

        });

        Integer count = satellites.entrySet().stream().mapToInt(satellite -> {
            int orbitCount = 0;
            Satelite theSatellite = satellite.getValue();
            while (theSatellite.orbits != null) {
                orbitCount++;
                theSatellite = theSatellite.orbits;
            }
//            System.out.println(orbitCount);
            return orbitCount;
        }).reduce(0, Integer::sum);
        System.out.println(count);

        Satelite you = satellites.get("YOU");
        Satelite san = satellites.get("SAN");

        Set<Satelite> orbitingYou = getOrbiting(you);
        Set<Satelite> orbitingSan = getOrbiting(san);
        orbitingSan.retainAll(orbitingYou);

        int countToGetToCommonAncestor = getCountToGetToCommonAncestor(orbitingSan, you);
        countToGetToCommonAncestor += getCountToGetToCommonAncestor(orbitingSan, san);
        System.out.println(countToGetToCommonAncestor);
    }

    private int getCountToGetToCommonAncestor(Set<Satelite> orbitingSan, Satelite temp) {
        int count = 0;
        while(temp.orbits != null && !orbitingSan.contains(temp.orbits)) {
            temp = temp.orbits;
            count++;
        }
        return count;
    }

    static Set<Satelite> getOrbiting(Satelite you) {
        Set<Satelite> youParents = new HashSet<>();

        Satelite tempSatellite = you;
        while (tempSatellite.orbits != null) {
            youParents.add(tempSatellite.orbits);
            tempSatellite = tempSatellite.orbits;
        }
        return youParents;
    }

    static class Satelite {
        String name;
        Satelite orbits;

        public Satelite(String name) {
            this.name = name;
        }

        public Satelite(String name, Satelite orbits) {
            this.name = name;
            this.orbits = orbits;
        }
    }
}
