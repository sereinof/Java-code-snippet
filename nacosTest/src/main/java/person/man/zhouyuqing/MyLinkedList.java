package person.man.zhouyuqing;

public class MyLinkedList {
    ListNode head;

    public static void main(String[] args) {
         MyLinkedList linkedList =  new MyLinkedList();
         linkedList.addAtIndex(2,1);
         linkedList.addAtIndex(3,4);
         linkedList.addAtTail(1);
         linkedList.get(0);
         linkedList.deleteAtIndex(0);
         linkedList.get(1);


    }
    public MyLinkedList() {

    }

    public int get(int index) {
        index++;
        int count = 1;
        if (index == 1) {
            if(head==null){
                return -1;
            }
            return head.val;
        }
        ListNode now = head;
        while (now.next != null) {
            now = now.next;
            count++;
            if (count == index) {
                return now.val;
            }
        }
        return -1;
    }

    public void addAtHead(int val) {
        ListNode newly = new ListNode(val);
        newly.next = head;
        head = newly;
    }

    public void addAtTail(int val) {
        ListNode tail = new ListNode(val);
        ListNode tmp = head;
        if(head==null){
            head = tail;
            return;
        }
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = tail;
    }

    public void addAtIndex(int index, int val) {
        ListNode dummy = new ListNode(-1);
        ListNode newly = new ListNode(val);
        dummy.next = head;
        index++;
        int count = 1;
        if (index == 1) {
            addAtHead(val);
        }

        if(head==null){
            //难道这就是头节点给我挖下的坑？ 为啥要又一个头节点还要初始化为0？
            if(index==2){
               ListNode tmp =new ListNode(-1);
               tmp.next = new ListNode(val);
               head=tmp;
               return;
            }
            head=new ListNode(val);
            return;
        }
        ListNode p = head;
        while (p.next != null) {
            dummy = dummy.next;
            p = p.next;
            count++;
            if (count == index) {
                dummy.next = newly;
                newly.next = p;
                return;
            }
        }

        while (count+1<index){
            p.next=new ListNode(-1);
            dummy = dummy.next;
            p = p.next;
            count++;

        }
        p.next = new ListNode(val);
    }

    public void deleteAtIndex(int index) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        index++;
        int count = 1;
        if (index == 1) {
            ListNode tmp = head.next;
            head.next = null;
            head = tmp;
            return;
        }
        ListNode p = head;
        while (p.next != null) {
            dummy = dummy.next;
            p = p.next;
            count++;
            if (count == index) {
                ListNode tmp=  p.next;
                p.next = null;
                dummy.next = tmp;
                return;
            }
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
}
