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
        int[][] grid = new int[][]{
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0}
        };
        new Divide().construct(grid);

    }

    public Node construct(int[][] grid) {
        Node res = build(grid, 0, grid.length - 1, 0, grid[0].length - 1);
        return res;
    }

    private Node build(int[][] grid, int rowStart, int rowEnd, int colStart, int colEnd) {
        if (rowStart == rowEnd && colStart == colEnd) {
            return new Node(grid[rowStart][colEnd] == 1 ? true : false, true);
        }
        Node res = new Node();
        int rowMid = (rowStart + rowEnd) / 2;
        int colMid = (colStart + colEnd) / 2;
        Node topLeft = build(grid, rowStart, rowMid, colStart, colMid);
        Node topRight = build(grid, rowStart, rowMid, colMid + 1, colEnd);
        Node bottomLeft = build(grid, rowMid + 1, rowEnd, colStart, colMid);
        Node bottomRight = build(grid, rowMid + 1, rowEnd, colMid + 1, colEnd);
        if (topLeft.val == topRight.val && topRight.val == bottomRight.val && bottomRight.val == bottomLeft.val
                && topRight.isLeaf && topLeft.isLeaf && bottomRight.isLeaf && bottomLeft.isLeaf) {
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

    public ListNode mergeKLists(ListNode[] lists) {
        return doMerge(lists, 0, lists.length - 1);
    }

    private ListNode doMerge(ListNode[] lists, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) return lists[0];
        int mid = (start + end) / 2;
        ListNode dummy = new ListNode(-1);
        ListNode ptr = dummy;
        ListNode left = doMerge(lists, start, mid);
        ListNode right = doMerge(lists, mid + 1, end);
        while (left != null && right != null) {
            if (left.val < right.val) {
                ptr.next = left;
                left = left.next;
            } else {
                ptr.next = right;
                right = right.next;
            }
            ptr = ptr.next;
        }
        if (left != null) {
            ptr.next = left;
        } else if (right != null) {
            ptr.next = right;
        }
        return dummy.next;
    }

    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int prevRestut = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (prevRestut <= 0) {
                prevRestut = nums[i];
                res = Math.max(res, prevRestut);
            } else {
                prevRestut += nums[i];
                res = Math.max(res, prevRestut);
            }
        }
        return res;
    }

    public int maxSubarraySumCircular(int[] nums) {
        int res = nums[0];
        int prevRestut = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (prevRestut <= 0) {
                prevRestut = nums[i];
                res = Math.max(res, prevRestut);
            } else {
                prevRestut += nums[i];
                res = Math.max(res, prevRestut);
            }
        }
        int res_min = nums[0];
        int sum = nums[0];
        int prev = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];
            prev = Math.min(prev + nums[i], nums[i]);
            res_min = Math.min(res_min, prev);
        }
        return Math.max(res, sum - res_min);
    }


    public int searchInsert(int[] nums, int target) {
        int l = -1;
        int r = nums.length;
        while (l + 1 != r) {
            int mid = l + (l - r) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int l = -1;
        int r = matrix.length;
        while (l + 1 != r) {
            int mid = l + (r - l) / 2;
            if (matrix[mid][0] == target) return true;
            if (matrix[mid][0] > target) {
                r = mid;
            } else {
                l = mid;
            }
        }
        int row = l;
        l = 0;
        r = matrix[0].length;
        while (l + 1 != r) {
            int mid = l + (r - l) / 2;
            if (matrix[row][mid] == target) return true;
            if (matrix[row][mid] > target) {
                r=mid;
            }else {
                l=mid;
            }
        }
        return false;
    }
    public int findPeakElement(int[] nums) {

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
