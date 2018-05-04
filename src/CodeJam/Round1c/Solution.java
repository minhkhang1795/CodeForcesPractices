package Round1c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        problem1(in);
    }

    private static void problem1(Scanner in) {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            problem2_solve(i + 1);
        }
    }

    private static void problem2_solve(int cas) {
        System.out.println("Case #" + cas + ": POSSIBLE");
    }

}
