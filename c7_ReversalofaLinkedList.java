import jdk.jshell.TypeDeclSnippet;

public class c7_ReversalofaLinkedList {
    class ListNode {
        int val = 0;
        ListNode next;

        ListNode(int value) {
         this.val = value;
        }
    }

//TODO Reverse a LinkedList (easy)
public static ListNode reverse(ListNode head) {
    // TODO: Write your code here
    ListNode pre = null;
    ListNode cur = head;
    ListNode next = null;
    while (cur != null){
        next = cur.next;
        cur.next = pre;
        pre = cur;
        cur = next;
    }
    return pre;
}

//TODO Reverse a Sub-list (medium)
public static ListNode reverse(ListNode head, int p, int q) {
        if (p == q) return head;
    ListNode pre = null;
    ListNode cur = head;
    ListNode next = null;
    // TODO: Write your code here
    while (cur.val != p) {
        pre = cur;
        cur = cur.next;
    }
    ListNode first = cur;
    ListNode last = pre;
    last.next = null;

    pre = null;
    while (pre.val != q) {
        next = cur.next;
        cur.next = pre;
        pre = cur;
        cur = next;
    }
    if (last != null)
        last.next = pre;
    else
        head = pre;
    first.next = cur;
    return head;
}
//TODO Reverse every K-element Sub-list (medium)
public static ListNode reverse(ListNode head, int k) {
    // TODO: Write your code here
    if (k == 1 || head == null) return head;

    ListNode cur = head;
    ListNode pre = null;
    while (true){
        ListNode lastNodeOfPrePart = pre;
        ListNode lastNodeOfCurPart = cur;

        ListNode next = null;
        for (int i = 0; i < k && cur != null; i++) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        if (lastNodeOfPrePart != null){
            lastNodeOfPrePart.next = pre;
        }else {
            head = pre;
        }

        lastNodeOfCurPart.next = cur;

        if (cur == null)
            break;
        pre = lastNodeOfCurPart;
    }
    return head;
}
//TODO Problem Challenge 2: Rotate a LinkedList (medium)
public static ListNode rotate(ListNode head, int rotations) {
    // TODO: Write your code here
    ListNode ansHead = head;
    ListNode pre = null;
    int length = 1;
    while (ansHead.next != null){
        ansHead = ansHead.next;
        length++;
    }
    rotations = length - rotations % length;
    ansHead.next = head;
    pre = ansHead;
    ansHead = ansHead.next;
    for (int i = 0; i < rotations; i++) {
        pre = ansHead;
        ansHead = ansHead.next;
    }
    pre.next = null;
    return ansHead;
}

//TODO Problem Challenge 1: Reverse alternating K-element Sub-list (medium)
public static ListNode revers1e(ListNode head, int k) {
    // TODO: Write your code here
    if ( k == 1 || head.next == null) return head;
    ListNode cur = head;
    ListNode pre = null;
    while (true) {
        ListNode next = null;
        ListNode lastOfPre = pre;
        ListNode lastOfCur = cur;
        int index = 0;
        for (int i = 0; i < k && cur != null; i++) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        if (lastOfPre != null){
            lastOfPre.next = pre;
        }else {
            head = pre;
        }

        lastOfCur.next = cur;
        if (cur == null) {
            return head;
        }
        pre = lastOfCur;

        for (int i = 0; i < k && cur != null; i++) {
            next = cur.next;
            pre = cur;
            cur = next;
        }

    }
}

}
