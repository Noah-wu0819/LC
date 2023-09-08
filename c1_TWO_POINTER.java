import java.util.*;

public class c1_TWO_POINTER {

    public static int[] search(int[] arr, int targetSum) {
        // TODO: Write your code here

        int left = 0;
        int right = arr.length - 1;

        while (left < right){
            if ( arr[left] + arr[right] == targetSum){
                return new int[] {left, right};
            }
            else if (arr[left] + arr[right] < targetSum){
                left++;
            }else{
                right--;
            }
        }
        return new int[] { -1, -1 };
    }

    //TODO remove duplicates
    public static int remove(int[] arr) {
        // TODO: Write your code here
       int nextNoDuplicate = 1;

       for (int i = 0; i < arr.length; i++){
           if (arr[nextNoDuplicate - 1] != arr[i]){
               arr[nextNoDuplicate] = arr[i];
               nextNoDuplicate++;
           }
       }
        return nextNoDuplicate;
    }

    //TODO squaring a sorted array
    public static int[] makeSquares(int[] arr) {
        int n = arr.length;
        int[] squares = new int[n];
        int ansIndex = n - 1;
        int left = 0; int right = n - 1;
        while ( left <= right){
            if ((arr[left] * arr[left]) > (arr[right] * arr[right])){
                squares[ansIndex--] = arr[left] * arr[left];
                left++;
            }else{
                squares[ansIndex--] = arr[right] * arr[right];
                right--;
            }
        }

        // TODO: Write your code here
        return squares;
    }

    //TODO Triplet sum to zero
    public static List<List<Integer>> searchTriplets(int[] arr) {
        List<List<Integer>> triplets = new ArrayList<>();
        // TODO: Write your code here

        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 2; i++){
            if (i > 0 && arr[i-1] == arr[i]) continue;
            find(arr, -arr[i], i+1, triplets);
        }

