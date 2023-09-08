import java.util.List;
import java.util.PriorityQueue;

public class c11_TwoHeaps {

    //TODO Find the Median of a Number Stream (medium)
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    public Solution() {
        // TODO: Write your code here
        minHeap = new PriorityQueue<>((o1, o2)-> o1- o2);
        maxHeap = new PriorityQueue<>((o1, o2)-> o2- o1);
    }

    //Do not modify this
    public double executeFindMedian(List<Integer> nums) {
        for (int num : nums) {
            insertNum(num);
        }
        return findMedian();
    }

    public void insertNum(int num) {
        // TODO: Write your code here
        if (!maxHeap.isEmpty() && num > maxHeap.peek())
            minHeap.add(num);
        else
            maxHeap.add(num);

        if (maxHeap.size() > minHeap.size() + 1)
            minHeap.offer(maxHeap.poll());
        else if (maxHeap.size() < minHeap.size())
            maxHeap.offer(minHeap.poll());
    }

    public double findMedian() {
        // TODO: Write your code here
        double median = 0;
        if (maxHeap.size() == minHeap.size())
            return (maxHeap.poll().doubleValue() + minHeap.poll().doubleValue()) / 2.0;
        else
            return maxHeap.size() > minHeap.size()? maxHeap.poll().doubleValue(): minHeap.poll().doubleValue();
    }

    //TODO Sliding Window Median (hard)
    public double[] findSlidingWindowMedian(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        // TODO: Write your code here
        return result;
    }

}
