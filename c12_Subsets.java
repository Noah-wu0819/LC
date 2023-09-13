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

    //TODO Problem Challenge 1: Evaluate Expression (hard)
    public static List<Integer> diffWaysToEvaluateExpression(String input) {
        List<Integer> result = new ArrayList<>();
        // TODO: Write your code here
        if(!input.contains("+") && !input.contains("-") && !input.contains("*")){
            result.add(Integer.parseInt(input));
        }else {
            for (int i = 0; i < input.length(); i++) {
                if (!Character.isDigit(input.charAt(i))){
                    List<Integer> left = diffWaysToEvaluateExpression(input.substring(0, i));
                    List<Integer> right = diffWaysToEvaluateExpression(input.substring(i+1));

                    for (int l: left){
                        for(int r: right){
                            if (input.charAt(i) == '+'){
                                result.add(l+r);
                            }else if (input.charAt(i) == '-'){
                                result.add(l-r);
                            } else if (input.charAt(i) == '*') {
                                result.add(l*r);
                            }
                        }
                    }
                }
            }
        }
        //pay more attention to the scope of List<Integer> varaiables can help me understand this code better
        return result;
    }
    //TODO Problem Challenge 2: Structurally Unique Binary Search Trees (hard)
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    };
    public static List<TreeNode> findUniqueTrees(int n) {
        if (n <= 0)
            return new ArrayList<TreeNode>();

        return findUniqueTreesRecursive(1, n);
    }

    private static List<TreeNode> findUniqueTreesRecursive(int start, int end) {
        List<TreeNode> result = new ArrayList<>();
        if (start > end){
            result.add(null);
            return result;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTree = findUniqueTreesRecursive(start, i);
            List<TreeNode> rightTree = findUniqueTreesRecursive(i+1, end);

            for (TreeNode left: leftTree){
                for(TreeNode right: rightTree){
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        return result;
    }
    // understand the recursive method further to solve BST
}
