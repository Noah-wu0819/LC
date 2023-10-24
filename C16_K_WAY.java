import java.util.List;
import java.util.Map;
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

    //TODO Kth Smallest Number in a Sorted Matrix (hard)
    public static int findKthSmallest(int[][] matrix, int k) {
        int result = 0;
        // TODO: Write your code here
        PriorityQueue<Matrix> minHeap = new PriorityQueue<>((a,b)->matrix[a.row][a.col] - matrix[b.row][b.col]);
        for (int i = 0; i < matrix.length; i++) {
            minHeap.add(new Matrix(i, 0));
        }
        int index = 0;
        while (!minHeap.isEmpty()){
            Matrix node = minHeap.poll();
            result = matrix[node.row][node.col];
            if (++index == k) break;
            node.col++;
            if (node.col < matrix.length) minHeap.add(node);
        }
        return result;
    }

    //TODO Smallest Number Range (hard)
    public static int[] findSmallestRange(List<List<Integer>> lists) {
        int curMax = 0;
        // TODO: Write your code here
        PriorityQueue<Node> minHeap = new PriorityQueue<>((a,b)->
                lists.get(a.arrayIndex).get(a.elementIndex)-lists.get(b.arrayIndex).get(b.elementIndex));

        for (int i = 0; i < lists.size(); i++) {
            minHeap.add(new Node(0, i));
            curMax = Math.max(curMax, lists.get(i).get(0));
        }

        int rangeStart = 0;
        int rangeEnd = Integer.MAX_VALUE;
        while (minHeap.size() == lists.size()){
            Node node = minHeap.poll();
            if (rangeEnd - rangeStart > curMax - lists.get(node.arrayIndex).get(node.elementIndex)){
                rangeStart = lists.get(node.arrayIndex).get(node.elementIndex);
                rangeEnd = curMax;
            }
            node.elementIndex++;
            if (node.elementIndex < lists.get(node.arrayIndex).size()){
                minHeap.add(node);
                curMax = Math.max(curMax, lists.get(node.arrayIndex).get(node.elementIndex));
            }
        }
        return new int[] { rangeStart, rangeEnd };
    }
}
class Matrix{
    int row,col;
    Matrix(int row, int col){
        this.row = row;
        this.col = col;
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
