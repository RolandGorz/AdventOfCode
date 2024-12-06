package advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class Third {
    public static void main(String[] args) throws IOException {
        URL input = Second.class.getClassLoader().getResource("input3.txt");
        int out = 0;
        boolean enabled = true;
        try (FileReader fileReader = new FileReader(input.getFile())) {
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String currLine = bufferedReader.readLine();
                while (currLine != null) {
                   for (int i = 0; i < currLine.length(); ++i) {
                       if (currLine.charAt(i) == 'd') {
                           i++;
                           if (i < currLine.length() && currLine.charAt(i) == 'o') {
                               i++;
                               if (i < currLine.length() && currLine.charAt(i) == '(') {
                                   i++;
                                   if (i < currLine.length() && currLine.charAt(i) == ')') {
                                       enabled = true;
                                   }
                               } else if (i < currLine.length() && currLine.charAt(i) == 'n') {
                                   i++;
                                   if (i < currLine.length() && currLine.charAt(i) == '\'') {
                                       i++;
                                       if (i < currLine.length() && currLine.charAt(i) == 't') {
                                           i++;
                                           if (i < currLine.length() && currLine.charAt(i) == '(') {
                                               i++;
                                               if (i < currLine.length() && currLine.charAt(i) == ')') {
                                                   enabled = false;
                                               }
                                           }
                                       }
                                   }
                               }
                           }
                       }
                       if (enabled && i < currLine.length() && currLine.charAt(i) == 'm') {
                           i++;
                           if (i < currLine.length() && currLine.charAt(i) == 'u') {
                               i++;
                               if (i < currLine.length() && currLine.charAt(i) == 'l') {
                                   i++;
                                   if (i < currLine.length() && currLine.charAt(i) == '(') {
                                       i++;
                                       int firstDigit = 0;
                                       int secondDigit = 0;
                                       while(i < currLine.length() && Character.isDigit(currLine.charAt(i))) {
                                           firstDigit *= 10;
                                           firstDigit += currLine.charAt(i) - '0';
                                           i++;
                                       }
                                       if (i < currLine.length() && currLine.charAt(i) == ',') {
                                           i++;
                                           while(i < currLine.length() && Character.isDigit(currLine.charAt(i))) {
                                               secondDigit *= 10;
                                               secondDigit += currLine.charAt(i) - '0';
                                               i++;
                                           }
                                           if (currLine.charAt(i) == ')') {
                                               out += firstDigit * secondDigit;
                                           }
                                       }
                                   }
                               }
                           }
                       }
                   }
                   currLine = bufferedReader.readLine();
                }
            }
        }
        System.out.println(out);
    }
}
