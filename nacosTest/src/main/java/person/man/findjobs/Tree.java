package person.man.findjobs;

import java.util.HashMap;
import java.util.Stack;

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
        if (p == null && q == null) return true;
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
        if (root == null) return null;
        TreeNode node = root.left;
        root.left = root.right;
        root.right = node;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    public boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;
        return isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }

    public static void main(String[] args) {
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        TreeNode res = new Tree().buildTree(preorder, inorder);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) return null;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        TreeNode root = new TreeNode(preorder[0]);
        int index = map.get(preorder[0]);
        root.left = help(1, index, 0, index - 1, preorder, inorder, map);
        root.right = help(index + 1, preorder.length - 1,
                index + 1, inorder.length - 1, preorder, inorder, map);
        return root;
    }

    public TreeNode help(int l, int r, int l1, int r1, int[] preorder,
                         int[] inoreder, HashMap<Integer, Integer> map) {
        //数组无重复元素
        if (r < l || r1 < l1) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[l]);
        int index = map.get(preorder[l]);
        root.left = help(l + 1, l + index - l1,
                l1, index - 1, preorder, inoreder, map);
        root.right = help(l + index - l1 + 1, r, index + 1, r1, preorder, inoreder, map);
        return root;
    }

    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0) return null;
        Stack<TreeNode> help = new Stack<>();
        TreeNode node = new TreeNode(postorder[postorder.length-1]);
        help.push(node);
        int in_idex = postorder.length-1;
        for (int i = postorder.length-2; i >=0;  i--) {
            TreeNode cur = new TreeNode(postorder[i]);
            if (help.peek().val != inorder[in_idex]) {
                help.peek().right = cur;

            } else {
                TreeNode pop = null;
                while (!help.isEmpty() && help.peek().val == inorder[in_idex]) {
                    pop = help.pop();//
                    in_idex--;
                }
                pop.left = cur;
            }
            help.push(cur);
        }
        return node;
    }
    public Node connect(Node root) {

    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

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
