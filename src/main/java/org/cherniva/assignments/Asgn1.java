package org.cherniva.assignments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Asgn1 {
    private static int measureDistance(List<Integer> list1, List<Integer> list2) {
        Collections.sort(list1);
        Collections.sort(list2);
        int distance = 0;
        for (int i = 0; i < list1.size(); i++) {
            distance += Math.abs(list1.get(i) - list2.get(i));
        }
        return distance;
    }

    private static int calculateSimilarity(List<Integer> list1, List<Integer> list2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer n : list2) {
            if (map.containsKey(n))
                map.put(n, map.get(n) + 1);
            else
                map.put(n, 1);
        }
        int sum = 0;
        for (Integer n : list1) {
            if (map.get(n) != null) sum += map.get(n) * n;
        }
        return sum;
    }

    public static void solve(String filePath) {
        try {
            Scanner sc = new Scanner(new File(filePath));
            List<Integer> list1 = new ArrayList<>();
            List<Integer> list2 = new ArrayList<>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                int n1 = Integer.parseInt(line.substring(0, line.indexOf(" ")));
                int n2 = Integer.parseInt(line.substring(line.lastIndexOf(" ")).trim());
                list1.add(n1);
                list2.add(n2);
            }
            System.out.println(measureDistance(list1, list2));
            System.out.println(calculateSimilarity(list1, list2));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
