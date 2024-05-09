package person.man.zhouyuqing;

import java.util.ArrayList;
import java.util.HashMap;

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
        while (l<r){
            char t = s[l];
            s[l] = s[r];
            s[r] = t;
            l++;
            r--;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

    }
}
