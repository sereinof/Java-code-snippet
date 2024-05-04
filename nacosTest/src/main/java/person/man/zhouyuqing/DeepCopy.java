package person.man.zhouyuqing;

import java.util.List;

public class DeepCopy {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node head_re = head;
        Node headRan = head;
        while (head != null) {
            Node newNode = new Node(head.val);
            Node next = head.next;
            head.next = newNode;
            newNode.next = next;
            head = head.next.next;//构建 A->A` ->B->B`
        }
        while (headRan != null) {
            if (headRan.random != null) {
                headRan.next.random = headRan.random.next;
            }
            headRan = headRan.next.next;
        }
        Node returnw = head_re.next;
        Node res = head_re.next;
        head_re.next = res.next;
        while (res != null) {
            Node next = res.next.next;
            res.next.next = next.next;
            res.next = next;
            res = res.next;
            if (res.next == null) {
                break;
            }
        }
        return returnw;
    }


    public ListNode reverseBetween(ListNode head, int left, int right) {
//来吧 翻转链表
        ListNode remeber = head;
        ListNode leftNode = head;
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        leftNode = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            leftNode = leftNode.next;
        }
        ListNode mid = leftNode.next;
        ListNode Rp = leftNode.next.next;
        for (int i = 0; i < right - left; i++) {
            ListNode next = Rp.next;
            mid.next = next;
            Rp.next = null;//先断开
            //插入
            ListNode subHead = leftNode.next;
            leftNode.next = Rp;
            Rp.next = subHead;
            Rp = next;
        }

        return head;
    }

}


class Node {
    int val;
    Node next;
    Node random;
    boolean isNew = false;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
