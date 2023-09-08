import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class c6_CyclicSortPattern {
    //TODO Cyclic Sort (easy)
    public static int[] sort(int[] nums) {
        // TODO: Write your code here

        int i  = 0;
        while ( i < nums.length){
            if (nums[i] != i+1){
                swap(nums, i, nums[i] -1);
            }else {
                i++;
            }
        }
        return nums;
    }

    public static void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    //TODO Find the Missing Number (easy)
    public static int findMissingNumber(int[] nums) {
        // TODO: Write your code here
        int i = 0;
        while ( i < nums.length){
            if (nums[i] < nums.length && i != nums[i]){
                swap(nums, i, nums[i]);
            }
            else {
                i++;
            }
        }

        int ans = -1;

        for (int j = 0; j < nums.length; j++) {
            if (j != nums[j]){
                return j;
            }
        }
        return nums.length;
    }

    //TODO Find all Missing Numbers (easy)
    public static List<Integer> findNumbers(int[] nums) {
        List<Integer> missingNumbers = new ArrayList<>();

        int i = 0;
        while ( i < nums.length){
            if (nums[i] != nums[nums[i] - 1])
                swap(nums, i, nums[i] - 1);
            else
                i++;
        }

        for (int j = 0; j < nums.length; j++) {
            if (j != nums[j]-1){
                missingNumbers.add(j+1);
            }
        }
        // TODO: Write your code here
        return missingNumbers;
    }

    //TODO Find the Duplicate Number (easy)
    public static int findNumber(int[] nums) {
        // TODO: Write your code here
        int missingNumbers = 0;

        int i = 0;
        while ( i < nums.length){
            if (nums[i] != i+1){
                if (nums[i] != nums[nums[i] - 1]){
                    swap(nums, i, nums[i] - 1);
                }else {
                    return nums[i];
                }
            }else {
                i++;
            }

        }

//        for (int j = 0; j < nums.length; j++) {
//            if (j != nums[j]){
//                return nums[j];
//            }
//        }
        // TODO: Write your code here

        return -1;
    }

    //TODO Find all Duplicate Numbers (easy)
    public static List<Integer> findNumbers2(int[] nums) {
        List<Integer> duplicateNumbers = new ArrayList<>();
        // TODO: Write your code here
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i+1){
                swap(nums, i, nums[i]-1);
            }else{
                i++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i+1){
                duplicateNumbers.add(nums[i]);
            }
        }
        return duplicateNumbers;
    }
    //TODO Problem Challenge 1: Find the Corrupt Pair (easy)
    public static int[] findNumbers3(int[] nums) {
        // TODO: Write your code here

        int i = 0;
        while ( i < nums.length) {
            if (nums[i] != nums[nums[i] - 1]){
                    swap(nums, i, nums[i] - 1);
            }
            else {
                i++;
            }
        }

        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1 )
                return new int[] { nums[i], i+1};
        }
        return new int[] { -1,-1 };
    }

    //TODO Problem Challenge 2: Find the Smallest Missing Positive Number (medium)
    public static int findNumber4(int[] nums) {
        // TODO: Write your code here
        int i = 0;
        while ( i < nums.length){
            if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1])
                swap(nums, i, nums[i] - 1);
            else
                i++;
        }
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != j+1)
                return j+1;
        }

        return nums.length + 1;
    }


    //TODO Problem Challenge 3: Find the First K Missing Positive Numbers (hard)
    public static List<Integer> findNumbers(int[] nums, int k) {
        List<Integer> missingNumbers = new ArrayList<>();
        // TODO: Write your code here
        int i  = 0;
        while (i < nums.length){
            if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1])
                swap(nums, i, nums[i] - 1);
            else
                i++;
        }
        Set<Integer> extraNumbers = new HashSet<>();
        for ( i = 0; i < nums.length && missingNumbers.size() < k; i++){
            if (nums[i] != i+1){
                missingNumbers.add(i+1);
                extraNumbers.add(nums[i]);
            }
        }

        for (i = 1; missingNumbers.size() < k; i++){
            int num = i + nums.length;
            if (!extraNumbers.contains(num)){
                missingNumbers.add(num);
            }
        }

        return missingNumbers;
    }

    public static void main(String[] args) {
        System.out.println(c6_CyclicSortPattern.findMissingNumber(new int[] { 0, 1, 1, 3 }));

    }
}
