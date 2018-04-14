package Round1b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        problem1(in);
    }

    public static void problem1(Scanner in) {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int d = in.nextInt();
            String code = in.next();
            System.out.println(d + code);
        }
    }
}
