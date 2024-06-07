package person.man.zhou;

import java.util.*;

class MyStack {
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
