package person.man.findjobs;

import java.util.HashMap;
import java.util.Map;

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
        int carry = 0;
        ListNode res = new ListNode(0);
        ListNode head = res;
        while (l1 != null || l2 != null) {
            int sum = 0;
            if (l1 == null) {
                sum = carry + l2.val;
                l2 = l2.next;

            } else if (l2 == null) {
                sum = carry + l1.val;
                l1 = l1.next;
            } else {
                sum = carry + l1.val + l2.val;
                l1 = l1.next;
                l2 = l2.next;
            }
            carry = sum / 10;
            int currentVal = sum % 10;
            ListNode node = new ListNode(currentVal);
            head.next = node;
            head = head.next;
        }
        if (carry != 0) {
            ListNode node = new ListNode(carry);
            head.next = node;
        }
        return res;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode res = dummy;
        while (list1 != null || list2 != null) {
            if (list1 == null) {
                dummy.next = list2;
                list2 = list2.next;
            } else if (list2 == null) {
                dummy.next = list1;
                list1 = list1.next;
            } else if (list2.val <= list1.val) {
                dummy.next = list2;
                list2 = list2.next;
            } else {
                dummy.next = list1;
                list1 = list1.next;
            }
            dummy = dummy.next;
        }
        return res.next;
    }

    // ************** 核心工具方法：将二维数组转为链表 **************
    public Node buildLinkedList(Object[][] data) {
        // 如果数据为空，直接返回 null
        if (data == null || data.length == 0) {
            return null;
        }

        int n = data.length;
        Node[] nodes = new Node[n];

        // 1. 第一遍：创建所有节点（只取 val），存入数组
        for (int i = 0; i < n; i++) {
            // data[i][0] 是节点值（Integer 类型）
            int val = (int) data[i][0];
            nodes[i] = new Node(val);
        }

        // 2. 第二遍：连接 next 和 random 指针
        for (int i = 0; i < n; i++) {
            // 连接 next 指针（除了最后一个节点）
            if (i < n - 1) {
                nodes[i].next = nodes[i + 1];
            }

            // 连接 random 指针
            // data[i][1] 可能是 Integer 索引，也可能是 null
            Object randomObj = data[i][1];
            if (randomObj != null) {
                int randomIdx = (int) randomObj;
                // 确保索引有效
                if (randomIdx >= 0 && randomIdx < n) {
                    nodes[i].random = nodes[randomIdx];
                }
            }
        }

        // 返回头节点
        return nodes[0];
    }

    public static void main(String[] args) {
        // 这就是你提供的入参
        Object[][] data = {
                {7, null},
                {13, 0},
                {11, 4},
                {10, 2},
                {1, 0}
        };
        LinkedListC linkedListC = new LinkedListC();
        // 生成链表头节点
        Node head = linkedListC.buildLinkedList(data);

        // 打印验证（输出应该和你给的格式完全一致）
        System.out.println("构建的链表为：");
        linkedListC.printLinkedList(head);

        // 这里就可以调用你写的 copyRandomList 方法了
        Node copiedHead = linkedListC.copyRandomList(head);
        linkedListC.printLinkedList(head);
    }

    // ************** 辅助方法：打印链表（验证结果用） **************
    public void printLinkedList(Node head) {
        // 先建立节点到索引的映射，方便打印 random 指向的位置
        Map<Node, Integer> indexMap = new HashMap<>();
        Node curr = head;
        int idx = 0;
        while (curr != null) {
            indexMap.put(curr, idx);
            curr = curr.next;
            idx++;
        }

        curr = head;
        while (curr != null) {
            int randomIdx = (curr.random != null) ? indexMap.get(curr.random) : -1;
            System.out.print("[" + curr.val + ", " + (randomIdx == -1 ? "null" : randomIdx) + "]");
            if (curr.next != null) System.out.print(" -> ");
            curr = curr.next;
        }
        System.out.println();
    }

    public Node copyRandomList(Node head) {

        Node ptr1 = head;
        while (ptr1 != null) {
            Node node = new Node(ptr1.val);
            node.next = ptr1.next;
            node.random = ptr1.random;
            ptr1.next = node;
            ptr1 = ptr1.next;
            ptr1 = ptr1.next;
        }
        printLinkedList(head);
        Node ptr2 = head.next;
        while (ptr2 != null) {
            if (ptr2.random != null) {
                ptr2.random = ptr2.random.next;
            }
            ptr2 = ptr2.next;
            if (ptr2 != null) {
                ptr2 = ptr2.next;
            }

        }
        printLinkedList(head);
//拆分的时候需要恢复原来的链表
        Node res = head.next;
        Node ptr3 = head;
        while (ptr3 != null) {
            Node node2 = ptr3.next;
            if (ptr3.next.next != null) {
                ptr3.next = ptr3.next.next;
            } else {
                ptr3.next = null;
            }
            if (node2.next != null) {
                node2.next = node2.next.next;
            }


            ptr3 = ptr3.next;


        }
        return res;
    }


    public ListNode reverseBetween(ListNode head, int left, int right) {
        return null;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

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
}
