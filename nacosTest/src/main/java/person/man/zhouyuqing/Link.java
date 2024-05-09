package person.man.zhouyuqing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

public class Link {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        ListNode res = dummy;
        dummy.next = head;
        ListNode p = head;
        while (p != null) {
            if (p.val == val) {
                ListNode tmp = p.next;
                dummy.next = tmp;
                p = tmp;
            } else {
                p = p.next;
                dummy = dummy.next;
            }
        }
        return res.next;
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i : nums1) {
            map.put(i, i);
        }
        for (int j : nums2) {
            if (map.containsKey(j) && map.get(j) != -1) {
                res.add(j);
                map.put(j, -1);
            }
        }
        int[] res1 = res.stream().mapToInt(Integer::intValue).toArray();
        return res1;
    }

    public void reverseString(char[] s) {
        int l = 0;
        int r = s.length - 1;
        while (l < r) {
            char t = s[l];
            s[l] = s[r];
            s[r] = t;
            l++;
            r--;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashMap map = new HashMap();
        while (headA != null) {
            map.put(headA, 3);
            headA = headA.next;
        }
        while (headB != null) {
            if (map.containsKey(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    public char findTheDifference(String s, String t) {
        int res = 0;
        for (int i = 0; i < t.length(); i++) {
            res ^= t.charAt(i);
        }
        ;
        for (int i = 0; i < t.length(); i++) {
            res ^= t.charAt(i);
        }
        ;
        return (char) res;
    }

    public int[] sortedSquares(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] *= nums[i];
        }
        int[] asn = new int[nums.length];
        int l = 0;
        int r = nums.length - 1;
        int p = nums.length - 1;
        while (l < r) {
            if (nums[l] > nums[r]) {
                asn[p] = nums[l];
                p--;
            } else {
                asn[p] = nums[r];
                p--;
            }
        }
        return asn;
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        while (head != null && head.next != null) {
            if (head.val != head.next.val) {
                head = head.next;
                dummy = dummy.next;
            } else {
                ListNode tmp = head.next;
                head.next = null;
                dummy.next = tmp;
                head = tmp;
            }
        }
        return dummy.next;
    }

    public int[][] transpose(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return matrix;
        }
        int[][] ret = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                ret[j][i] = matrix[i][j];
            }
        }
        return ret;
    }

    public boolean lemonadeChange(int[] bills) {
        int five = 0;
        int ten = 0;
        int twenty = 0;
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                five++;
            } else if (bills[i] == 10) {
                if (five == 0) {
                    return false;
                } else {
                    five--;
                    ten++;
                }
            } else if (bills[i] == 20) {
                if (ten != 0) {
                    ten--;
                    if (five == 0) {
                        return false;
                    } else {
                        five--;
                    }
                    twenty++;
                } else {
                    if (five < 3) {
                        return false;
                    } else {
                        five -= 3;
                        twenty++;
                    }
                }
            }
        }
        return false;
    }

    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty()) {
                stack.push(s.charAt(i));
            } else {
                if (s.charAt(i) == stack.peek()) {
                    stack.pop();
                } else {
                    stack.push(s.charAt(i));
                }
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.insert(0, stack.pop());
        }
        return res.toString();
    }

    public int maxDepth(Node root) {
        int len = dfs(root);
        return len;
    }

    public int dfs(Node node) {
        int max = 0;
        for (Node n : node.children) {
            max = Math.max(max, dfs(n, ));
        }
        return max + 1;
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }


    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i - 2] + cost[i], dp[i - 1] + cost[i]);
        }
        return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
    }

    public int findLengthOfLCIS(int[] nums) {
        int l = 0;
        int r = 0;
        int max = 0;
        while (r < nums.length) {
            if (nums[r + 1] < nums[r]) {
                r++;
            } else {
                r++;
                l = r;
                max = Math.max(r - l, max);
            }
        }
        return max;
    }

    public int[] findErrorNums(int[] nums) {
        int xOry = 0;//存储多的那个数和小的那个数的异或结果
        int n = 1;
        for (int i = 0; i < nums.length; i++) {
            xOry ^= nums[i];
            xOry ^= n;
            n++;
        }
        int lowBit = xOry & (-xOry);
        int nums1 = 0;
        int nums2 = 0;
        for (int num : nums) {
            if ((num & lowBit) == 1) {
                nums1 ^= num;
            } else {
                nums2 ^= num;
            }
        }
        for (int i = 1; i < nums.length + 1; i++) {
            System.out.println(i&lowBit);
            if ((i & lowBit) == 1) {
                nums1 ^= i;
            } else {
                nums2 ^= i;
            }
        }
        for (int num : nums) {
            if (num == nums1) {
                return new int[]{nums1, nums2};
            }
        }
        return new int[]{nums2, nums1};
    }
}
