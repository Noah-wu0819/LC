import javax.management.modelmbean.ModelMBean;
import java.nio.channels.Pipe;
import java.util.*;

public class c15_TOP_K_Elements {
    //Introduction to Top 'K' Elements Pattern

    //TODO Top 'K' Numbers (easy)
    public static List<Integer> findKLargestNumbers(int[] nums, int k) {
        // TODO: Write your code here
        PriorityQueue<Integer> minheap = new PriorityQueue<>((a, b) -> a - b);
        for (int i = 0; i < k; i++) {
            minheap.add(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > minheap.peek()) {
                minheap.poll();
                minheap.add(nums[i]);
            }
        }
        return new ArrayList<>(minheap);
    }

    //TODO Kth Smallest Number (easy)
    public static int findKthSmallestNumber(int[] nums, int k) {
        // TODO: Write your code here
        PriorityQueue<Integer> maxheap = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < k; i++) {
            maxheap.add(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] < maxheap.peek()) {
                maxheap.poll();
                maxheap.add(nums[i]);
            }
        }
        return maxheap.poll();
    }

    //TODO 'K' Closest Points to the Origin (easy)

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int distFromOrigin() {
            // ignoring sqrt
            return (x * x) + (y * y);
        }
    }

    public static List<Point> findClosestPoints(Point[] points, int k) {
        // TODO: Write your code here
        PriorityQueue<Point> maxHeap = new PriorityQueue<>((a, b) -> b.distFromOrigin() - a.distFromOrigin());

        for (int i = 0; i < k; i++) {
            maxHeap.add(points[i]);
        }

        for (int i = k; i < points.length; i++) {
            if (points[i].distFromOrigin() < maxHeap.peek().distFromOrigin()) {
                maxHeap.poll();
                maxHeap.add(points[i]);
            }
        }
        return new ArrayList<Point>(maxHeap);
    }

    //TODO Connect Ropes (easy)
    public static int minimumCostToConnectRopes(int[] ropeLengths) {
        // TODO: Write your code here
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);
        for (int i = 0; i < ropeLengths.length; i++) {
            minHeap.add(ropeLengths[i]);
        }
        int result = 0;
        while (minHeap.size() > 1) {
            int a = minHeap.poll();
            int b = minHeap.poll();
            result += a + b;
            minHeap.add(a + b);
        }

        return result;
    }

    //TODO Top 'K' Frequent Numbers (medium)
    public static List<Integer> findTopKFrequentNumbers(int[] nums, int k) {
        List<Integer> topNumbers = new ArrayList<>(k);
        // TODO: Write your code here
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            minHeap.add(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }

        }

        for (int i = 0; i < k; i++) {
            topNumbers.add(minHeap.poll().getKey());
        }
        return topNumbers;
    }

    //TODO Frequency Sort (medium)
    public static String sortCharacterByFrequency(String str) {
        // TODO: Write your code here

        HashMap<Character, Integer> map = new HashMap<>();
        char[] chars = str.toCharArray();
        for (Character c : chars) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>>
                minHeap = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        minHeap.addAll(map.entrySet());

        StringBuilder result = new StringBuilder();
        while (!minHeap.isEmpty()) {
            Map.Entry<Character, Integer> entry = minHeap.poll();
            for (int i = 0; i < entry.getValue(); i++) {
                result.append(entry.getKey());
            }
        }
        return result.toString();
    }

    //TODO Kth Largest Number in a Stream (medium)
    class Solution {
        PriorityQueue<Integer> minHeap;
        int k;

        public Solution(int[] nums, int k) {
            // TODO: Write your code here
            this.k = k;
            this.minHeap = new PriorityQueue<>((a, b) -> a - b);
            for (int i = 0; i < nums.length; i++) {
                minHeap.add(nums[i]);
                if (minHeap.size() > k) {
                    minHeap.poll();
                }
            }
        }

        public int add(int num) {
            // TODO: Write your code here
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
            return minHeap.peek();
        }

    }

    //TODO 'K' Closest Numbers (medium)
    static class Entry {
        int key;
        int value;

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static List<Integer> findClosestElements(int[] arr, int K, int X) {
//        List<Integer> result = new ArrayList<>();
//        // TODO: Write your code here
//        List<Entry> entries = new ArrayList<Entry>();
//
//        for (int i = 0; i < arr.length; i++) {
//            entries.add(new Entry(arr[i], Math.abs(arr[i] - X)));
//        }
//
//        PriorityQueue<Entry> maxHeap = new PriorityQueue<>((a, b) -> {
//            if (b.value == a.value) {
//                return b.key - a.key;
//            } else {
//                return b.value - a.value;
//            }
//        });
//        for (Entry entry : entries) {
//            maxHeap.add(entry);
//            if (maxHeap.size() > K)
//                maxHeap.poll();
//        }
//
//        for (int i = 0; i < K; i++) {
//            result.add(maxHeap.poll().key);
//        }
//        Collections.sort(result);
        List<Integer> result = new ArrayList<>();
        // TODO: Write your code here
        int key = binarySearch(arr, X);
        int low = key - K, high = key + K;

        PriorityQueue<Entry> maxHeap = new PriorityQueue<>((a,b)->a.key - b.key);

        for (int i = low; i <= high; i++) {
            maxHeap.add(new Entry(arr[i] - K, i));
        }

        for (int i = 0; i < K; i++) {
            result.add(maxHeap.poll().key);
        }
        return result;
    }
    /*
    * 如果 low > 0，这表示目标值不在数组中，但应该插入到数组的某个位置。此时返回 low - 1，表示应该插入的位置是 low - 1。
    如果 low 不大于0，这表示目标值比整个数组中的所有元素都小，那么返回 low，表示应该插入到数组的第一个位置。这是因为在 Java 中，数组索引是从 0 开始的。
    让我们考虑一下这两种情况的 low 值如何计算：
    如果目标值小于数组中的所有元素，那么在整个搜索过程中，low 的值始终为0。这是因为始终选择数组的第一个元素作为搜索范围的起点，因此 low 不会增加。
    如果目标值不在数组中，那么在搜索范围内找不到与目标值相等的元素。当搜索范围缩小到无效范围时，low 的值将大于0，因为它将等于 high + 1，这是在二分查找的过程中最后一次执行 low = mid + 1 时发生的。
    所以，如果 low > 0，表示目标值不在数组中，且 low - 1 是应该插入的位置；如果 low 不大于0，表示目标值比整个数组中的所有元素都小，那么 low 是应该插入的位置。这是根据二分查找算法的性质来计算的。*/

    private static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high){
            int mid = low + (high - low) / 2;

            if (arr[mid] == target){
                return mid;
            }else if (arr[mid] < target){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }

        if (low > 0){
            low -= 1;
        }

        return low;
    }
    //堆新元素会插到末尾

    //TODO Maximum Distinct Elements (medium)
    public static int findMaximumDistinctElements(int[] nums, int k) {
        int distinctElementsCount = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer,Integer>> minHeap = new PriorityQueue<>((a,b)->a.getValue()-b.getValue());
        for (Map.Entry<Integer,Integer> entry: map.entrySet()){
            if (entry.getValue() == 1){
                distinctElementsCount++;
            }else
            minHeap.add(entry);
        }
        while (k > 0 && !minHeap.isEmpty()){
            Map.Entry<Integer, Integer> entry = minHeap.poll();
            k -= entry.getValue() - 1;
            if (k >= 0){
                distinctElementsCount++;
            }
        }
        if (k > 0)
            distinctElementsCount -= k;
        // TODO: Write your code here
        return distinctElementsCount;
    }

    //TODO Sum of Elements (medium)

    public static int findSumOfElements(int[] nums, int k1, int k2) {
        int elementSum = 0;
        // TODO: Write your code here
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b)->a-b);
        for (int n: nums){
            minHeap.add(n);
        }
        k1 = k1 < k2? k1: k2;
        for (int i = 0; i < nums.length; i++) {

            if (i >= k1 && i < k2-1){
                elementSum += minHeap.poll();
            }else {
                minHeap.poll();
            }
        }
        return elementSum;
    }

    //TODO Rearrange String (hard)
    public static String rearrangeString(String str) {
        // TODO: Write your code here
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c: str.toCharArray()){
            map.put(c, map.getOrDefault(c, 0 ) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(
                (a,b)->b.getValue() - a.getValue()
        );

        maxHeap.addAll(map.entrySet());

        Map.Entry<Character, Integer> preEntry = null;
        StringBuilder ans = new StringBuilder(str.length());

        while (!maxHeap.isEmpty()){
            Map.Entry<Character, Integer> curEntry = maxHeap.poll();
            ans.append(curEntry.getKey());
            curEntry.setValue(curEntry.getValue() - 1);
            if (preEntry != null && preEntry.getValue() > 0)
                maxHeap.add(preEntry);
            preEntry = curEntry;
        }
        return ans.length() == str.length()? ans.toString():"";
    }
    //TODO Problem Challenge 1: Rearrange String K Distance Apart (hard)
    public static String reorganizeString(String str, int k) {
        // TODO: Write your code here
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c: str.toCharArray()){
            map.put(c, map.getOrDefault(c, 0 ) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(
                (a,b)->b.getValue() - a.getValue()
        );

        maxHeap.addAll(map.entrySet());
        Queue<Map.Entry<Character, Integer>> queue = new LinkedList<>();
        StringBuilder ans = new StringBuilder(str.length());

        while (!maxHeap.isEmpty()){
            Map.Entry<Character, Integer> curEntry = maxHeap.poll();
            ans.append(curEntry.getKey());
            curEntry.setValue(curEntry.getValue() - 1);
            queue.add(curEntry);
            if (queue.size() == k){
                if (queue.peek().getValue() > 0) {
                    maxHeap.add(queue.poll());
                }
            }
        }
        return ans.length() == str.length()? ans.toString() : "";
    }
    //TODO Problem Challenge 2: Scheduling Tasks
    public static int scheduleTasks(char[] tasks, int k) {
        int intervalCount = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (char c: tasks){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((a,b)->b.getValue() - a.getValue());

        maxHeap.addAll(map.entrySet());
        // TODO: Write your code here

        while (!maxHeap.isEmpty()){
            List<Map.Entry<Character, Integer>> waitList = new ArrayList<>();
            int n = k + 1;
            for (; n>0 && !maxHeap.isEmpty(); n--){
                intervalCount++;
                Map.Entry<Character, Integer> curEntry = maxHeap.poll();
                if(curEntry.getValue() > 1){
                    curEntry.setValue(curEntry.getValue() - 1);
                    waitList.add(curEntry);
                }
            }
            maxHeap.addAll(waitList);
            if (!maxHeap.isEmpty())
                intervalCount += n;
        }
        return intervalCount;
    }

    //TODO Problem Challenge 3: Frequency Stack (hard)

    public int execute(int[] nums){
        int result = 0;
        for(int i=0;i<nums.length;i++)  {
            push(nums[i]);
        }
        pop();
        pop();
        result = pop();
        return result;
    }
    class Element{
        int number;
        int frequency;
        int sequenceNumber;
        public Element(int number, int frequency, int sequenceNumber){
            this.number = number;
            this.frequency = frequency;
            this.sequenceNumber = sequenceNumber;
        }
    }
    class myComparator implements Comparator<Element>{

        @Override
        public int compare(Element o1, Element o2) {
            if (o1.frequency != o2.frequency){
                return o2.frequency - o1.frequency;
            }
            return o2.sequenceNumber - o1.sequenceNumber;
        }
    }
    int sequenceNumber = 0;
    Map<Integer, Integer> frequencyMap = new HashMap<>();
    PriorityQueue<Element> maxHeap = new PriorityQueue<>(new myComparator());
    public void push(int num) {
        // TODO: Write your code here
        frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        maxHeap.add(new Element(num, frequencyMap.get(num), sequenceNumber++));
    }

    public int pop() {
        // TODO: Write your code here
        Element curElement = maxHeap.poll();
        if(curElement.frequency > 1){
            frequencyMap.put(curElement.number, curElement.frequency - 1);
        }else {
            frequencyMap.remove(curElement.number);
        }
        return curElement.number;
    }

    //TODO Merge K Sorted Lists (medium)
    class ListNode {
        int val;
        ListNode next;

        ListNode(int value) {
            this.val = value;
        }
    }
    public static ListNode merge(ListNode[] lists) {
        ListNode resultHead = null;
        // TODO: Write your code here
        return resultHead;
    }
}
