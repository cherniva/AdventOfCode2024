package org.cherniva.assignments;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Asgn2 {
    private static boolean checkNumbers(boolean asc, int n1, int n2) {
        if (Math.abs(n2 - n1) <= 3)
            return (asc && n2 > n1) || (!asc && n2 < n1);
        else
            return false;
    }

    private static int countSafeReports(List<List<Integer>> reports) {
        int cnt = 0;
        for (List<Integer> report : reports) {
            boolean safe = true;
            boolean asc = report.get(0) < report.get(1);
            for (int i = 0; i < report.size() - 1; i++) {
                int n1 = report.get(i);
                int n2 = report.get(i + 1);

                if (!checkNumbers(asc, n1, n2)) {
                    safe = false;
                    break;
                }
            }
            cnt += safe ? 1 : 0;
        }
        return cnt;
    }

    private static int countSageReportsTolerateOne(List<List<Integer>> reports) {
        int cnt = 0;
        for (List<Integer> report : reports) {
            boolean safe = false;

            for (int i = 0; i < report.size(); i++) {
                final int _i = i;
                Integer[] modifiedReport = IntStream.range(0, report.size())
                        .filter(j -> j != _i)
                        .mapToObj(report::get)
                        .toArray(Integer[]::new);

                boolean areAdjacentDifferencesValid = IntStream.range(0, modifiedReport.length - 1)
                        .allMatch(j -> Math.abs(modifiedReport[j] - modifiedReport[j + 1]) <= 3);

                boolean isAscending = IntStream.range(0, modifiedReport.length - 1)
                        .allMatch(j -> modifiedReport[j] < modifiedReport[j + 1]);

                boolean isDescending = IntStream.range(0, modifiedReport.length - 1)
                        .allMatch(j -> modifiedReport[j] > modifiedReport[j + 1]);

                if (areAdjacentDifferencesValid && (isAscending || isDescending)) {
                    safe = true;
                    break;
                }
            }

            cnt += safe ? 1 : 0;
        }
        return cnt;
    }

    public static void solve() {
        Scanner sc = new Scanner(System.in);
        List<List<Integer>> listOfLists = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            List<Integer> list = new ArrayList<>();
            Scanner lineSc = new Scanner(line);
            while (lineSc.hasNextInt()) list.add(lineSc.nextInt());
            listOfLists.add(list);
        }
        System.out.println(countSafeReports(listOfLists));
        System.out.println(countSageReportsTolerateOne(listOfLists));
    }
}
