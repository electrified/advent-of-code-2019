package org.maidavale.aoc2019;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Day3Part2 {

    public Day3Part2() throws URISyntaxException, IOException {
        List<Set<Point>> points = new ArrayList<>();
        points.add(new HashSet<>());
        points.add(new HashSet<>());

        List<List<String>> lines = Files.lines(Path.of(ClassLoader.getSystemResource("day3input").toURI())).map(line -> Arrays.asList(line.split(","))).collect(Collectors.toList());
        for (int line = 0; line < lines.size(); line++) {
            processLine(points, lines.get(line), line);
        }

        points.get(0).retainAll(points.get(1));

        points.get(0).stream().forEach(p ->
                {
                        System.out.println(p.distanceTravelled + " " + p.x + " " + p.y);
                }
        );

        int closestCrossing = 9999999;
        for (Point point: points.get(0)) {
            int wire2dist =  points.get(1).stream().filter(p -> p.equals(point)).findFirst().get().distanceTravelled;
            System.out.println(point.distanceTravelled + " " + wire2dist);
            int distance = point.distanceTravelled + wire2dist;
            if (distance < closestCrossing) {
                closestCrossing = distance;
            }
        }
        System.out.println(closestCrossing);
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        new Day3Part2();
    }

    private void processLine(List<Set<Point>> points , List<String> commands, int wire) {
        int x = 0;
        int y = 0;
        int cumulativeDistance = 0;
        for (String command : commands) {
            char direction = command.charAt(0);
            int distance = Integer.parseInt(command.substring(1));
            System.out.println(command + " " + distance + " " + x + " " + y);
            switch (direction) {
                case 'U':
                    while (distance > 0) {
                        y--;
                        distance--;
                        cumulativeDistance++;
                        points.get(wire).add(new Point(x, y, cumulativeDistance));
                    }
                    break;
                case 'D':
                    while (distance > 0) {
                        y++;
                        distance--;
                        cumulativeDistance++;
                        points.get(wire).add(new Point(x, y, cumulativeDistance));
                    }
                    break;
                case 'L':
                    while (distance > 0) {
                        x--;
                        distance--;
                        cumulativeDistance++;
                        points.get(wire).add(new Point(x, y, cumulativeDistance));
                    }
                    break;
                case 'R':
                    while (distance > 0) {
                        x++;
                        distance--;
                        cumulativeDistance++;
                        points.get(wire).add(new Point(x, y, cumulativeDistance));
                    }
                    break;
            }
        }
    }


    public static class Point {
        private int x;
        private int y;
        private int distanceTravelled;

        public Point(int x, int y, int distanceTravelled) {
            this.x = x;
            this.y = y;
            this.distanceTravelled = distanceTravelled;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

}
