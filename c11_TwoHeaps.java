import java.util.Date;
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
    PriorityQueue<Integer> minHeap = new PriorityQueue<>((o1, o2)->o1-o2);
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2)->o2-o1);
    public double[] findSlidingWindowMedian(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            if (maxHeap.size() == 0 || nums[i] <= maxHeap.peek()){
                maxHeap.add(nums[i]);
            }else {
                minHeap.add(nums[i]);
            }
            reBalanceHeap();
            if (i - k + 1 >= 0){
                if (maxHeap.size() == minHeap.size()){
                    result[i - k + 1] = maxHeap.peek()/2.0 + minHeap.peek()/2.0;
                }else {
                    result[i - k + 1] = maxHeap.peek();
                }

                int remove = nums[i - k + 1];
                if (remove <= maxHeap.peek()){
                    maxHeap.remove(remove);
                }else {
                    minHeap.remove(remove);
                }
                reBalanceHeap();
            }
        }

        // TODO: Write your code here
        return result;
    }

    private void reBalanceHeap() {
        if (maxHeap.size() > minHeap.size() + 1){
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    //TODO Maximize Capital (hard)
    public static int findMaximumCapital(int[] capital, int[] profits,int numberOfProjects, int initialCapital) {
        // TODO: Write your code here  
        return 0;
    }

}
