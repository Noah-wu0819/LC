import java.util.*;


public class c4_Sliding_Window {
    //TODO Maximum Sum Subarray of Size K (easy)
    public int findMaxSumSubArray(int k, int[] arr) {
        // TODO: Write your code here
        int max = Integer.MIN_VALUE;
        int windowEnd = 0;
        int windowStart = 0;
        int windowSum = 0;

        for (;windowEnd < arr.length; windowEnd++){
            windowSum += arr[windowEnd];

            if (windowEnd >= k-1){
                max = Math.max(max, windowSum);
                windowSum -= arr[windowStart];
                windowStart++;
            }
        }
        return max;
    }

    //TODO Smallest Subarray With a Greater Sum (easy)
    public static int findMinSubArray(int S, int[] arr) {
        // TODO: Write your code here
        int sum  = 0;
        int end = 0;
        int start = 0;
        int minSize = Integer.MAX_VALUE;
        for (; end < arr.length; end++){
            sum += arr[end];
            while (sum >= S){
                sum -= arr[start];
                minSize = Math.min(end - start + 1, minSize);
                start++;
            }
        }
        return minSize == Integer.MAX_VALUE? 0: minSize;
    }
    //TODO Longest Substring with K Distinct Characters (medium)
    public static int findLength(String str, int k) {
        int maxLength = 0;
        // TODO: Write your code here.
        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0;
        for (int end = 0; end < str.length(); end++){
            map.put(str.charAt(end), map.getOrDefault(str.charAt(end), 0) + 1);
            while (map.size() > k){
                if (map.containsKey(str.charAt(start))) {
                    map.put(str.charAt(start), map.get(str.charAt(start))-1);
                }
                if (map.get(str.charAt(start)) == 0) {
                    map.remove(str.charAt(start));
                }
                start++;
            }
            maxLength = Math.max(maxLength, end-start+1);
        }
        return maxLength;
    }

