package person.man.findjobs;

import java.util.HashMap;

public class LRUCache {

    int remainCapacity = 0;
    ListNode head = new ListNode(-1);
    ListNode tail = head;
    HashMap<Integer, ListNode> map = new HashMap<>();

    public LRUCache(int capacity) {
        this.remainCapacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            if (node != head) {
                node.pre.next = node.next;
                if (node.next != null) {
                    node.next.pre = node.pre;
                }
                node.next = head;
                head.pre = node;
                head = node;
                head.pre = null;
                return node.val;

            } else {
                return head.val;
            }

        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            node.val = value;
            if (node != head) {
                node.pre.next = node.next;
                if (node.next != null) {
                    node.next.pre = node.pre;
                }
                node.next = head;
                node.pre = null;
                head.pre = node;
                head = node;

            } else {
//取head 不需要动的
            }
        } else {
            ListNode node = new ListNode(value);
            node.key = key;
            map.put(key, node);
            head.pre = node;
            node.next = head;
            head = node;
            remainCapacity--;
            if (remainCapacity < 0) {
                ListNode realTail = tail.pre;
                if (realTail != null) {
                    ListNode realTailPre = realTail.pre;
                    tail.pre = realTailPre;
                    realTail.pre = null;
                    realTail.next = null;
                    if (realTailPre != null) {
                        realTailPre.next = tail;
                    }
                    map.remove(realTail.key);
                    remainCapacity++;
                }

            }
        }

    }

    class ListNode {
        int val;
        ListNode next;
        ListNode pre;
        int key;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
