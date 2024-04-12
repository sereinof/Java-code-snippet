package person.man.zhouyuqing;


import java.util.HashMap;


public class LRUCache {
    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        int i = lRUCache.get(1);    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        int j = lRUCache.get(2);    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        int k = lRUCache.get(1);    // 返回 -1 (未找到)
        int p = lRUCache.get(3);    // 返回 3
        int l = lRUCache.get(4);
    }

    public int capacity = 0;
    public HashMap<Integer, DqueneNode> map = new HashMap();
    public int exactlyCounts = 0;
    public DqueneNode header;
    public DqueneNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.header = new DqueneNode();
        this.tail = new DqueneNode();
        header.next = tail;
        tail.pre = header;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            moveToHeader(map.get(key));
            return map.get(key).value;
        } else {
            return -1;
        }

    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.get(key).value = value;
            moveToHeader(map.get(key));
        } else {
            DqueneNode newN = new DqueneNode(value);
            map.put(key, newN);
            addToHeader(newN);//这个方法需要考虑淘汰的问题

        }
    }

    public void moveToHeader(DqueneNode dqueneNode) {
        remove(dqueneNode);
        addToHeader(dqueneNode);
    }

    public void remove(DqueneNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public void addToHeader(DqueneNode node) {
        node.pre = header;
        node.next = header.next;
        header.next.pre = node;
        header.next = node;
        if (this.capacity == exactlyCounts) {
            removTail();
        } else {
            this.exactlyCounts++;
        }
    }

    public void removTail() {
        map.remove(this.tail.pre.value);
        remove(tail.pre);
    }


}

class DqueneNode {
    public int value;
    public DqueneNode pre;
    public DqueneNode next;

    public DqueneNode() {

    }

    public DqueneNode(int value) {
        this.value = value;
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */