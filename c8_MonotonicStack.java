import java.util.HashMap;
import java.util.Stack;

public class c8_MonotonicStack {
//TODO Next Greater Element (easy)
public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    // TODO: Write your code here
    Stack<Integer> stack = new Stack<>();
    HashMap<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums2.length; i++) {
        while (!stack.isEmpty() && nums2[i] > stack.peek()){
            int key = stack.pop();
            map.put(key, nums2[i]);
        }
        stack.push(nums2[i]);
    }


    for (int i = 0; i < nums1.length; i++) {
        nums1[i] = map.getOrDefault(nums1[i],-1);
    }

    return nums1;
}

//TODO Daily Temperatures (easy)
public int[] dailyTemperatures(int[] temperatures) {
    // TODO: Write your code here
    Stack<Integer> stack = new Stack<>();
    int[] ans = new int[temperatures.length];

    for (int i = 0; i < temperatures.length; i++) {
        while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
            int idx = stack.pop();
            ans[idx] = i - idx;
        }
        stack.push(i);
    }
    return temperatures;
}

//TODO Remove Nodes From Linked List (easy)
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
public ListNode removeNodes(ListNode head) {
    // TODO: Write your code here
    ListNode cur = head;
    Stack<ListNode> stack = new Stack<>();
    while (cur != null){
        while (!stack.isEmpty() && cur.val > stack.peek().val){
            stack.pop();
        }
//这小逻辑要注意
        if (!stack.isEmpty())
            stack.peek().next = cur;
        stack.push(cur);
        cur = cur.next;
    }

    if (stack == null) return null;
    else {
       return stack.lastElement();
    }
}
//TODO Remove All Adjacent Duplicates In String (easy)
public String removeDuplicates(String s) {
    // TODO: Write your code here
    StringBuilder stack = new StringBuilder();

    for (int i = 0; i < s.length(); i++) {
        while (stack != null && s.charAt(i) == stack.charAt(stack.length() - 1)) {
            stack.deleteCharAt(stack.length() - 1);
        }
        stack.append(s.charAt(i));
    }

    return stack.toString();
}
//TODO Remove All Adjacent Duplicates in String II (medium)
public String removeDuplicates(String s, int k) {
    // TODO: Write your code here
    StringBuilder ans = new StringBuilder();
    Stack<int[]> stack = new Stack<>();

    for (int i = 0; i < s.length(); i++) {
        if (!stack.isEmpty() && stack.peek()[0] == s.charAt(i)){
            stack.peek()[1]++;
        }

        if (!stack.isEmpty() && stack.peek()[0] != s.charAt(i)){
            stack.pop()[0] = s.charAt(i);
            stack.peek()[1] = 1;
        }

        if (stack.peek()[1] == k)
            stack.pop();
    }

    while (!stack.isEmpty()){
        int top[] = stack.pop();
        ans.append(String.valueOf((char)top[0]).repeat(top[1]));
    }
    return ans.reverse().toString();
}
//TODO Remove K Digits (hard)
public String removeKdigits(String num, int k) {
    // TODO: Write your code here
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < num.length(); i++) {
        while (k > 0 && !stack.isEmpty() && num.charAt(i) < stack.peek()){
            stack.pop();
            k--;
        }
        stack.push(num.charAt(i));
    }

    while (k > 0){
        stack.pop();
        k--;
    }

    StringBuilder ans = new StringBuilder();
    while (!stack.isEmpty()){
        ans.insert(0, stack.pop());
    }

    while (ans.length() > 1 && ans.charAt(0) == '0'){
        ans.deleteCharAt(0);
    }
    return ans.length() < 1? "0": ans.toString();
}
}
