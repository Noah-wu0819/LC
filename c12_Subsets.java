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
        permutations.add(str);
        char[] chars = str.toCharArray();
        for (char c: chars){
            int n = permutations.size();
            for (int i = 0; i < n; i++) {
                String newStr = permutations.get(i);
                if (Character.isLetter(c)){
                    if (Character.isLowerCase(c)){
                        String ans = newStr.replace(c, Character.toUpperCase(c));
                        permutations.add(ans);
                    }else {
                        String ans = newStr.replace(c, Character.toLowerCase(c));
                        permutations.add(ans);
                    }
                }
            }
        }

        // TODO: Write your code here
        return permutations;
    }

    //TODO Balanced Parentheses (hard)
    static class Parentheses{
        String str;
        int openCount;
        int closeCount;
        public Parentheses(String str, int openCount, int closeCount){
            this.str = str;
            this.openCount = openCount;
            this.closeCount = closeCount;
        }
    }
    public static List<String> generateValidParentheses(int num) {
        List<String> result = new ArrayList<String>();
        Queue<Parentheses> queue = new LinkedList<>();
        queue.add(new Parentheses("", 0, 0));
        while (!queue.isEmpty()){
            Parentheses ps = queue.poll();
            if (ps.openCount == num && ps.closeCount == num){
                result.add(ps.str);
            }else {
                if (ps.openCount < num){
                    queue.add(new Parentheses(ps.str + '(', ps.openCount+1, ps.closeCount));
                }

                if (ps.openCount > ps.closeCount){
                    queue.add(new Parentheses(ps.str + ')', ps.openCount, ps.closeCount + 1));
                }
            }
        }

        // TODO: Write your code here
        return result;
    }

    //TODO Unique Generalized Abbreviations (hard)
    static class AbbreviatedWord {
        StringBuilder str;
        int start;
        int count;

        public AbbreviatedWord(StringBuilder str, int start, int count) {
            this.str = str;
            this.start = start;
            this.count = count;
        }
    }
    public static List<String> generateGeneralizedAbbreviation(String word) {
        List<String> result = new ArrayList<String>();
        // TODO: Write your code here
        return result;
    }
}
