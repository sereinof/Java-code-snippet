package person.man.zhouyuqing;

public class SameTree {
    public boolean isSameTree = true;

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null & q != null) {
            return false;
        }
        if (p != null & q == null) {
            return false;
        }
        if (p.val != q.val) {
            this.isSameTree = false;
            return false;
        }
        isSameTree = isSameTree(p.left, q.left);
        if (!isSameTree) {
            return false;
        }
        isSameTree = isSameTree(p.right, q.right);
        return this.isSameTree;
    }
}
