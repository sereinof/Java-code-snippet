package person.man.zhou;

import java.util.*;

public class May_17 {
    public static void main(String[] args) {
        //permuteUnique(new int[]{1, 2, 3});
        // int res = maxCoins(new int[]{3, 1, 5, 8});
        //System.out.println(res);
        String res = new May_17().minWindow("ADOBECODEBANC", "ABC");
        System.out.println(res);
    }

    HashMap map = new HashMap<>();
    HashMap<Character, Integer> map1 = new HashMap<>();
    int l = 0;
    int r = 0;
    int[] interve;
    int min = Integer.MAX_VALUE;

    public String minWindow(String s, String t) {
        for (int i = 0; i < t.length(); i++) {
            map1.put(t.charAt(i), 0);
        }
        expand(s);
        return s.substring(interve[0], interve[1] + 1);
    }

    public void expand(String s) {
        while (r < s.length()) {
            if (map1.containsKey(s.charAt(r))) {
                Character cha = s.charAt(r);
                map1.put(cha, map1.get(cha) + 1);
                if (check()) {
                    ///
                    if ((r - l) < min) {
                        min = r - l;
                        interve = new int[]{l, r};
                    }
                    shousuo(s);
                } else {

                    r++;
                }
            } else {
                r++;
            }
        }
    }

    public void shousuo(String s) {
        while (l <= r) {
            if (map1.containsKey(s.charAt(l))) {
                Character cha = s.charAt(l);
                map1.put(cha, map1.get(cha) - 1);
                l++;
                if (check()) {
                    if ((r - l) < min) {
                        min = r - l;
                        interve = new int[]{l, r};
                    }
                } else {
                    expand(s);
                }
            } else {
                l++;
                if ((r - l) < min) {
                    min = r - l;
                    interve = new int[]{l, r};
                }
            }
        }
    }

    public boolean check() {
        for (int value : map1.values()) {
            if (value == 0) {
                return false;
            }
        }
        return true;
    }

    ;

    class Item {
        ListNode node;
        int index;

        public Item(ListNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Item> queue = new PriorityQueue<Item>(Comparator.comparingInt((a) -> a.node.val));
        for (int i = 0; i < lists.length; i++) {
            Item item = new Item(lists[i], i);
            if (lists[i] != null) {
                queue.add(item);
                lists[i] = lists[i].next;
            }
        }
        ListNode dummy = new ListNode(-1);
        ListNode result = dummy;
        while (!queue.isEmpty()) {
            Item item = queue.poll();
            dummy.next = item.node;
            dummy = dummy.next;
            item.node.next = null;

            if (lists[item.index] != null) {
                queue.add(new Item(lists[item.index], item.index));
                lists[item.index] = lists[item.index].next;
            }

        }
        return result.next;
    }

    class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static int maxCoins(int[] nums) {
        int[][] dp = new int[nums.length + 2][nums.length + 2];
        int[] arr = new int[nums.length + 2];
        arr[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            arr[i + 1] = nums[i];
        }
        arr[arr.length - 1] = 1;
        nums = arr;
        int len = nums.length + 2;
        int n = 2;
        while (n <= nums.length) {
            for (int i = 0; i < len - 2 - n; i++) {
                //区间是 i,i+n
                int maxThisTurn = 0;
                for (int k = i + 1; k < i + n; k++) {
                    int val = dp[i][k] + nums[i] * nums[k] * nums[i + n] + dp[k][i + n];
                    if (val > maxThisTurn) {
                        maxThisTurn = val;
                    }
                }
                dp[i][i + n] = maxThisTurn;
            }
            n++;
        }

        return dp[0][len - 3];
    }


    static List<List<Integer>> res = new ArrayList<>();
    static boolean used[];
    static ArrayList<Integer> every = new ArrayList<>();

    public static List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        used = new boolean[nums.length];
        backtracking(nums, 0);
        return res;
    }

    public static void backtracking(int[] nums, int level) {
        if (level == nums.length) {
            List<Integer> thisTurn = new ArrayList<>();
            every.stream().forEach((a) -> {
                thisTurn.add(a);
            });
            res.add(thisTurn);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) continue;
                every.add(nums[i]);
                used[i] = true;
                backtracking(nums, level + 1);
                every.remove(every.size() - 1);
                used[i] = false;
            }
        }
    }
}
