package advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SixPartOne {
    public static void main(String[] args) throws IOException {
        URL input = Second.class.getClassLoader().getResource("input6.txt");
        List<List<Character>> search = new ArrayList<>();
        int x = 0;
        int y = 0;
        try (FileReader fileReader = new FileReader(input.getFile())) {
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String currLine = bufferedReader.readLine();
                int row = 0;
                while (currLine != null) {
                    List<Character> curr = new ArrayList<>();
                    search.add(curr);
                    for (int i = 0; i < currLine.length(); ++i) {
                        curr.add(currLine.charAt(i));
                        if (curr.getLast() == '^') {
                            x = row;
                            y = i;
                        }
                    }
                    currLine = bufferedReader.readLine();
                    row++;
                }
            }
        }
        boolean[][] seen = new boolean[search.size()][search.getFirst().size()];
        Direction currDirection = Direction.UP;
        int out = 0;
        while (x >= 0 && x < search.size() && y >= 0 && y < search.getFirst().size()) {
            if (!seen[x][y]) {
                seen[x][y] = true;
                out++;
            }
            switch (currDirection) {
                case UP:
                    if (x == 0) {
                        x--;
                    } else {
                        Character next = search.get(x - 1).get(y);
                        if (next == '#') {
                            currDirection = Direction.RIGHT;
                        } else {
                            x--;
                        }
                    }
                    break;
                case DOWN:
                    if (x == search.size() - 1) {
                        x++;
                    } else {
                        Character next = search.get(x + 1).get(y);
                        if (next == '#') {
                            currDirection = Direction.LEFT;
                        } else {
                            x++;
                        }
                    }
                    break;
                case LEFT:
                    if (y == 0) {
                        y--;
                    } else {
                        Character next = search.get(x).get(y - 1);
                        if (next == '#') {
                            currDirection = Direction.UP;
                        } else {
                            y--;
                        }
                    }
                    break;
                case RIGHT:
                    if (y == search.getFirst().size() - 1) {
                        y++;
                    } else {
                        Character next = search.get(x).get(y + 1);
                        if (next == '#') {
                            currDirection = Direction.DOWN;
                        } else {
                            y++;
                        }
                    }
                    break;
            }
        }
        System.out.println(out);
    }

    private enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
}
