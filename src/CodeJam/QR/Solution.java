package QR;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    public static void main(String[] args) {

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        problem3(in);
    }

    public static void problem1(Scanner in) {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int d = in.nextInt();
            String code = in.next();
            problem1_solve(i + 1, d, code.toCharArray());
        }
    }

    private static void problem1_solve(int num, int d, char[] code) {
        int power = 1;
        int totalDam = 0;
        int minHack = -1;
        ArrayList<Integer> powers = new ArrayList<>();
        for (char c : code) {
            if (c == 'C') {
                power *= 2;
            } else {
                powers.add(power);
                totalDam += power;
            }
        }
        int hack = 0;
        outerloop:
        for (int i = powers.size() - 1; i >= 0; i--) {
            while (powers.get(i) > 1) {
                if (totalDam <= d) {
                    minHack = hack;
                    break outerloop;
                }
                if (i >= 1 && powers.get(i).equals(powers.get(i - 1))) {
                    break;
                }
                totalDam = totalDam - powers.get(i) + powers.get(i) / 2;
                powers.set(i, powers.get(i) / 2);
                if (i + 1 < powers.size() && powers.get(i) < powers.get(i + 1))
                    i++;
                hack++;
            }
        }
        if (totalDam <= d) {
            minHack = hack;
        }
        if (minHack != -1) {
            System.out.println("Case #" + num + ": " + minHack);
        } else {
            System.out.println("Case #" + num + ": IMPOSSIBLE");
        }
    }

    public static void problem2(Scanner in) {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int length = in.nextInt();
            PriorityQueue<Integer> first = new PriorityQueue<>();
            PriorityQueue<Integer> second = new PriorityQueue<>();
            for (int j = 0; j < length; j++) {
                if (j % 2 == 0)
                    first.add(in.nextInt());
                else
                    second.add(in.nextInt());
            }
            problem2_solve(i + 1, first, second, length);
        }
    }

    private static void problem2_solve(int num, PriorityQueue<Integer> first, PriorityQueue<Integer> second, int length) {
        int index = -1;
        int current = first.poll();
        for (int i = 1; i < length; i++) {
            int temp;
            if (i % 2 == 0)
                temp = first.poll();
            else
                temp = second.poll();
            if (temp < current) {
                index = i - 1;
                break;
            }
            current = temp;
        }
        if (index != -1) {
            System.out.println("Case #" + num + ": " + index);
        } else {
            System.out.println("Case #" + num + ": OK");
        }
    }

    public static void problem4(Scanner in) {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            double area = in.nextDouble();
            problem4_solve(i + 1, area);
            if (i != n - 1)
                System.out.print("\n");
        }
    }

    private static void problem4_solve(int num, double area) {
        double[][] points = new double[3][3];
        // Only works for area < sqrt(2)
        points[0] = new double[]{0.5, 0, 0};
        points[1] = new double[]{0, 0.5, 0};
        points[2] = new double[]{0, 0, 0.5};
        if (area <= 1.5) {
            double angle = Math.PI / 4 - Math.acos(area * Math.sqrt(2) / 2);
            double p0_x = Math.cos(angle) * points[0][0] - Math.sin(angle) * points[0][1];
            double p0_y = Math.sin(angle) * points[0][0] + Math.cos(angle) * points[0][1];
            double p1_x = Math.cos(angle) * points[1][0] - Math.sin(angle) * points[1][1];
            double p1_y = Math.sin(angle) * points[1][0] + Math.cos(angle) * points[1][1];
            points[0][0] = p0_x;
            points[0][1] = p0_y;
            points[1][0] = p1_x;
            points[1][1] = p1_y;
        }
        System.out.print("Case #" + num + ":");
        for (double[] point : points) {
            System.out.print("\n" + point[0] + " " + point[1] + " " + point[2]);
        }
    }

    public static void problem3(Scanner in) {

        int n = in.nextInt();
        outerloop:
        for (int caseNum = 0; caseNum < n; caseNum++) {
            int area = in.nextInt();
            int a, b;
            if (n == 200)
                a = 50;
            else
                a = 5;
            b = area / a;

            int i0 = 1, j0 = 1;
            System.out.println((i0 + 1) + " " + (j0 + 1));
            System.out.flush();
            boolean[][] grids = new boolean[a][b];
            int count = 0;
            while (count < area) {
                int i = in.nextInt(), j = in.nextInt();
                if (i == 0 && j == 0)
                    break;
                if (i == -1 && j == -1)
                    break outerloop;
                i -= 1;
                j -= 1;
                if (!grids[i][j]) {
                    count++;
                    grids[i][j] = true;
//                    printGrids(grids);
                }
                if (j0 + 1 < b - 1) {
                    if (grids[i0 - 1][j0-1] && grids[i0][j0-1] && grids[i0 + 1][j0-1]) {
                        // Move to the right
                        j0 += 1;
                    }
                } else {
                    if (grids[i0 - 1][j0 - 1] && grids[i0][j0 - 1] && grids[i0 + 1][j0 - 1]) {
                        if (grids[i0 - 1][j0] && grids[i0][j0] && grids[i0 + 1][j0]) {
                            if (grids[i0 - 1][j0 + 1] && grids[i0][j0 + 1] && grids[i0 + 1][j0 + 1]) {
                                // Move down, to first column
                                j0 = 1;
                                if (i0 + 1 < a - 1)
                                    i0 += 1;
                            }
                        }
                    }
                }
                System.out.println((i0 + 1) + " " + (j0 + 1));
                System.out.flush();
            }
        }
    }

    private static void printGrids(boolean[][] grids) {
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[j].length; j++) {
                if (grids[i][j]) {
                    System.out.print(0 + " ");
                } else
                    System.out.print(1 + " ");
            }
            System.out.println();
        }
    }
}
