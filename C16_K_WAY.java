import java.util.List;
import java.util.PriorityQueue;

public class C16_K_WAY {
    //TODO Merge K Sorted Lists (medium)
    public static ListNode merge(ListNode[] lists) {
        ListNode resultHead = null;
        // TODO: Write your code here
        PriorityQueue<ListNode> minQ = new PriorityQueue<>((a,b)-> a.val - b.val);

        for (ListNode node: lists){
            minQ.add(node);
        }

        ListNode head = null, tail = null;

        while (!minQ.isEmpty()){
            ListNode cur = minQ.poll();
            if (head == null && tail == null){
                head = cur; tail = cur;
            }else{
                tail.next = cur;
                tail = tail.next;
            }
            if (cur.next != null){
                minQ.add(cur.next);
            }
        }
        return head;
    }
}
class ListNode {
    int val;
    ListNode next;

    ListNode(int value) {
        this.val = value;
    }
}
