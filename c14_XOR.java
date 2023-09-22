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

    //TODO Complement of Base 10 Number (medium)
    public static int bitwiseComplement(int num) {
        // TODO: Write your code here
        int count = 0;
        int n = num;
        while (n != 0){
            count++;
            n >>= 1;
        }

        int all_set = (int)(Math.pow(2, count) - 1);

        return num ^ all_set;
    }

    //TODO Problem Challenge 1: Flip and Invert an Image (hard)
    public static int[][] flipAndInvertImage(int[][] arr) {
        // TODO: Write your code here
        int col = arr[0].length;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < (col+1)/2; j++) {
                int temp = arr[i][j] ^ 1;
                arr[i][j] = arr[i][col - 1 - j] ^ 1;
                arr[i][col - 1 - j] = temp;
            }
        }
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr[0].length/2; j++) {
//                int temp = arr[i][j];
//                arr[i][j] = arr[i][arr[0].length - 1 -j];
//                arr[i][arr.length - 1 -j] = temp;
//            }
//        }
//
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr[0].length; j++) {
//                arr[i][j] ^= 1;
//            }
//        }
        return arr;
    }
}