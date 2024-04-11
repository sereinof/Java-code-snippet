package person.man.zhouyuqing;


import java.util.HashMap;


public class LRUCache {
    public int capacity = 0;
    public HashMap<Integer, DqueneNode> map = new HashMap();
    public int exactlyCounts = 0;
    public DqueneNode header ;
    public DqueneNode tail ;

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

    public void moveToHeader(DqueneNode dqueneNode) {

        dqueneNode.pre.next = dqueneNode.next;
        dqueneNode.next.pre = dqueneNode.pre;
        dqueneNode.pre =header;
        dqueneNode.next = header.next;
        header.next = dqueneNode;
    }

    public void addToHeader(DqueneNode dqueneNode) {
        dqueneNode.next = this.header;
        this.header.pre = dqueneNode;
        this.header = dqueneNode;
        if (this.capacity == exactlyCounts) {
            removTail();
        } else {
            this.exactlyCounts++;
        }
    }
    public void removTail(){
        map.remove(this.tail.pre.value);
        DqueneNode node = tail.pre;
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.get(key).value = value;
            moveToHeader(map.get(key));
        } else {
            DqueneNode newN = new DqueneNode(value);
            addToHeader(newN);//这个方法需要考虑淘汰的问题

        }
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