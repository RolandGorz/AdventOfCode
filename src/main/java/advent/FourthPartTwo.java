package advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FourthPartTwo {
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
                out += searchX(search, i, j);
            }
        }
        System.out.println(out);
    }

    private static int searchX(List<List<Character>> search, int row, int col) {
        if (search.get(row).get(col) != 'A' || row == 0 || col == 0 || row == search.size() - 1 || col == search.getFirst().size() - 1) {
            return 0;
        }
        Character upLeft = search.get(row - 1).get(col - 1);
        Character upRight = search.get(row - 1).get(col + 1);
        Character downLeft = search.get(row + 1).get(col - 1);
        Character downRight = search.get(row + 1).get(col + 1);
        int masCount = 0;
        if ((upLeft == 'M' && downRight == 'S') || upLeft == 'S' && downRight == 'M') {
            masCount++;
        }
        if ((upRight == 'M' && downLeft == 'S') || upRight == 'S' && downLeft == 'M') {
            masCount++;
        }
        return masCount == 2 ? 1 : 0;
    }
}
