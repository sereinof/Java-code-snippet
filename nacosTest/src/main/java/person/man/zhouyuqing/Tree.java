package person.man.zhouyuqing;

import java.util.*;

public class Tree {
    int[] inorder;
    int[] postorder;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        TreeNode root = buldTree1(0, inorder.length - 1, 0, postorder.length - 1);
        return root;
    }

    public TreeNode buldTree1(int index1, int index2, int index3, int index4) {
        if (index1 > index2) {
            return null;
        }
        if (index1 == index2) {
            return new TreeNode(inorder[index1]);
        }
        TreeNode root = new TreeNode(postorder[index4]);
        int Rl = 0;
        for (int i = index1; i <= index2; i++) {
            if (inorder[i] == postorder[index4]) {
                Rl = i - index1;
            }
        }
        root.left = buldTree1(index1, index1 + Rl - 1, index3, index3 + Rl - 1);
        root.right = buldTree1(index1 + Rl + 1, index2, index3 + Rl, index4 - 1);
        return root;
    }

    //二叉树的层序遍历
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        //借助队列吧
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (i != size - 1) {
                    node.next = queue.peek();
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }

    int sum = 0;

    public int sumNumbers(TreeNode root) {

        dfsSum(root, 0);
        return sum;
    }

    public void dfsSum(TreeNode node, int s) {

        if (node.left == null && node.right == null) {
            sum += (s * 10 + node.val);
        }
        if (node.left != null) {
            dfsSum(node.left, s * 10 + node.val);
        }
        if (node.right != null) {
            dfsSum(node.right, s * 10 + node.val);
        }

    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == size - 1) {
                    res.add(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return res;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        boolean flag =true;
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> now = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                now.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            if(flag){
                res.add(now);
                flag=false;
            }else{
                Collections.reverse(now);
                res.add(now);
                flag=true;
            }
        }
        return res;
    }
}
