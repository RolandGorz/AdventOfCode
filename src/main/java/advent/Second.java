package advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Second {
    public static void main(String[] args) throws IOException {
        URL input = Second.class.getClassLoader().getResource("input2.txt");
        List<List<Integer>> levels = new ArrayList<>();
        try (FileReader fileReader = new FileReader(input.getFile())) {
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String currLine = bufferedReader.readLine();
                while (currLine != null) {
                    String[] level = currLine.split(" ");
                    List<Integer> nums = new ArrayList<>();
                    for (String x : level) {
                        int curr = Integer.parseInt(x);
                        nums.add(curr);
                    }
                    levels.add(nums);
                    currLine = bufferedReader.readLine();
                }
            }
        }
        int out = 0;
        List<List<Integer>> failures = new ArrayList<>();
        for (List<Integer> list : levels) {
            if (isValid(list)) {
                out++;
            } else {
                failures.add(list);
            }
        }
        for (List<Integer> list : failures) {
            for (int i = 0; i < list.size(); ++i) {
                List<Integer> temp = new ArrayList<>();
                for (int j = 0; j < list.size(); ++j) {
                    if (i != j) {
                        temp.add(list.get(j));
                    }
                }
                if (isValid(temp)) {
                    out++;
                    break;
                }
            }
        }
        System.out.println(out);
    }

    private static boolean isValid(List<Integer> in) {
        int prev = in.get(0);
        int curr = in.get(1);
        boolean increasing = prev < curr;
        if (Math.abs(prev - curr) > 3 || prev - curr == 0) {
            return false;
        }
        for (int i = 2; i < in.size(); ++i) {
            prev = curr;
            curr = in.get(i);
            if ((prev < curr && !increasing) || (prev > curr && increasing)) {
                return false;
            }
            if (Math.abs(prev - curr) > 3 || prev - curr == 0) {
                return false;
            }
        }
        return true;
    }
}