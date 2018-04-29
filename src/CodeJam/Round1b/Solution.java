package Round1b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        problem2(in);
    }

    public static void problem2(Scanner in) {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int M = in.nextInt();
            int[][] choices = new int[M][2];
            for (int j = 0; j < M; j++) {
                choices[j][0] = in.nextInt();
                choices[j][1] = in.nextInt();
            }
            int[] avai = new int[M];
            for (int j = 0; j < M; j++) {
                avai[j] = in.nextInt();
            }
            problem2_solve(i + 1, M, choices, avai);
        }
    }

    private static void problem2_solve(int cas, int m, int[][] choices, int[] avai) {
        HashSet<Integer> dt = new HashSet<>();
        dt.add(0);
        problem2_solveDP(Integer.MAX_VALUE, 0, choices, avai, dt);
        System.out.println("Case #" + cas + ": " + avai[0]);
    }

    private static void problem2_solveDP(int goal, int target, int[][] choices, int[] avai, HashSet<Integer> dt) {
        int material1 = choices[target][0] - 1;
        int material2 = choices[target][1] - 1;
        if (dt.contains(material1) || dt.contains(material2))
            return;
        if (avai[material1] == 0 && avai[material2] == 0) {
            dt.add(material1);
            problem2_solveDP(3, material1, choices, avai, dt);
            dt.remove(material1);
            dt.add(material2);
            problem2_solveDP(2, material2, choices, avai, dt);
            dt.remove(material1);
        }
        while (avai[material1] > 0 && avai[material2] > 0) {
            while (avai[material1] > 0 && avai[material2] > 0 && avai[target] <= goal) {
                avai[target]++;
                avai[material1]--;
                avai[material2]--;
            }
            if (avai[target] >= goal)
                return;
            if (avai[material1] == 0 && avai[material2] == 0) {
                dt.add(material1);
                problem2_solveDP(3, material1, choices, avai, dt);
                dt.remove(material1);
                dt.add(material2);
                problem2_solveDP(2, material2, choices, avai, dt);
                dt.remove(material1);
            }
            if (avai[material1] > 0 && avai[material2] > 0)
                continue;
            if (avai[material1] == 0) {
                dt.add(material1);
            }
            if (avai[material1] == 0) {
                int temp = avai[material2] / 2 > 0 ? avai[material2] / 2 : 1;
                problem2_solveDP(temp, material1, choices, avai, dt);
            }
            if (avai[material1] != 0) {
                dt.remove(material1);
            }
            if (avai[material1] > 0 && avai[material2] > 0)
                continue;
            if (avai[material2] == 0) {
                dt.add(material2);
            }

            if (avai[material2] == 0) {
                int temp = avai[material1] / 2 > 0 ? avai[material1] / 2 : 1;
                problem2_solveDP(temp, material2, choices, avai, dt);
            }
            if (avai[material2] != 0) {
                dt.remove(material2);
            }
        }
    }

    public static void problem1(Scanner in) {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int people = in.nextInt();
            int lang_count = in.nextInt();
            int sum = 0;
            int[] languages = new int[lang_count];
            for (int j = 0; j < lang_count; j++) {
                languages[j] = in.nextInt();
                sum += languages[j];
            }
            int[] temp_lang = new int[lang_count + (people - sum)];
            for (int j = 0; j < lang_count; j++) {
                temp_lang[j] = languages[j];
            }
            problem1_solve(i + 1, people, sum, temp_lang);
        }
    }

    static class MyDouble implements Comparable {
        double d = 0;
        int in = 0;

        public MyDouble(int d, double people) {
            this.d = d / people;
            this.in = d;
        }

        @Override
        public int compareTo(Object o) {
            double dThis = this.d * 100 + 0.5;
            dThis = dThis - Math.floor(dThis);
            double dOther = ((MyDouble) o).d * 100 + 0.5;
            dOther = dOther - Math.floor(dOther);
            return dThis < dOther ? 1 : -1;
        }
    }

    public static void problem1_solve(int cas, int people, int sum, int[] languages) {
        MyDouble[] doubles = new MyDouble[languages.length];
        PriorityQueue<MyDouble> queue = new PriorityQueue<>();
        for (int i = 0; i < languages.length; i++) {
            doubles[i] = new MyDouble(languages[i], people);
            queue.add(doubles[i]);
        }
        double add = 1.0 / people;
        for (int i = 0; i < people - sum; i++) {
            MyDouble temp = queue.poll();
            if (temp != null) {
                temp.d += add;
                temp.in += 1;
                queue.add(temp);
            }
        }
        int result = 0;
        while (!queue.isEmpty()) {
            MyDouble temp = queue.poll();
            result += Math.round(temp.d * 100);
        }
        System.out.println("Case #" + cas + ": " + result);
    }
}


