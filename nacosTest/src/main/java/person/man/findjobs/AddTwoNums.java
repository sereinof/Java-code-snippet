package person.man.findjobs;

public class AddTwoNums {
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode pointer = head;
        int carry = 0;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                int val = l2.val + carry;
                int thisVal = val % 10;
                carry = val / 10;
                ListNode now = new ListNode(thisVal);
                pointer.next = now;
                pointer = pointer.next;
                l2 = l2.next;
            } else if (l2 == null) {
                int val = l1.val + carry;
                int thisVal = val % 10;
                carry = val / 10;
                ListNode now = new ListNode(thisVal);
                pointer.next = now;
                pointer = pointer.next;
                l1 = l1.next;
            } else {
                int val = l1.val + l2.val + carry;
                int thisVal = val % 10;
                carry = val / 10;
                ListNode now = new ListNode(thisVal);
                pointer.next=now;
                pointer= pointer.next;
                l1 = l1.next;
                l2 = l2.next;
            }
        }
        if(carry!=0){
            ListNode now = new ListNode(carry);
            pointer.next=now;
        }

        return head;
    }
}
