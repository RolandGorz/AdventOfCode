package advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Fifth {
    public static void main(String[] args) throws IOException {
        Map<Integer, Set<Integer>> before = new HashMap<>();
        URL input = Second.class.getClassLoader().getResource("input5.txt");
        int out = 0;
        List<List<Integer>> failed = new ArrayList<>();
        try (FileReader fileReader = new FileReader(input.getFile())) {
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String currLine = bufferedReader.readLine();
                while (!currLine.isEmpty()) {
                    String[] curr = currLine.split("\\|");
                    int left = Integer.parseInt(curr[0]);
                    int right = Integer.parseInt(curr[1]);
                    Set<Integer> value = before.getOrDefault(left, new HashSet<>());
                    value.add(right);
                    before.put(left, value);
                    currLine = bufferedReader.readLine();
                }
                currLine = bufferedReader.readLine();
                linesLoop:
                while (currLine != null) {
                    List<Integer> original = new ArrayList<>();
                    List<Integer> available = new ArrayList<>();
                    String[] curr = currLine.split(",");
                    for (String x : curr) {
                        int value = Integer.parseInt(x);
                        original.add(value);
                        available.add(value);
                    }
                    int mid = available.get(available.size() / 2);
                    while (!available.isEmpty()) {
                        Set<Integer> banned = before.get(available.removeLast());
                        for (Integer x : available) {
                            if (banned.contains(x)) {
                                failed.add(original);
                                currLine = bufferedReader.readLine();
                                continue linesLoop;
                            }
                        }
                    }
                    out += mid;
                    currLine = bufferedReader.readLine();
                }
            }
        }
        System.out.printf("Part One result: %d", out);

    }
    private static List<Integer> getTopologicalSort(Map<Integer, Set<Integer>> before) {
        Set<Integer> seen = new HashSet<>();
        List<Integer> topologicalSort = new ArrayList<>();
        for (Integer key : before.keySet()) {

        }
        return topologicalSort;
    }

    private static void traverse(Integer curr, Map<Integer, Set<Integer>> before, List<Integer> topologicalSort, Set<Integer> seen) {

    }
}
