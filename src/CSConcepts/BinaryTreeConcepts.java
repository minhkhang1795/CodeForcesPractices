package CSConcepts;

import static java.lang.System.out;

public class BinaryTreeConcepts {
    public static void main(String[] args) {
//        isBinaryTreeTest();
//        treeHeightTest();
    }

    public static void treeHeightTest() {
        //    6
        //   / \
        //  4   7
        TreeNode a1 = new TreeNode(6);
        TreeNode a2 = new TreeNode(4);
        TreeNode a3 = new TreeNode(7);
        a1.left = a2;
        a1.right = a3;
        out.println(treeHeight(a2));

        //      6
        //    /  \
        //   4    8
        //  / \  /
        // 3   7 7
        //      \
        //       7
        TreeNode c1 = new TreeNode(6);
        TreeNode c2 = new TreeNode(4);
        TreeNode c3 = new TreeNode(8);
        TreeNode c4 = new TreeNode(3);
        TreeNode c5 = new TreeNode(7);
        TreeNode c6 = new TreeNode(7);
        TreeNode c7 = new TreeNode(7);
        c1.left = c2;
        c1.right = c3;
        c2.left = c4;
        c2.right = c5;
        c3.left = c6;
        c5.right = c7;
        out.println(treeHeight(c1));
    }

    public static int treeHeight(TreeNode root) {
        if (root == null)
            return -1;
        int leftHeight = treeHeight(root.left);
        int rightHeight = treeHeight(root.right);

        if (leftHeight > rightHeight)
            return leftHeight + 1;
        else
            return rightHeight + 1;
    }

    public static void isBinaryTreeTest() {
        //    6
        //   / \
        //  4   7
        // True
        TreeNode a1 = new TreeNode(6);
        TreeNode a2 = new TreeNode(4);
        TreeNode a3 = new TreeNode(7);
        a1.left = a2;
        a1.right = a3;
        out.println(isBinaryTree(a1));

        //    6
        //   / \
        //  4   5
        // False
        TreeNode b1 = new TreeNode(6);
        TreeNode b2 = new TreeNode(4);
        TreeNode b3 = new TreeNode(5);
        b1.left = b2;
        b1.right = b3;
        out.println(isBinaryTree(b1));

        //      6
        //    /  \
        //   4    8
        //  / \  /
        // 3   7 7
        // False
        TreeNode c1 = new TreeNode(6);
        TreeNode c2 = new TreeNode(4);
        TreeNode c3 = new TreeNode(8);
        TreeNode c4 = new TreeNode(3);
        TreeNode c5 = new TreeNode(7);
        TreeNode c6 = new TreeNode(7);
        c1.left = c2;
        c1.right = c3;
        c2.left = c4;
        c2.right = c5;
        c3.left = c6;
        out.println(isBinaryTree(c1));

        //      4
        //    /  \
        //   2    5
        //  / \
        // 1   5
        TreeNode d1 = new TreeNode(4);
        TreeNode d2 = new TreeNode(2);
        TreeNode d3 = new TreeNode(5);
        TreeNode d4 = new TreeNode(1);
        TreeNode d5 = new TreeNode(5);
        d1.left = d2;
        d1.right = d3;
        d2.left = d4;
        d2.right = d5;
        out.println(isBinaryTree(d1));
    }

    public static boolean isBinaryTree(TreeNode root) {
        return isBinaryTreeHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean isBinaryTreeHelper(TreeNode root, int min, int max) {
        if (root == null)
            return true;

        if (root.value < min || root.value > max)
            return false;

        return (isBinaryTreeHelper(root.left, min, root.value) && isBinaryTreeHelper(root.right, root.value, max));
    }

}
