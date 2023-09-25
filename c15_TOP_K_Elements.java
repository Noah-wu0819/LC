import java.nio.channels.Pipe;
import java.util.*;

public class c15_TOP_K_Elements {
    //Introduction to Top 'K' Elements Pattern

    //TODO Top 'K' Numbers (easy)
    public static List<Integer> findKLargestNumbers(int[] nums, int k) {
        // TODO: Write your code here
        PriorityQueue<Integer> minheap = new PriorityQueue<>((a,b)-> a-b);
        for (int i = 0; i < k; i++) {
            minheap.add(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > minheap.peek()){
                minheap.poll();
                minheap.add(nums[i]);
            }
        }
        return new ArrayList<>(minheap);
    }

    //TODO Kth Smallest Number (easy)
    public static int findKthSmallestNumber(int[] nums, int k) {
        // TODO: Write your code here
        PriorityQueue<Integer> maxheap = new PriorityQueue<>((a, b)->b-a);
        for (int i = 0; i < k; i++) {
            maxheap.add(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if(nums[i] < maxheap.peek()){
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
        PriorityQueue<Point> maxHeap = new PriorityQueue<>((a, b)->b.distFromOrigin()-a.distFromOrigin());

        for (int i = 0; i < k; i++) {
            maxHeap.add(points[i]);
        }

        for (int i = k ; i < points.length; i++) {
            if (points[i].distFromOrigin() < maxHeap.peek().distFromOrigin()){
                maxHeap.poll();
                maxHeap.add(points[i]);
            }
        }
        return new ArrayList<Point>(maxHeap);
    }

    //TODO Connect Ropes (easy)
    public static int minimumCostToConnectRopes(int[] ropeLengths) {
        // TODO: Write your code here
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b)->a-b);
        for (int i = 0; i < ropeLengths.length; i++) {
            minHeap.add(ropeLengths[i]);
        }
        int result = 0;
        while (minHeap.size() > 1){
            int a = minHeap.poll();
            int b = minHeap.poll();
            result += a+b;
            minHeap.add(a+b);
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

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((a, b)->a.getValue()-b.getValue());

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                minHeap.add(entry);
                if (minHeap.size() > k){
                    minHeap.poll();
                }

        }

        for (int i = 0; i < k; i++) {
            topNumbers.add(minHeap.poll().getKey());
        }
        return topNumbers;
    }
}
