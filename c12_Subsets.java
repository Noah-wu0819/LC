import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class c12_Subsets {
    //TODO Subsets (easy)
    public static List<List<Integer>> findSubsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        // TODO: Write your code here
        subsets.add(new ArrayList<>());
        for (int num : nums){
            int n = subsets.size();
            for (int i = 0; i < n; i++) {
                List<Integer> set = new ArrayList<>(subsets.get(i));
                set.add(nums[i]);
                subsets.add(set);
            }
        }
        return subsets;
    }

    //TODO Subsets With Duplicates (easy)
    public static List<List<Integer>> findSubsets1(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        // TODO: Write your code here
        subsets.add(new ArrayList<>());
        for(int num : nums){
            int n = subsets.size();
            for (int i = 0; i < n; i++) {
                List<Integer> set = new ArrayList<>(subsets.get(i));
                set.add(num);
                if (!subsets.contains(set))
                    subsets.add(set);
            }
        }
        return subsets;
    }

    //TODO Permutations (medium)
    public static List<List<Integer>> findPermutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // TODO: Write your code here
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(new ArrayList<>());
        for (int num: nums){
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                List<Integer> curList = queue.poll();
                int curListSize = curList.size();
                for (int j = 0; j <= curListSize; j++) {//insert new value in diff index
                    List<Integer> newList = new ArrayList<>(curList);
                    newList.add(j, num);
                    if (newList.size() == nums.length){
                        result.add(newList);
                    }else {
                        queue.add(newList);
                    }
                }
            }
        }
        return result;
    }

    //TODO String Permutations by changing case (medium)
    public static List<String> findLetterCaseStringPermutations(String str) {
        List<String> permutations = new ArrayList<>();
        // TODO: Write your code here
        return permutations;
    }
}
