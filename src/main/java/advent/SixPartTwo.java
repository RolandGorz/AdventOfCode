package advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SixPartTwo {
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
        boolean[][] handled = new boolean[search.size()][search.getFirst().size()];
        Direction currDirection = Direction.UP;
        int out = 0;
        while (x >= 0 && x < search.size() && y >= 0 && y < search.getFirst().size()) {
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
                            if (!handled[x][y] && next != '^') {
                                search.get(x).set(y, '#');
                                if (findLoop(currDirection, x + 1, y, search)) {
                                    out++;
                                }
                                handled[x][y] = true;
                                search.get(x).set(y, '.');
                            }
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
                            if (!handled[x][y] && next != '^') {
                                search.get(x).set(y, '#');
                                if (findLoop(currDirection, x - 1, y, search)) {
                                    out++;
                                }
                                handled[x][y] = true;
                                search.get(x).set(y, '.');
                            }
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
                            if (!handled[x][y] && next != '^') {
                                search.get(x).set(y, '#');
                                if (findLoop(currDirection, x, y + 1, search)) {
                                    out++;
                                }
                                handled[x][y] = true;
                                search.get(x).set(y, '.');
                            }
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
                            if (!handled[x][y] && next != '^') {
                                search.get(x).set(y, '#');
                                if (findLoop(currDirection, x, y - 1, search)) {
                                    out++;
                                }
                                handled[x][y] = true;
                                search.get(x).set(y, '.');
                            }
                        }
                    }
                    break;
            }
        }
        System.out.println(out);
    }

    private static boolean findLoop(Direction currDirection, int x, int y, List<List<Character>> search) {
        Set<PositionWithDirection> seen = new HashSet<>();
        while (x >= 0 && x < search.size() && y >= 0 && y < search.getFirst().size()) {
            PositionWithDirection positionWithDirection = new PositionWithDirection(x, y, currDirection);
            if (seen.contains(positionWithDirection)) {
                return true;
            } else {
                seen.add(positionWithDirection);
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
        return false;
    }

    private enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    private static class PositionWithDirection {
        int x;
        int y;
        Direction direction;

        PositionWithDirection(int x, int y, Direction direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof PositionWithDirection temp) {
                return temp.x == x && temp.y == y && temp.direction == direction;
            }
            return false;
        }

        @Override
        public int hashCode() {
            int h = 5381;
            h += (h << 5) + x;
            h += (h << 5) + y;
            h += (h << 5) + direction.hashCode();
            return h;
        }
    }
}
