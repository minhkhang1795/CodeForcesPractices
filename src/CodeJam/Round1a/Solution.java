package Round1a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        problem1(in);
    }

    static class Cake {
        double w = 0;
        double h = 0;
        double minCut = 0;
        double maxCut = 0;
        double p = 0;

        public Cake(double width, double height) {
            w = width;
            h = height;
            p = width * 2 + height * 2;
            if (width < height)
                minCut = 2 * width;
            else
                minCut = 2 * height;
            maxCut = 2 * Math.sqrt(width * width + height * height);
        }

        public static int compare(Cake a, Cake b) {
            return (int) (a.maxCut - b.maxCut);
        }

        public static int compare2(Cake a, Cake b) {
            return (int) (a.minCut - b.minCut);
        }
    }

    public static void problem2(Scanner in) {
        int cases = in.nextInt();
        for (int cas = 1; cas <= cases; cas++) {
            int n = in.nextInt();
            double p = in.nextDouble();
            double p_orin = p;
            Cake[] cakes = new Cake[n];

            for (int i = 0; i < n; i++) {
                Cake cake = new Cake(in.nextDouble(), in.nextDouble());
                cakes[i] = cake;
                p -= cake.p;
            }
            Arrays.sort(cakes, Cake::compare);
            int i = 0;
            boolean pass = false;
            for (; i < cakes.length; i++) {
                if (p >= cakes[i].minCut && p <= cakes[i].maxCut) {
                    System.out.println("Case #" + cas + ": " + p_orin);
                    pass = true;
                    break;
                }
                if (p - cakes[i].maxCut >= 0)
                    p -= cakes[i].maxCut;
                else
                    break;
            }
            if (!pass) {
                if (p == 0) {
                    System.out.println("Case #" + cas + ": " + p_orin);
                } else {
                    Arrays.sort(cakes, Cake::compare2);
                    for (; i < cakes.length; i++) {
                        if (p >= cakes[i].minCut && p <= cakes[i].maxCut) {
                            System.out.println("Case #" + cas + ": " + p_orin);
                            pass = true;
                            break;
                        }
                        if (p - cakes[i].minCut >= 0)
                            p -= cakes[i].minCut;
                        else
                            break;
                    }
                    if (p == 0) {
                        System.out.println("Case #" + cas + ": " + p_orin);
                    } else if (!pass) {
                        double result = p_orin - p;
                        System.out.println("Case #" + cas + ": " + result);
                    }

                }
            }
        }
    }

    public static void problem1(Scanner in) {
        int n = in.nextInt();
        for (int i = 1; i <= n; i++) {
            int r = in.nextInt();
            int c = in.nextInt();
            int h = in.nextInt();
            int v = in.nextInt();
            char[][] list = new char[r][c];
            for (int ri = 0; ri < r; ri++) {
                char[] row = in.next().toCharArray();
                System.arraycopy(row, 0, list[ri], 0, c);
            }
            problem1Solve(i, r, c, h, v, list);
        }
    }

    private static void problem1Solve(int i, int r, int c, int h, int v, char[][] list) {
        int[] rows = new int[r];
        int[] cols = new int[c];
        int sum = 0;
        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                if (list[x][y] == '@') {
                    rows[x]++;
                    cols[y]++;
                    sum++;
                }
            }
        }
        if (sum == 0) {
            System.out.println("Case #" + i + ": POSSIBLE");
        } else {
            if (sum % (h + 1) != 0 || sum % (v + 1) != 0 || sum % (v + 1) % (h + 1) != 0) {
                System.out.println("Case #" + i + ": IMPOSSIBLE");
            } else {
                // Horizontal
                int hValue = sum / (h + 1);
                int temp = hValue;
                int[] cutH = new int[h + 2];
                int cutH_index = 0;
                cutH[cutH_index++] = 0;
                for (int a = 0; a < rows.length; a++) {
                    temp -= rows[a];
                    if (temp < 0) {
                        System.out.println("Case #" + i + ": IMPOSSIBLE");
                        return;
                    } else if (temp == 0) {
                        temp = hValue;
                        cutH[cutH_index] = a + 1;
                        cutH_index++;
                    }
                }

                // Vertical
                int vValue = sum / (v + 1);
                temp = vValue;
                int[] cutV = new int[v + 2];
                int cutV_index = 0;
                cutV[cutV_index++] = 0;
                for (int a = 0; a < cols.length; a++) {
                    temp -= cols[a];
                    if (temp < 0) {
                        System.out.println("Case #" + i + ": IMPOSSIBLE");
                        return;
                    } else if (temp == 0) {
                        temp = vValue;
                        cutV[cutV_index++] = a + 1;
                    }
                }

                int expected = sum / (v + 1) / (h + 1);
                for (int hr = 0; hr < cutH.length - 1; hr++) {
                    for (int vr = 0; vr < cutV.length - 1; vr++) {
                        int actual = 0;
                        for (int ai = cutH[hr]; ai < cutH[hr + 1]; ai++) {
                            for (int bi = cutV[vr]; bi < cutV[vr + 1]; bi++) {
                                if (list[ai][bi] == '@')
                                    actual++;
                            }
                        }
                        if (actual != expected) {
                            System.out.println("Case #" + i + ": IMPOSSIBLE");
                            return;
                        }
                    }
                }
                System.out.println("Case #" + i + ": POSSIBLE");
            }

        }
    }
}
