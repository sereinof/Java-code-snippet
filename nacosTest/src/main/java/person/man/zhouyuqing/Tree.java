package person.man.zhouyuqing;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

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
        if(root==null){
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
}
