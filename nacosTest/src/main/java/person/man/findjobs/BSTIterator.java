package person.man.findjobs;

import java.util.*;

public class BSTIterator {
    Stack<TreeNode> stack = new Stack<>();

    public BSTIterator(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    public int next() {
        TreeNode now = stack.pop();
        int res = now.val;
        if (now.right != null) {
            TreeNode right = now.right;
            while (right != null) {
                stack.push(right);
                right = right.left;
            }
        }
        return res;
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        if (leftDepth == rightDepth) {
            return 1 << leftDepth + countNodes(root.right);
        } else {
            return 1 << rightDepth + countNodes(root.left);
        }
    }

    public int getDepth(TreeNode root) {
        int res = 0;
        while (root != null) {
            res++;
            root = root.left;
        }
        return res;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return H(root, p, q);
    }

    private TreeNode H(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;
        TreeNode rightRes = H(root.right, p, q);
        TreeNode leftRes = H(root.left, p, q);
        if (rightRes != null && leftRes != null) {
            return root;
        }
        if (rightRes != null) {
            return rightRes;
        }
        if (leftRes != null) {
            return leftRes;
        }
        return null;
    }

    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new ArrayDeque();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (i == size - 1) {
                    res.add(cur.val);
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return res;
    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int count = 0;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                count += cur.val;
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            res.add(((double) count / (double) size));
        }
        return res;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean flag = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelStock = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                levelStock.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            if (flag) {
                res.add(levelStock);
                flag = !flag;
            } else {
                Collections.reverse(levelStock);
                res.add(levelStock);
                flag = !flag;
            }
        }
        return res;
    }

    public int getMinimumDifference(TreeNode root) {
        TreeNode pre = null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root.left;
        stack.push(root);
        int ans = Integer.MAX_VALUE;
        while (cur!=null!stack.isEmpty()) {
            if (cur != null) {
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }
            }
            cur = stack.pop();
            if (pre != null) {
                ans = Math.min(ans, cur.val - pre.val);
            }
            pre = cur;
            cur = cur.right;
        }
        return ans;
    }

    public int kthSmallest(TreeNode root, int k) {

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root.left;
        stack.push(root);
        int count = 0;
        while (cur!=null||!stack.isEmpty()) {
            if (cur != null) {
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }
            }
            cur = stack.pop();
            count++;
            if (count == k) {
                return cur.val;
            }
            cur = cur.right;
        }
        return 0;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
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
