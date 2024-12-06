package advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class Fifth {
    public static void main(String[] args) throws IOException {
        URL input = Second.class.getClassLoader().getResource("input5.txt");
        try (FileReader fileReader = new FileReader(input.getFile())) {
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String currLine = bufferedReader.readLine();
                while (!currLine.isEmpty()) {
                    currLine = bufferedReader.readLine();
                }
                System.out.println(bufferedReader.readLine());
            }
        }


    }
}
