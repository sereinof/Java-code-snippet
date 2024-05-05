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
        boolean flag = true;
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
            if (flag) {
                res.add(now);
                flag = false;
            } else {
                Collections.reverse(now);
                res.add(now);
                flag = true;
            }
        }
        return res;
    }

    public int kthSmallest(TreeNode root, int k) {
        int res = 0;
//这题用中序遍历可以直接秒
        //才发现非递归的中序遍历好牛逼呀 估计是要背的 这种算法
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode cur = root;
        while (!stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode n = stack.pop();
            res++;
            if (res == k) {
                return n.val;
            }
            cur = cur.right;
        }
        return res;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        return res;
    }

    public void solve(char[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return;
        }
        List<List<Integer>> boundary = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i == 0 || i == board.length - 1 || j == 0 || j == board[0].length - 1) {
                    if (board[i][j] == 'O') {
                        List<Integer> now = new ArrayList<>();
                        now.add(i);
                        now.add(j);
                        boundary.add(now);
                    }
                }
            }
        }
        for (List s : boundary) {
            dfs(board, (int) s.get(0), (int) s.get(1));
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != 'R') {
                    board[i][j] = 'X';
                } else {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void dfs(char[][] board, int index1, int index2) {
        if (!(index1 >= 0 && index1 <= board.length - 1)) {
            return;
        }
        if (!(index2 >= 0 && index2 <= board[0].length - 1)) {
            return;
        }
        if (board[index1][index2] == 'O') {
            board[index1][index2] = 'R';
            dfs(board, index1 - 1, index2);
            dfs(board, index1 + 1, index2);
            dfs(board, index1, index2 - 1);
            dfs(board, index1, index2 + 1);
        }

    }
}
