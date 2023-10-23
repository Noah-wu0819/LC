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

    //TODO Kth Smallest Number in M Sorted Lists (medium)
    public static int findKthSmallest(List<List<Integer>> lists, int k) {
        int result = 0;
        PriorityQueue<Node> minHeap = new PriorityQueue<>((a,b)->
                lists.get(a.arrayIndex).get(a.elementIndex) - lists.get(b.arrayIndex).get(b.elementIndex)
        );

        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i) != null)
                minHeap.add(new Node(0, i));
        }
        int numberCount = 0;
        while (!minHeap.isEmpty()){
            Node node = minHeap.poll();
            result = lists.get(node.arrayIndex).get(node.elementIndex);
            if (++numberCount == k) break;
            node.elementIndex++;
            if (node.elementIndex < lists.get(node.arrayIndex).size())//elementIndex必须在++之后在检查，要不然出界了
                minHeap.add(node);
        }

        return result;
    }
}
class Node{
    int elementIndex;
    int arrayIndex;
    Node(int elementIndex, int arrayIndex){
        this.elementIndex = elementIndex;
        this.arrayIndex = arrayIndex;
    }
}
class ListNode {
    int val;
    ListNode next;

    ListNode(int value) {
        this.val = value;
    }
}
