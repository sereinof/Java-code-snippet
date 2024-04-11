package person.man.zhouyuqing;

import java.util.HashMap;

public class detectCycle {
    public static void main(String[] args) {

    }

    public ListNode detectCycle(ListNode head) {
        HashMap<ListNode,Integer> map= new HashMap();
        while (head.next!=null){
            if(map.get(head)>0){
                return  head;
            }else{
                map.put(head,1);
                head = head.next;
            }
        }
        return  null;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}