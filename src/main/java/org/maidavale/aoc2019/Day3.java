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

public class Day3 {
    private List<Set<Point>> points = new ArrayList<>();

    public Day3() throws URISyntaxException, IOException {
        points.add(new HashSet<>());
        points.add(new HashSet<>());

        List<List<String>> lines = Files.lines(Path.of(ClassLoader.getSystemResource("day3input").toURI())).map(line -> Arrays.asList(line.split(","))).collect(Collectors.toList());
        for (int line = 0; line < lines.size(); line++) {
            processLine(points, lines.get(line), line);
        }

        points.get(0).retainAll(points.get(1));

        int closestCrossing = 9999999;
        for (Point point: points.get(0)) {
            int distance = Math.abs(point.getX()) + Math.abs(point.getY());
            if (distance < closestCrossing) {
                closestCrossing = distance;
            }
        }
        System.out.println(closestCrossing);
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        Day3 day3 = new Day3();
    }

    private void processLine(List<Set<Point>> points , List<String> commands, int wire) {
        int x = 0;
        int y = 0;
        for (String command : commands) {
            char direction = command.charAt(0);
            int distance = Integer.parseInt(command.substring(1));
            System.out.println(command + " " + distance + " " + x + " " + y);
            switch (direction) {
                case 'U':
                    while (distance > 0) {
                        y--;
                        distance--;
                        points.get(wire).add(new Point(x, y));
                    }
                    break;
                case 'D':
                    while (distance > 0) {
                        y++;
                        distance--;
                        points.get(wire).add(new Point(x, y));
                    }
                    break;
                case 'L':
                    while (distance > 0) {
                        x--;
                        distance--;
                        points.get(wire).add(new Point(x, y));
                    }
                    break;
                case 'R':
                    while (distance > 0) {
                        x++;
                        distance--;
                        points.get(wire).add(new Point(x, y));
                    }
                    break;
            }
        }
    }


    public static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
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
