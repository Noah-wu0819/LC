/*
* Taking XOR of a number with itself returns 0, e.g., 1 ^ 1 = 0 29 ^ 29 = 0
Taking XOR of a number with 0 returns the same number, e.g., 1 ^ 0 = 1 31 ^ 0 = 31
XOR is Associative & Commutative, which means: (a ^ b) ^ c = a ^ (b ^ c) a ^ b = b ^ a
* */


class Solution {
    //TODO Single Number (easy)
    public static int findSingleNumber(int[] arr) {
        int num = 0;
        for (int i = 0; i < arr.length; i++) {
            num ^= arr[i];
        }
        return num;
    }
    //TODO Two Single Numbers (medium)
    public static int[] findSingleNumbers(int[] nums) {
        // TODO: Write your code here
        int n1xn2 = 0;
        int right = 1;
        int n1 = 0;
        int n2 = 0;
        for (int i = 0; i < nums.length; i++) {
            n1xn2 ^= nums[i];
        }

        while ((right & n1xn2) == 0){
            right <<= 1;
        }

        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & right) != 0){
                n1 ^= nums[i];
            }else {
                n2 ^= nums[i];
            }
        }
        return new int[] { n1, n2};
    }
}