package person.man.zhou;


import java.util.*;
import java.util.concurrent.Callable;

class MyStack {
    public static void main(String[] args) {
        // new Thread();
        findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"});
    }

    public int romanToInt(String s) {
        //下面这种方法太low了 现在进行改写
        HashMap<String, Integer> map = new HashMap();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);
        int i = 0;
        int res = 0;
        while (i < s.length()) {
            if (i < s.length() - 1) {
                if (map.containsKey(s.substring(i, i + 2))) {
                    res += map.get(s.substring(i, i + 2));
                } else {
                    res += map.get(s.charAt(i));
                }
                i++;
                i++;
            } else {
                res += map.get(s.charAt(i)+"");
                i++;
            }
        }
        return res;
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        HashMap<String, Integer> total = new HashMap();
        for (String word : words) {
            total.put(word, total.getOrDefault(word, 0) + 1);//初始化哈希表
        }
        int single_word_length = words[0].length();
        int total_length = single_word_length * words.length;
        //这是用来记录所谓的汉明距离的吗？

        for (int i = 0; i < single_word_length; i++) {
            HashMap<String, Integer> window = new HashMap();
            int windowSize = 0;
            int cnt = 0;
            for (int j = i; j <= s.length() - single_word_length; j += single_word_length) {
                String thisTime = s.substring(j, j + single_word_length);
                if (windowSize >= words.length) {
                    String first = s.substring(j - total_length, j - total_length + single_word_length);
                    window.put(first, window.get(first) - 1);
                    windowSize--;
                    if (window.get(first) < total.getOrDefault(first, 0)) {
                        cnt--;
                    }
                    //这里还要看情况更新cnt的值
                }
                windowSize++;
                window.put(thisTime, window.getOrDefault(thisTime, 0) + 1);//更新窗口的值
                if (window.get(thisTime) <= total.getOrDefault(thisTime, 0)) {
                    cnt++;
                }
                //并且还需要更新cnt的值 视情况
                if (cnt == words.length) {
                    res.add(j - single_word_length * words.length + single_word_length);
                }
            }
        }
        return res;
    }

    public int[] searchRange(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;//向下取整；
            if (nums[mid] == target) {
                int a = mid;
                int b = mid;
                while (a - 1 >= 0 && nums[a - 1] == nums[a]) {
                    a--;
                }
                while (b + 1 <= nums.length - 1 && nums[b + 1] == nums[b]) {
                    b++;
                }
                return new int[]{a, b};
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return new int[]{l, r};
    }

    TreeNode before;
    TreeNode mid;
    TreeNode after;
    TreeNode x = null;
    TreeNode y = null;

    public void recoverTree(TreeNode root) {
//1，2，3，4，5，6，7
//1，2，3，7，5，6，4  --交换了7，4
//1，2，4，3，5，6，7  --交换了3，4
//1,5,3,4,5,2,7
//需要中序遍历维持三个变量卧槽

        //老老实实中序遍历吧
        //这题我想的太复杂了 其实是可以直接改val属性的  想太复杂了
        //可以用Morris遍历来优化而已 不过那个遍历我想我用不上呢
        inorder(root);
        if (after.val < mid.val) {
            y = mid;
        }
        int val = x.val;
        x.val = y.val;
        y.val = val;
    }

    public void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        before = mid;
        mid = after;
        after = node;
        judge();
        inorder(node.right);
    }

    public void judge() {
        if (mid == null) return;
        if (before == null) {
            if (mid.val > after.val) {
                if (x == null) {
                    x = mid;
                } else {
                    y = mid;
                }
            }
            return;
        }
        if ((before.val < mid.val && mid.val > after.val) || before.val > mid.val && mid.val < after.val) {
            if (x == null) {
                x = mid;
            } else {
                y = mid;
            }
        }
    }

    public int reverse(int x) {
        boolean positive = x > 0 ? true : false;
        int res = 0;
        while (x != 0) {
            if (positive) {
                if (x > Integer.MAX_VALUE / 10) {
                    return 0;
                }
                res = res * 10 + x % 10;
                x = x / 10;
            } else {
                if (x < Integer.MIN_VALUE / 10) {
                    return 0;
                }
                res = res * 10 + x % 10;
                x = x / 10;
            }
        }
        return res;
    }

    public String convert(String s, int numRows) {//z字形打印字符串卧槽
        if (numRows == 1) {
            return s;
        }
        StringBuilder[] arr = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            arr[i] = new StringBuilder();
        }
        boolean flag = true;
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            if (flag) {
                arr[index].append(s.charAt(i));
                index++;
            } else {
                arr[i].append(s.charAt(i));
                index--;
            }
            if (index == 0 || index == numRows - 1) {
                flag = !flag;
            }
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder g : arr) {
            res.append(g.toString());
        }
        return res.toString();
    }

    public List<List<Integer>> threeSum(int[] nums) {
        ArrayList res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        if (nums[0] > 0) {
            return res;
        }
        Arrays.sort(nums);//先排序

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = -nums[i];
            int l = i + 1;
            int r = nums.length - 1;//来吧 双指针；；；
            while (l < r) {
                int sum = nums[l] + nums[r];
                if (sum > target) {
                    r--;
                } else if (sum < target) {
                    l++;
                } else {//此时是相等的；
                    res.add(new int[]{nums[i], nums[l], nums[r]});
                    //这里还需要处理重复逻辑
                    while (l < r && nums[l + 1] == nums[l]) {
                        l++;
                    }
                    while (l < r && nums[r - 1] == nums[r]) {
                        r--;
                    }
                    l++;
                    r--;
                }
            }
        }
        return res;
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode res = new ListNode(-1);
        ListNode pointer = res;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                int val = (l2.val + carry) % 10;
                carry = (l2.val + carry) / 10;
                pointer.next = new ListNode(val);
                pointer = pointer.next;
                l2 = l2.next;
                continue;
            }
            if (l2 == null) {
                int val = (l1.val + carry) % 10;
                carry = (l1.val + carry) / 10;
                pointer.next = new ListNode(val);
                pointer = pointer.next;
                l1 = l1.next;
                continue;
            }
            int val = (carry + l1.val + l2.val) % 10;
            carry = (carry + l1.val + l2.val) / 10;
            pointer.next = new ListNode(val);
            pointer = pointer.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        if (carry != 0) {
            pointer.next = new ListNode(carry);
        }
        return res.next;
    }

    Queue<Integer> A = new LinkedList();
    Queue<Integer> B = new LinkedList();

    public MyStack() {

    }

    public void push(int x) {
        if (!A.isEmpty()) {
            A.offer(x);
        } else {
            B.offer(x);
        }
    }

    public int pop() {
        if (A.isEmpty()) {
            while (!B.isEmpty()) {
                if (B.size() > 1) {
                    A.offer(B.poll());
                } else {
                    return B.poll();
                }
            }
        } else {
            while (!A.isEmpty()) {
                if (A.size() > 1) {
                    B.offer(A.poll());
                } else {
                    return A.poll();
                }
            }
        }
        return 0;
    }

    public int top() {
        HashMap map = new HashMap();
        if (A.isEmpty()) {
            while (!B.isEmpty()) {
                if (B.size() > 1) {
                    A.offer(B.poll());
                } else {
                    int a = B.poll();
                    A.offer(a);
                    return a;
                }
            }
        } else {
            while (!A.isEmpty()) {
                if (A.size() > 1) {
                    B.offer(A.poll());
                } else {
                    int a = A.poll();
                    B.offer(a);
                    return a;
                }
            }
        }
        return 0;
    }

    public boolean empty() {
        return A.isEmpty() && B.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
