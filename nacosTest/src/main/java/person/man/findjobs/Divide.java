package person.man.findjobs;

public class Divide {
    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root = null;
        root = divide(nums, 0, nums.length - 1);
        return root;
    }

    private TreeNode divide(int[] nums, int i, int j) {
        if (i > j) {
            return null;
        }
        if (i == j) {
            return new TreeNode(nums[i]);
        }
        int mid = (i + j) / 2;
        TreeNode cur = new TreeNode(nums[mid]);
        cur.left = divide(nums, i, mid - 1);
        cur.right = divide(nums, mid + 1, j);
        return cur;
    }

    public ListNode sortList(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;

        ListNode slow = head;
        ListNode fast = head;
        ListNode prv = null;
        boolean flag = false;
        while (fast != null) {
            fast = fast.next;
            if (flag) {
                prv = slow;
                slow = slow.next;
            }
            flag = !flag;
        }
        prv.next = null;
        ListNode head1 = sortList(head);
        ListNode head2 = sortList(slow);
        ListNode dummy = new ListNode(-1);
        ListNode ptr = dummy;
        while (head2 != null || head1 != null) {
            if (head2 == null) {
                ptr.next = head1;
                head1 = head1.next;
            } else if (head1 == null) {
                ptr.next = head2;
                head2 = head2.next;
            } else if (head1 != null && head2 != null) {
                if (head2.val < head1.val) {
                    ptr.next = head2;
                    head2 = head2.next;
                } else {
                    ptr.next = head1;
                    head1 = head1.next;
                }
            }
            ptr = ptr.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0, 1}, {1, 0}};
        new Divide().construct(grid);

    }

    public Node construct(int[][] grid) {
        Node res = build(grid, 0, grid.length, 0, grid[0].length);
        return res;
    }

    private Node build(int[][] grid, int i, int length, int j, int lengthj) {
        if (i == length) {
            return new Node(grid[i][j] == 1 ? true : false, true);
        }
        Node res = new Node();
        Node topLeft = build(grid, 0, length / 2, 0, lengthj / 2);
        Node topRight = build(grid, 0, length / 2, (lengthj + 1) / 2, lengthj);
        Node bottomLeft = build(grid, (length + 1) / 2, length, 0, lengthj / 2);
        Node bottomRight = build(grid, (length + 1) / 2, length, (lengthj + 1) / 2, lengthj);
        if (topLeft.val == topRight.val && topRight.val == bottomRight.val && bottomRight.val == bottomLeft.val) {
            res.isLeaf = true;
            res.val = topLeft.val;
            return res;
        } else {
            res.isLeaf = false;
            res.topRight = topRight;
            res.topLeft = topLeft;
            res.bottomRight = bottomRight;
            res.bottomLeft = bottomLeft;
            return res;
        }
    }


    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
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
