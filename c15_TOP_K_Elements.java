import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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
}
