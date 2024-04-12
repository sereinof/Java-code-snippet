package person.man.zhouyuqing;

public class sortLinkedList {
    public static void main(String[] args) {

        listNode head = new listNode();
        listNode head_res = head;
        head.next = new listNode(4);
        head = head.next;
        head.next = new listNode(2);
        head = head.next;
        head.next = new listNode(1);
        head = head.next;
        head.next = new listNode(3);
        new sortLinkedList().sortList(head_res);
        System.out.println("nihao");
    }

    public listNode sortList(listNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return null;
        }
        if (head.next != null && head.next.next == null) {// 当链表分割为只有两个节点的时候进行排序
            if (head.val > head.next.val) {
                listNode res = head.next;
                res.next = head;
                head.next = null;
                return res;
            } else {
                return head;
            }
        }
        listNode slow = head;
        listNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        } // 实际上这个fast指针没有用处了吧

        // 找到中点需要断开链表吗？
        listNode slow_next = new listNode();
        slow_next.next = slow.next;
        slow.next = null;// 断开链表
        listNode node1 = sortList(head);

        listNode node2 = sortList(slow_next);
        listNode res = merge(node1, node2);
        return res;
    }

    public listNode merge(listNode node1, listNode node2) {
        listNode head = new listNode();
        listNode point = head;// 定义两个指针 一个头指针 一个滑动指针
        while (node1 != null || node2 != null) {
            if (node1 != null && node2 != null) {
                if (node1.val > node2.val) {
                    point.next = node2;
                    node2 = node2.next;
                    point = point.next;
                } else {
                    point.next = node1;
                    node1 = node1.next;
                    point = point.next;
                }
            }
            if (node1 == null) {
                point.next = node2;
                node2 = node2.next;
                point = point.next;
            }
            if (node2 == null) {
                point.next = node1;
                node1 = node1.next;
                point = point.next;
            }
        }
        return head;
    }

}

class listNode {
    int val;
    listNode next;

    listNode() {
    }

    listNode(int val) {
        this.val = val;
    }

    listNode(int val, listNode next) {
        this.val = val;
        this.next = next;
    }
}