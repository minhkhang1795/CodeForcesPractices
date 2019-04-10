package Playground;

import java.util.Arrays;

/***
 * The Hydra has 9 heads, 4 of which are poisonous and 5 are not.
 *
 * Every time a head is chopped, two other will grow,
 * each with 50% chance to be poisonous and 50% chance to be not poisonous.
 *
 * What is the probability that after 100 chops (each head is chopped randomly),
 * none of the heads is poisonous?
 */
public class Playground {
    public static void main(String[] args) {

        // Test1 = 4/9 * 3/10 * 2/11 * 1/12 * (1/4)^4
        System.out.println(hydra(4, 9, 4, generateMemo()));
        // Test2 = 0
        System.out.println(hydra(4, 9, 3, generateMemo()));
        // Test3 = 0
        System.out.println(hydra(4, 9, 2, generateMemo()));
        // Test4 = 0
        System.out.println(hydra(4, 9, 1, generateMemo()));
        // Solution = 3.668331E-36
        System.out.println(hydra(4, 9, 100, generateMemo()));
    }

    /**
     * Create a 2D memo table 101x101, filled all with -1
     * @return
     */
    private static float[][] generateMemo() {
        float[][] memo = new float[101][101];
        Arrays.stream(memo).forEach(a -> Arrays.fill(a, -1));
        return memo;
    }

    public static float hydra(int poison, int total, int chops, float[][] memo) {
        if (poison < 0) poison = 0;

        // BASE CASE
        if (chops < poison) {
            memo[chops][poison] = 0;
            return memo[chops][poison];
        } else if (chops == 0) {
            // poison is zero
            memo[chops][poison] = 1f;
            return memo[chops][poison];
        }

        // MEMORIZATION: if already solved, retrieve from the memo table
        if (memo[chops][poison] != -1) {
            return memo[chops][poison];
        }

        // RECURRENT RELATIONS

        // Chop 1 poison, added 0 poisons
        float probOnePoisonLess = ((float) poison / total * .5f * .5f)
                * hydra(poison - 1, total + 1, chops - 1, memo);

        // Chop 1 poison, added 1 poison + chop 0 poisons, added 0 poisons
        float probSamePoison = ((float) poison / total * 2 * .5f * .5f +
                (float) (total - poison) / total * .5f * 0.5f)
                * hydra(poison, total + 1, chops - 1, memo);

        // Chop 1 poison, added 2 poisons + chop 0 poisons, added 1 poison
        float probOnePoisonMore = ((float) poison / total * .5f * .5f +
                (float) (total - poison) / total * 2 * .5f * .5f)
                * hydra(poison + 1, total + 1, chops - 1, memo);

        // Chop 0 poisons, added 2 poisons.
        float probTwoPoisonsMore = ((float) (1 - poison) / total * .5f * .5f)
                * hydra(poison + 2, total + 1, chops - 1, memo);

        memo[chops][poison] = probOnePoisonLess + probSamePoison + probOnePoisonMore + probTwoPoisonsMore;
        return memo[chops][poison];
    }


}
