import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day3 {
    public static void main(String[] args) throws URISyntaxException, IOException {
        int[][] map = new int[100000][100000];
        List<List<String>> lines = Files.lines(Path.of(ClassLoader.getSystemResource("day3input").toURI())).map( line -> Arrays.asList(line.split(","))).collect(Collectors.toList());
        for (int line = 0; line < lines.size(); line++) {
            processLine(map, lines.get(line), 1 << line);
        }
    }

    private static void processLine (int[][] map, List<String> commands, int wire) {
        int x = 1000;
        int y = 1000;
        for(String command : commands) {
            char direction = command.charAt(0);
            int distance = Integer.parseInt(command.substring(1));
            System.out.println(command + " " + distance + " " + x + " " + y);
            switch (direction) {
                case 'U':
                    while (distance > 0) {
                        y--;
                        distance--;
                        map[x][y] = map[x][y] & wire;
                    }
                    break;
                case 'D':
                    while (distance > 0) {
                        y++;
                        distance--;
                        map[x][y] = map[x][y] & wire;
                    }
                    break;
                case 'L':
                    while (distance > 0) {
                        x--;
                        distance--;
                        map[x][y] = map[x][y] & wire;
                    }
                    break;
                case 'R':
                    while (distance > 0) {
                        x++;
                        distance--;
                        map[x][y] = map[x][y] & wire;
                    }
                    break;
            }
        };
    }
}
