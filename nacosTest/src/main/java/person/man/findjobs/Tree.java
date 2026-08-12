package person.man.findjobs;

public class Tree {
    public int maxDepth(TreeNode root) {
        int deepth = 0;
        int deeptR = R(root.right);
        int deeptL = R(root.left);
        return Math.max(deeptR, deeptL);
    }

    public int R(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return Math.max(R(node.left) + 1, R(node.right) + 1);
        }
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null) return true;
        if (p == null || q == null) {
            return false;
        }
        if (p.val == q.val) return H(p.left, q.left) && H(q.right, p.right);
        return false;
    }

    public boolean H(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a != null && b != null) {
            if (a.val != b.val) return false;
            return H(a.left, b.left) && H(a.right, b.right);
        }
        return false;
    }


    public TreeNode invertTree(TreeNode root) {

    }
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
