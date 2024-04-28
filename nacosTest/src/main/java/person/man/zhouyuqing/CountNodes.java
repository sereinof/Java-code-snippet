package person.man.zhouyuqing;

public class CountNodes {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if(root.left==null&&root.right==null){
            return 1;
        }

        int count_left = countLevel(root.left);
        int count_right = countLevel(root.right);
        if (count_left == count_right) {
            return countNodes(root.right) + (1 << count_left);
        } else {
            return countNodes(root.left) + (1 << count_right);
        }

    }

    public int countLevel(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 1;
        while (root.left != null) {
            res++;
            root = root.left;
        }
        return res;
    }
}
