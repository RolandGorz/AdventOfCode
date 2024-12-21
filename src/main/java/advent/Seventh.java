package advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Seventh {
    public static void main(String[] args) throws IOException {
        URL input = Second.class.getClassLoader().getResource("input7.txt");
        Long out = 0L;
        try (FileReader fileReader = new FileReader(input.getFile())) {
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String currLine = bufferedReader.readLine();
                while (currLine != null) {
                    List<Long> numbers = new ArrayList<>();
                    Long target = null;
                    String[] split = currLine.split(" ");
                    target = Long.valueOf(split[0].substring(0, split[0].length() - 1));
                    for (int i = 1; i < split.length; ++i) {
                        numbers.add(Long.valueOf(split[i]));
                    }
                    if (add(numbers, 0, target, 0L) || mult(numbers, 0, target, 1L) || concat(numbers, 0, target, 0L)) {
                        out+= target;
                    }
                    currLine = bufferedReader.readLine();
                }
            }
        }
        System.out.println(out);
    }

    private static boolean add(List<Long> nums, int pos, Long target, Long total) {
        if(total > target) {
            return false;
        }
        total += nums.get(pos);
        pos++;
        if (pos == nums.size()) {
            return total.equals(target);
        }
        return add(nums, pos, target, total) || mult(nums,pos,target,total) || concat(nums, pos, target, total);
    }

    private static boolean mult(List<Long> nums, int pos, Long target, Long total) {
        if(total > target) {
            return false;
        }
        total *= nums.get(pos);
        pos++;
        if (pos == nums.size()) {
            return total.equals(target);
        }
        return add(nums, pos, target, total) || mult(nums, pos, target, total) || concat(nums, pos, target, total);
    }

    private static boolean concat(List<Long>nums, int pos, Long target, Long total) {
        if (total > target) {
            return false;
        }
        Long curr = nums.get(pos);
        while(curr > 0) {
            curr /= 10;
            total *= 10;
        }
        total += nums.get(pos);
        pos++;
        if (pos == nums.size()) {
            return total.equals(target);
        }
        return add(nums, pos, target, total) || mult(nums, pos, target, total) || concat(nums, pos, target, total);
    }
}