    //TODO Fruits into Baskets (medium)
    public static int findLength(char[] arr) {
        if (arr.length < 1) return 0;

        int maxLength = 0;

        // TODO: Write your code here
        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0;
        for (int end = 0; end < arr.length; end++) {
            map.put(arr[end], map.getOrDefault(arr[end], 0) + 1);

            while (map.size() > 2){
                map.put(arr[start], map.get(arr[start]) - 1);
                if (map.get(arr[start]) == 0)
                    map.remove(arr[start]);
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }

    //TODO Longest Substring with Same Letters after Replacement (hard)
    public static int findLength1(String str, int k) {
        int maxLength = 0;
        char[] chars = str.toCharArray();
        // TODO: Write your code here
        HashMap<Character, Integer> map = new HashMap<>();
        int maxRepeatLetterCount = 0;
        int start = 0;
        for (int end = 0; end < chars.length; end++) {
            map.put(chars[end], map.getOrDefault(chars[end], 0) + 1);
            maxRepeatLetterCount = Math.max(maxRepeatLetterCount, map.get(chars[end]));

            if (end - start + 1 - maxRepeatLetterCount > k){
                map.put(chars[start], map.get(chars[start]) - 1);
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }
    //TODO Longest Subarray with Ones after Replacement (hard)
    public static int findLength(int[] arr, int k) {
        int maxLength = 0;
        // TODO: Write your code here
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxRepeatLetterCount = 0;
        int start = 0;
        for (int end = 0; end < arr.length; end++) {
            map.put(arr[end], map.getOrDefault(arr[end], 0) + 1);
            if (arr[end] == 1)
                maxRepeatLetterCount = Math.max(maxRepeatLetterCount, map.get(arr[end]));
            if (end - start+1 -maxRepeatLetterCount > k){
                map.put(arr[start], map.get(arr[start])-1);
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }

    //TODO Problem Challenge 1: Permutation in a String (hard)

    public static boolean findPermutation(String str, String pattern) {
        // TODO: Write your code here
        int start = 0; int matched = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            map.put(pattern.charAt(i), map.getOrDefault(pattern.charAt(i), 0) + 1);
        }

        for (int end = 0; end < str.length(); end++) {
            if (map.containsKey(str.charAt(end))){
                map.put(str.charAt(end), map.get(str.charAt(end)) - 1);
                if (map.get(str.charAt(end)) == 0)
                    matched++;
            }

            if (matched == map.size()){
                return true;
            }

            if (end >= pattern.length() - 1){// pattern.length() = end - start + 1
                if (map.containsKey(str.charAt(start))){
                    if (map.get(str.charAt(start)) == 0)
                        matched--;
                    map.put(str.charAt(start), map.get(str.charAt(start)) + 1);
                }
                start++;
            }
        }
        return false;
    }

    //TODO Problem Challenge 2: String Anagrams (hard
    public static List<Integer> findStringAnagrams(String str, String pattern) {
        List<Integer> resultIndices = new ArrayList<Integer>();

        int start = 0;
        int match = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            map.put(pattern.charAt(i), map.getOrDefault(pattern.charAt(i), 0) + 1);
        }

        for (int end = 0; end < str.length(); end++){
            if (map.containsKey(str.charAt(end))){
                map.put(str.charAt(end), map.get(str.charAt(end)) - 1);
                if (map.get(str.charAt(end)) == 0){
                    match++;
                }
            }

            if (match == map.size()){
                resultIndices.add(start);
            }

            if (end >= pattern.length() - 1){
                char startChar = str.charAt(start);
                if (map.containsKey(startChar)){
                    if (map.get(startChar) == 0)
                        match--;
                    map.put(startChar, map.get(startChar) + 1);
                }
                start++;
            }
        }
        // TODO: Write your code here
        return resultIndices;

    }

    //TODO Problem Challenge 3: Smallest Window containing Substring (hard)
    public static String findSubstring(String str, String pattern) {
        int left = 0;
        int match = 0;
        String min = str;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            map.put(pattern.charAt(i), map.getOrDefault(pattern.charAt(i), 0) + 1);
        }
        for (int right = 0; right < str.length(); right++) {
            if (map.containsKey(str.charAt(right))){
                map.put(str.charAt(right), map.get(str.charAt(right)) - 1);
                if (map.get(str.charAt(right)) >= 0){
                    match++;
                }
            }
            while (match == pattern.length()){

                    if (min.length() > (right - left +1)){
                        min = str.substring(left, right+1);
                    }
                    char leftchar = str.charAt(left++);
                    if (map.containsKey(leftchar)){
                        map.put(leftchar, map.get(leftchar) + 1);
                    }
            }


        }
        // TODO: Write your code here
        return str;
    }

    //TODO Problem Challenge 4: Words Concatenation (hard)
    public static List<Integer> findWordConcatenation(String str, String[] words) {
        List<Integer> resultIndices = new ArrayList<Integer>();
        HashMap<String, Integer> wordFrequencyMap = new HashMap<>();
        for (String word : words){
            wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
        }
        int count = words.length; int length = words[0].length();
        for (int i = 0; i <= str.length() - count * length; i++) {
            Map<String, Integer> wordsSeen = new HashMap<>();
            for (int j = 0; j < count; j++) {
                int nextIndex = i + j * length;
                String word = str.substring(nextIndex, nextIndex + length);
                if (!wordFrequencyMap.containsKey(word)) break;
                wordsSeen.put(word, wordsSeen.getOrDefault(word, 0) + 1);
                if (wordsSeen.get(word) > wordFrequencyMap.getOrDefault(word, 0))break;
                if (j + 1 == count) resultIndices.add(i);
            }
        }
        // TODO: Write your code here
        return resultIndices;
    }

    public static void main(String[] args) {

        System.out.println("Permutation exist: "
                + c4_Sliding_Window.findPermutation("abcabcabc", "abcd"));

    }

}
