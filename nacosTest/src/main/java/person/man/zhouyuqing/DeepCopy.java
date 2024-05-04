package person.man.zhouyuqing;

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
           if(headRan.random!=null){
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
