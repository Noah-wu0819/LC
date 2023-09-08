import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class c0_WARMUP {


        public boolean containsDuplicate(int[] nums) {
            // TODO: Write your code here
            HashSet<Integer> set = new HashSet<>();
            for(int i = 0; i < nums.length; i++){
                if (set.contains(nums[i])){
                    return false;
                }else {
                    set.add(nums[i]);
                }
            }
            return true;
        }

    public boolean checkIfPangram(String sentence) {
        // TODO: Write your code here
        HashSet<Character> set = new HashSet<>();
        char[] chars = sentence.toLowerCase().toCharArray();
        for (char c: chars){
            set.add(c);
        }
        return set.size() >= 26? true: false;
    }

    //TODO:Sqrt
    public int mySqrt(int x) {
        // TODO: Write your code here
        if (x < 2) return x;

        int left = 2;
        int right = x/2;
        int pivot;
        long ans;
        while (left <= right){
            pivot = left + (right - left)/2;
            ans = (long)pivot * pivot;

            if (ans < x){
                left = pivot + 1;
            } else if (ans > x) {
                right = pivot - 1;
            }else {
                return pivot;
            }
        }
        return right;
    }

    public void swap(char[] chars, int i, int j){
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
    }
    //TODO REVERSE VOWEL
    public String reverseVowels(String s) {
        // TODO: Write your code here
        String key = "aAeEiIoOuU";
        HashSet<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');
        char[] chars = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;

        while (left < right){
            while (left < right &&!set.contains(chars[left])){
                left++;
            }
            while (left < right &&!set.contains(chars[right])){
                right--;
            }
            swap(chars, left, right);
            left++;
            right--;
        }
        return new String(chars);
    }

    //TODO:valid palindrome
    public boolean isPalindrome(String s) {
        // TODO: Write your code here
        int left = 0;
        int right = s.length() - 1;
        while (left < right){
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))){
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))){
                right--;
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    //TODO Anagram

        public boolean isAnagram(String s, String t) {
            // TODO: Write your code here
            if (s.length() != t.length()) return false;
            HashMap<Character, Integer> map = new HashMap<>();

            for (int i = 0; i < s.length(); i++){
                map.put( s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);

                map.put( t.charAt(i), map.getOrDefault(t.charAt(i), 0) - 1);
            }

            for(Map.Entry<Character, Integer> entry: map.entrySet()){
                if (entry.getValue() != 0) return false;
            }


            return true;
        }

        //TODO shortest word distance
    public int shortestDistance(String[] words, String word1, String word2) {
        // TODO: Write your code here
        int index1 = -1;
        int index2 = -1;
        int ans = words.length;
        for (int i = 0; i < words.length; i++){
            if (words[i].equals(word1)){
                index1 = i;
            } else if (words[i].equals(word2)) {
                index2 = i;
            }

            if (index1 != -1 && index2 != -1){
                ans = Math.min(ans, Math.abs(index1 - index2));
            }
        }

        return ans;
    }

    //TODO nums of good pairs
    public int numGoodPairs(int[] nums) {
        int pairCount = 0;
        // TODO: Write your code here

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            if (map.get(nums[i]) > 1){
                pairCount += map.get(nums[i]) - 1;
            }
        }
        return pairCount;
    }
    public static void main(String[] args) {
        c0_WARMUP c = new c0_WARMUP();


        String s4 = "rat";
        String t4 = "rata";
        System.out.println(c.isAnagram(s4, t4));
    }
}
