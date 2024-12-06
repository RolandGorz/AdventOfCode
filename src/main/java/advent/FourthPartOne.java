package advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FourthPartOne {
    private static final Character[] GOAL = new Character[]{'X', 'M', 'A', 'S'};

    public static void main(String[] args) throws IOException {
        URL input = Second.class.getClassLoader().getResource("input4.txt");
        List<List<Character>> search = new ArrayList<>();
        try (FileReader fileReader = new FileReader(input.getFile())) {
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String currLine = bufferedReader.readLine();
                while (currLine != null) {
                    List<Character> curr = new ArrayList<>();
                    search.add(curr);
                    for (int i = 0; i < currLine.length(); ++i) {
                        curr.add(currLine.charAt(i));
                    }
                    currLine = bufferedReader.readLine();
                }
            }
        }
        int out = 0;
        for (int i = 0; i < search.size(); ++i) {
            for (int  j = 0; j < search.getFirst().size(); ++j) {
                out += searchLeft(search, i, j) + searchRight(search, i, j) + searchDown(search, i, j) + searchUp(search, i, j)
                        + searchUpLeft(search, i, j) + searchUpRight(search, i, j) + searchDownLeft(search, i, j) + searchDownRight(search, i, j);
            }
        }
        System.out.println(out);
    }

    private static int searchLeft(List<List<Character>> search, int row, int col) {
        int goalPos = 0;
        while (true) {
            if (goalPos == GOAL.length) {
                return 1;
            }
            if (col < 0 || search.get(row).get(col) != GOAL[goalPos]) {
                return 0;
            } else {
                goalPos++;
                col--;
            }
        }
    }

    private static int searchRight(List<List<Character>> search, int row, int col) {
        int goalPos = 0;
        while (true) {
            if (goalPos == GOAL.length) {
                return 1;
            }
            if (col == search.getFirst().size() || search.get(row).get(col) != GOAL[goalPos]) {
                return 0;
            } else {
                goalPos++;
                col++;
            }
        }
    }

    private static int searchDown(List<List<Character>> search, int row, int col) {
        int goalPos = 0;
        while (true) {
            if (goalPos == GOAL.length) {
                return 1;
            }
            if (row == search.size() || search.get(row).get(col) != GOAL[goalPos]) {
                return 0;
            } else {
                goalPos++;
                row++;
            }
        }
    }

    private static int searchUp(List<List<Character>> search, int row, int col) {
        int goalPos = 0;
        while (true) {
            if (goalPos == GOAL.length) {
                return 1;
            }
            if (row < 0 || search.get(row).get(col) != GOAL[goalPos]) {
                return 0;
            } else {
                goalPos++;
                row--;
            }
        }
    }

    private static int searchUpLeft(List<List<Character>> search, int row, int col) {
        int goalPos = 0;
        while (true) {
            if (goalPos == GOAL.length) {
                return 1;
            }
            if (col < 0 || row < 0 || search.get(row).get(col) != GOAL[goalPos]) {
                return 0;
            } else {
                goalPos++;
                col--;
                row--;
            }
        }
    }

    private static int searchUpRight(List<List<Character>> search, int row, int col) {
        int goalPos = 0;
        while (true) {
            if (goalPos == GOAL.length) {
                return 1;
            }
            if (col == search.getFirst().size() || row < 0 || search.get(row).get(col) != GOAL[goalPos]) {
                return 0;
            } else {
                goalPos++;
                col++;
                row--;
            }
        }
    }

    private static int searchDownLeft(List<List<Character>> search, int row, int col) {
        int goalPos = 0;
        while (true) {
            if (goalPos == GOAL.length) {
                return 1;
            }
            if (col < 0 || row == search.size() || search.get(row).get(col) != GOAL[goalPos]) {
                return 0;
            } else {
                goalPos++;
                col--;
                row++;
            }
        }
    }

    private static int searchDownRight(List<List<Character>> search, int row, int col) {
        int goalPos = 0;
        while (true) {
            if (goalPos == GOAL.length) {
                return 1;
            }
            if (col == search.getFirst().size() || row == search.size() || search.get(row).get(col) != GOAL[goalPos]) {
                return 0;
            } else {
                goalPos++;
                col++;
                row++;
            }
        }
    }
}
