package Round1c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        problem3(in);
    }

    private static void problem3(Scanner in) {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int N = in.nextInt();
            int[] w = new int[N];
            for (int j = 0; j < N; j++) {
                w[j] = in.nextInt();
            }
            int cas = i + 1;

            int r = problem3_solve(w, N);
            System.out.println("Case #" + cas + ": " + r);
        }
    }

    private static int problem3_solve(int[] w, int N) {
        int[][] weight = new int[N][N];
        int maxCarry = -1;
        for (int len = 0; len < weight.length; len++) {
            for (int j = 0; j < weight.length - len; j++) {
                int minWeight = Integer.MAX_VALUE;
                int i = j + len;

                if (len == 0) {
                    weight[j][i] = w[i];
                    maxCarry = maxCarry > len + 1 ? maxCarry : len + 1;
                } else {
                    for (int k = 0; k < j + 1; k++) {
                        if (weight[j - k][i - 1 - k] != -1)
                            minWeight = minWeight < weight[j - k][i - 1 - k] ? minWeight : weight[j - k][i - 1 - k];
                    }
                    if (w[i] * 6 >= minWeight) {
                        weight[j][i] = w[i] + minWeight;
                        maxCarry = maxCarry > len + 1 ? maxCarry : len + 1;
                    } else {
                        weight[j][i] = -1;
                    }
                }
            }
        }
        return maxCarry;
    }

    private static void problem1(Scanner in) {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int N = in.nextInt();
            int L = in.nextInt();
            char[][] words = new char[N][L];
            HashSet<String> exist = new HashSet<>();
            for (int j = 0; j < N; j++) {
                String word = in.next();
                exist.add(word);
                words[j] = word.toCharArray();
            }
            String word = problem1_solvedp(new char[L], 0, words, exist, N, L);
            int cas = i + 1;
            System.out.println("Case #" + cas + ": " + word);
        }
    }

    private static String problem1_solvedp(char[] current, int i, char[][] words, HashSet<String> exist, int N, int L) {
        if (i == L) {
            String w = charToString(current);
            if (exist.contains(w)) {
                return "-";
            } else
                return w;
        }
        String a = "-";
        for (int j = 0; j < N; j++) {
            current[i] = words[j][i];
            a = problem1_solvedp(current, i + 1, words, exist, N, L);
            if (!a.equals("-"))
                return a;
        }

        return a;
    }

    private static String charToString(char a[]) {
        StringBuilder b = new StringBuilder();
        for (char c : a) {
            b.append(c);
        }
        return b.toString();

    }

}
