package person.man.zhouyuqing;

public class PathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            if (targetSum == root.val) {
                return true;
            }
        }
        //题目要求根节点到叶子节点

        boolean b1 = hasPathSum(root.right, targetSum - root.val);
        if (b1) {
            return true;
        }
        boolean b2 = hasPathSum(root.left, targetSum - root.val);
        if (b2) {
            return true;
        }
        return false;
    }
}
