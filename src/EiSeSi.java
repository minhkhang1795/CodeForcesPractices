import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class EiSeSi {
    /*
    Problem 876: Problem http://codeforces.com/problemset/problem/876
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        problem_b2(in);
    }

    private static void problem_b2(Scanner in) {
        // Problem http://codeforces.com/problemset/problem/876/B

        // Processing input
        int n = in.nextInt(), k = in.nextInt(), m = in.nextInt();
        ArrayList<Integer> nums = new ArrayList<>();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++)
            numbers[i] = in.nextInt();

        // Algorithm
        int[] count = new int[m];
        int max_index = -1;
        for (int i : numbers) {
            int p = numbers[i] % m;
            nums.add(p);
            int a = ++count[p];
            if (a == k) {
                max_index = p;
                break;
            }
        }

        // Printing output
        if (max_index != -1) {
            System.out.println("Yes");
            for (int i = 0; i < nums.size(); i++)
                if (nums.get(i) == max_index)
                    System.out.print(numbers[i] + " ");
        } else
            System.out.print("No");
    }

    private static void problem_b(Scanner in) {
        // Problem http://codeforces.com/problemset/problem/876/B

        // Old solution B(0) = n^2
        int n = in.nextInt(), k = in.nextInt(), m = in.nextInt();
        ArrayList<Integer> numbers = new ArrayList<>();
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        for (int i = 0; i < n; i++)
            numbers.add(in.nextInt());


        ArrayList<Integer> empty = new ArrayList<>();
        empty.add(numbers.get(0));
        results.add(empty);
        int index = -1;
        for (int i = 1; i < numbers.size(); i++) {
            if (index != -1)
                break;
            int number = numbers.get(i);
            boolean isNew = true;
            for (int j = 0; j < results.size(); j++) {
                ArrayList<Integer> result = results.get(j);
                int subtract = Math.abs(number - result.get(0));
                if (subtract % m == 0) {
                    result.add(number);
                    isNew = false;
                    if (result.size() == k) {
                        index = j;
                        empty = result;
                    }
                    break;
                }
            }
            if (isNew) {
                empty = new ArrayList<>();
                empty.add(number);
                results.add(empty);
            }
        }

        if (index != -1) {
            System.out.println("Yes");
            for (int i = 0; i < k; i++)
                System.out.print(empty.get(i) + " ");
        } else
            System.out.print("No");
    }
}