        return triplets;
    }

    private static void find(int[] arr, int key, int left, List<List<Integer>> triplets) {
        int right = arr.length - 1;
        while (left < right){
            if (arr[left] + arr[right] == key){
                triplets.add(Arrays.asList(-key, arr[left], arr[right]));
                left++;
                right--;

                while (left< right && arr[left] == arr[left-1]) left++;
                while (left< right && arr[right] == arr[right+1]) right--;
            }

            if (arr[left] + arr[right] > key){
                right--;
            }
            if (arr[left] + arr[right] < key){
                left++;
            }
        }
    }
    //TODO Triplet Sum Close to Target (medium)
    public static int searchTriplet(int[] arr, int targetSum) {
        // TODO: Write your code here
        // TODO: Write your code here

        if (arr == null || arr.length < 3) return -1;
        Arrays.sort(arr);

        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 2; i++){
            int left = i+1; int right = arr.length - 1;
            while (left < right){
                int targetDiff = targetSum - arr[i] - arr[left] - arr[right];
                if (targetDiff == 0) return targetSum;

                if ((Math.abs(targetDiff) < Math.abs(minDiff)) || ((Math.abs(targetDiff) == Math.abs(minDiff)) && targetDiff > minDiff)){
                    minDiff = targetDiff;
                }
                if (targetDiff > 0) left++;
                else right--;
            }

        }
        return targetSum - minDiff;
    }

    //TODO Triplets with Smaller Sum (medium)
    public static int searchTriplets(int[] arr, int target) {
        if (arr == null || arr.length < 3) return -1;
        int count = 0;
        // TODO: Write your code here
        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 2; i++){
            int left = i+1; int right = arr.length - 1;
            while (left < right){
                int targetDiff = target - arr[i] - arr[left] - arr[right];

                if (targetDiff > 0) {
                    count+= right - left;
                    left++;
                }
                else right--;
            }

        }
        return count;
    }

    //TODO Subarrays with Product Less than a Target (medium)
    public static List<List<Integer>> findSubarrays(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // TODO: Write your code here
        int left = 0;

        int product = 1;//TODO
        for (int right = 0; right < arr.length; right++){
            List<Integer> temp = new ArrayList<>();
            product *= arr[right];
            while (product >= target && left < arr.length){
                product /= arr[left++];
            }

            for (int i = right; i >= left; i--){
                if (product < target){
                    temp.add(arr[i]);
                    System.out.println("temp.add("+ arr[i]+")");
                    System.out.println(temp);
                    result.add(new ArrayList<>(temp));
                }
            }
        }
        return result;
    }

    public static void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    //TODO Dutch National Flag Problem (medium)
    public static int[] sort(int[] arr) {
        // TODO: Write your code here
        int left = 0;
        int right = arr.length - 1;
        int index = 0;
        while (index <= right){
            if (arr[index] == 0){
                swap(arr, index++, left++);
            }else if (arr[index] == 1){
                index++;
            }else {
                swap(arr, index, right--);
            }
        }
        for (int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }

        return arr;
    }

    //TODO Problem Challenge 1: Quadruple Sum to Target (medium)
    public static List<List<Integer>> searchQuadruplets(int[] arr, int target) {
        List<List<Integer>> quadruplets = new ArrayList<>();
        // TODO: Write your code here
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 3; i++){
            if (i != 0 && (arr[i] == arr[i - 1])) continue;
            for (int j = i+1; j < arr.length - 2; j++) {
                if (j != i + 1 && (arr[j] == arr[j-1])) continue;
                searchQ(arr, quadruplets, i, j, target);
            }
        }
        return quadruplets;
    }

    private static void searchQ(int[] arr, List<List<Integer>> quadruplets, int i, int j, int target) {
        int left = j + 1;
        int right = arr.length - 1;
        while (left < right){
            int sum = arr[i] + arr[j] + arr[left] + arr[right];

            if (sum == target){
                List<Integer> l = new ArrayList<>();
                l.add(arr[i]);
                l.add(arr[j]);
                l.add(arr[left]);
                l.add(arr[right]);
                quadruplets.add(l);
                left++;
                right--;
                while (left < right && (arr[left] ==  arr[left-1])){
                    left++;
                }
                while (left < right && (arr[right] ==  arr[right+1])){
                    right--;
                }
            } else if (sum < target) {
                left++;

            }else {
                right--;

            }
        }
    }

    //TODO Problem Challenge 2: Comparing Strings containing Backspaces (medium)
    public static boolean compare(String str1, String str2) {
        // TODO: Write your code here
        int index1 = str1.length() - 1;
        int index2 = str2.length() - 1;

        while (index1 >= 0 || index2 >= 0){
            int i1 = searchLastChar(str1, index1);
            int i2 = searchLastChar(str2, index2);

            if (i1 < 0 && i2 < 0) return true;
            if (i1 < 0 || i2 < 0) return false;

            if (str1.charAt(i1) != str2.charAt(i2)) return false;
            index1 = i1 - 1;
            index2 = i2 - 1;

        }
        return true;
    }

    private static int searchLastChar(String str, int index) {

        int count = 0;
        while (index >= 0){
            if (str.charAt(index) == '#') count++;
            else if (count > 0) count--;
            else break;
            index--;
        }

        return index;
    }

    //TODO Problem Challenge 3: Minimum Window Sort (medium)
    public static int sort1(int[] arr) {
        // TODO: Write your code here
        int left = 0; int right = arr.length - 1;
        while (left < arr.length - 1 && arr[left] < arr[left + 1]){
            left++;
        }
        if (left == arr.length - 1) return 0;
        while (right >= 0 && arr[right - 1] < arr[right]){
            right--;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = left; i <= right; i++) {
            if (arr[i] < min) min = arr[i];
            if (arr[i] > max) max = arr[i];
        }

        for (int i = left - 1; i >= 0; i--) {
            if (arr[i] > min) left--;
        }
        for (int i = right + 1; i < arr.length; i++) {
            if (arr[i] < max) right++;
        }
        return right - left + 1;
    }

    public static void main(String[] args) {
        c1_TWO_POINTER c = new c1_TWO_POINTER();
        int[] a = {1,0,2,1,0};

        System.out.println( c1_TWO_POINTER.sort(a));
    }
}
