public class c3_FastSlow_POINTER {

    static class ListNode {
        int val = 0;
        ListNode next;

        ListNode(int value) {
            this.val = value;
        }
    }

    //TODO LinkedList Cycle (easy)
    public static boolean hasCycle(ListNode head) {
        // TODO: Write your code here
        if (head == null || head.next == null) return false;
        ListNode slow = new ListNode(-1);
        ListNode fast = head.next;
        slow.next = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) return true;
        }
        return false;
    }

    public static int findCycleLength(ListNode head) {
        int ans = 0;
        if (head == null || head.next == null) return 0;
        ListNode slow = new ListNode(-1);
        ListNode fast = head.next;
        slow.next = head;

        while (fast != slow){
            slow = slow.next;
            fast = fast.next.next;
        }

        fast = slow.next;
        ans++;
        while (fast != slow){
            fast = fast.next;
            ans++;
        }
        return ans;
    }

    //TODO Start of LinkedList Cycle (medium)
    public static ListNode findCycleStart(ListNode head) {

        if (head == null || head.next == null) return null;
        // TODO: Write your code here
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) break;
        }
        if (fast == null || fast.next == null) return new ListNode(1);
        fast = head;
        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }
    //TODO Happy Number (medium)

    public static boolean find(int num) {


        int slow = findSum(num);
        int fast = findSum(findSum(num));

        while (slow != fast){
            slow = findSum(slow);
            fast = findSum(findSum(fast));
        }
        // TODO: Write your code here
        return slow == 1;
    }

    private static int findSum(int num) {

        int sum = 0;
        while (num > 0){
            int digit = num % 10;
            sum += digit * digit;
            num /= 10;
        }
        return  sum;
    }

    //TODO Middle of the LinkedList (easy)
    public static ListNode findMiddle(ListNode head) {
        // TODO: Write your code here
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //TODO Problem Challenge 1: Palindrome LinkedList (medium)
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return  true;
        // TODO: Write your code here
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode left = head;
        ListNode right =  reverse(slow.next);
        ListNode copy = right;
        while (right != null && left != null){
            if (right.val != left.val) return false;
            right = right.next;
            left = left.next;
        }
        right = reverse(copy);
       slow.next = right;
        return true;
    }
    public static ListNode reverse(ListNode node){
        ListNode pre = null;
        while (node != null){
            ListNode next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }
    public static void insert(ListNode node1, ListNode node2){
        while (node1 != null && node2 != null){
            ListNode next1 = node1.next;
            ListNode next2 = node2.next;
            node1.next = node2;
            node2.next = next1;
            node1 = next1;
            node2 = next2;
        }
    }
    //TODO Problem Challenge 2: Rearrange a LinkedList (medium)
    public static ListNode reorder(ListNode head) {
        // TODO: Write your code here
        if (head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode right = reverse(slow.next);
        slow.next = null;
        ListNode left = head;
        insert(left, right);
        return head;
    }

    //TODO Problem Challenge 3: Cycle in a Circular Array (hard)
    public static boolean loopExists(int[] arr) {
        // TODO: Write your code here

        for (int i = 0; i < arr.length; i++) {
            boolean dir = arr[i] >= 0;
            int slow = i;
            int fast = i;
            do {
                slow = findNext(arr, slow, dir);
                fast = findNext(arr, fast, dir);
                if (fast != -1) fast = findNext(arr, fast, dir);
            }while (slow != -1 && fast != -1 && fast != slow);

            if (fast == slow && slow != -1) return true;
        }
        return false;
    }

    private static int findNext(int[] arr, int cur, boolean direction) {
        boolean dir = arr[cur] >= 0;
        if (dir != direction) return -1;
        int index = (arr[cur] + cur) % arr.length;
        if (index < 0) index += arr.length;

        if (cur == index) return -1;
        return index;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);
        head.next.next.next.next.next = new ListNode(12);
        c3_FastSlow_POINTER.reorder(head);
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
}
