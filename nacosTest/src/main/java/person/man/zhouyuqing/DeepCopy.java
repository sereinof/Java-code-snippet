package person.man.zhouyuqing;

import org.w3c.dom.ls.LSInput;

import java.util.List;

//好几道链表的题目
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

    public ListNode deleteDuplicates(ListNode head) {
        ListNode nodeR = head;
        ListNode dummy = new ListNode(-1);
        ListNode res = dummy;
        dummy.next = head;
        while (head != null) {
            boolean flag = false;
            while (head.next != null && head.next.val == head.val) {
                flag = true;
                ListNode n1 = head.next.next;
                head.next.next = null;
                head.next = n1;
            }
            if (flag) {
                ListNode nn = head.next;
                head.next = null;
                dummy.next = nn;
                head = nn;

            } else {
                dummy = dummy.next;
                head = head.next;
            }

        }
        return res.next;
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head.next == null) {
            return head;
        }
        ListNode h = head;
        int len = 0;
        while (head != null) {
            len++;
            ListNode t = head.next;
            if (t == null) {
                break;//成环
            } else {
                head = head.next;
            }
        }
        ListNode h1 = h;
        int Rk = k % len;
        if (Rk == 0) {
            return h;
        }
        for (int i = 0; i < len - Rk - 1; i++) {
            h1 = h1.next;
        }
        ListNode res = h1.next;
        h1.next = null;
        head.next = h;
        return res;
    }

    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummp = new ListNode(-1);
        ListNode res = dummp;
        dummp.next = head;
        while (dummp.next.val < x) {
            dummp = dummp.next;//在第一个大于x的节点前停下来
            if (dummp.next == null) {
                return head;
            }
        }
        ListNode n = dummp.next;
        ListNode dump1 = new ListNode(-2);
        dump1.next = n;
        while (n != null) {
            if (n.val >= x) {
                dump1 = dump1.next;//记录前驱节点
                n = n.next;
            } else {
                ListNode y = n.next;
                n.next = null;
                dump1.next = y;

                ListNode y1 = dummp.next;
                dummp.next = n;
                n.next = y1;
                dummp = dummp.next;

                n = y;
                //交换
            }
        }
        return res.next;
    }

}


class Node {
    public Node left;
    public Node right;
    public Node next;
    int val;
    Node random;
    public List<Node> neighbors;
    boolean isNew = false;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
