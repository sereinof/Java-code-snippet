package person.man.findjobs;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
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
        TreeNode node = new TreeNode(postorder[postorder.length - 1]);
        help.push(node);
        int in_idex = postorder.length - 1;
        for (int i = postorder.length - 2; i >= 0; i--) {
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

    public Node connect1(Node root) {
        if (root == null) return null;
        Queue<Node> queue = new ArrayDeque<>();
        int size = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (i != size - 1) {
                    cur.next = queue.peek();
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return root;
    }

    public Node connect(Node root) {
        if (root == null) return null;
        Node levelRoot = root;
        while (levelRoot != null) {
            Node dummy = new Node(-1);
            Node pre = dummy;
            while (levelRoot != null) {
                if (levelRoot.left != null) {
                    dummy.next = levelRoot.left;
                    dummy = dummy.next;
                }
                if (levelRoot.right != null) {
                    dummy.next = levelRoot.right;
                    dummy = dummy.next;
                }
                levelRoot = levelRoot.next;
            }

            levelRoot = pre.next;
        }
        return root;
    }

    TreeNode dummy = new TreeNode(-1);

    public void flatten(TreeNode root) {// 手写Morris啊
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode node = cur.left;
                TreeNode help = null;
                while (node != null) {
                    help = node;
                    node = node.right;
                }
                help.right = cur.right;
                TreeNode left = cur.left;
                cur.right = left;
                cur.left = null;
                cur = left;
            } else {
                cur = cur.right;
            }
        }
    }

    public TreeNode make(TreeNode node) {
        if (node == null) return null;
        TreeNode right = node.right;
        TreeNode left = node.left;
        node.left = null;
        dummy.right = node;
        dummy = dummy.right;
        make(left);
        make(right);
        return null;
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> args = new Stack<>();
        stack.push(root);
        args.push(targetSum);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            Integer sum = args.pop();
            if (node.left == null && node.right == null) {
                if (node.val == sum) {
                    return true;
                }
            }
            if (node.right != null) {
                stack.push(node.right);
                args.push(sum - node.val);
            }
            if (node.left != null) {
                stack.push(node.left);
                args.push(sum - node.val);
            }
        }
        return false;
    }

    public int sumNumbers(TreeNode root) {
        int res = 0;
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            Stack<Integer> arges = new Stack<>();
            stack.push(root);
            arges.push(0);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                int preSum = arges.pop() * 10 + node.val;
                if (node.left == null && node.right == null) {
                    res += preSum;
                }
                if (node.right != null) {
                    stack.push(node.right);
                    arges.push(preSum * 10);
                }
                if (node.left != null) {
                    stack.push(node.left);
                    arges.push(preSum * 10);
                }
            }
        }
        return res;
    }

    Integer res = 0;
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return res;
    }
    public int dfs(TreeNode root) {
        if (root == null) return -1000000;
        int left = dfs(root.left);
        int right = dfs(root.right);
        int currentMax = Math.max(root.val, Math.max(root.val + left, Math.max(root.val + right, Math.max(left, Math.max(right, root.val + left + right)))));
        res = Math.max(res, currentMax);
        return Math.max(root.val + left, Math.max(root.val + right,root.val));
    }

    public BSTIterator(TreeNode root) {

    }

    public int next() {

    }

    public boolean hasNext() {

    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    ;

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
