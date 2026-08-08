package person.man.findjobs;

public class LinkedListC {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode s = head;
        ListNode q = head;
        while (s != null && s.next != null && q != null && q.next != null && q.next.next != null) {
            s = s.next;
            q = q.next;
            q = q.next;
            if (s == q) {
                return true;
            }
        }

        return false;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
