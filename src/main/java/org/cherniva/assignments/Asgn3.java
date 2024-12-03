package org.cherniva.assignments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Asgn3 {
    private int mullItOverDoDont(List<String> input) {
        int sum = 0;
        String regex = "do\\(\\).*?don't\\(\\)";
        Pattern pattern = Pattern.compile(regex);
        for (String line : input) {
//        String regex = "don't\\(\\).*?(mul\\(\\d+,\\d+\\))*?do\\(\\)";
            Matcher matcher = pattern.matcher(line);
            String inputCopy = line;
            if (matcher.find()) {
                String dont = matcher.group();
                System.out.println();
                System.out.println(dont);
                inputCopy = inputCopy.replace(dont, "");
            }
        }
    }


    private static int mullItOver(List<String> input) {
        int res = 0;
        for (String line : input) {
            String regex = "mul\\(\\d+,\\d+\\)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(line);
            Pattern patternNum = Pattern.compile("\\d+");
            int sum = 0;
            while (matcher.find()) {
                String mulClause = matcher.group();
                int mul = 1;
                Matcher matcherNum = patternNum.matcher(mulClause);
                while (matcherNum.find()) {
                    int num = Integer.parseInt(matcherNum.group());
                    mul *= num;
                }
                sum += mul;
            }
            res += sum;
        }
        return res;
    }

    public static void solve(String filePath) {
        try {
            Scanner sc = new Scanner(new File(filePath));
            List<String> input = new ArrayList<>();
            while (sc.hasNextLine()) {
                input.add(sc.nextLine());
            }
            System.out.println(mullItOver(input));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

}
