public class c13_Binary_Search {
    //TODO Order-agnostic Binary Search (easy)

    public static int search(int[] arr, int key) {
        // TODO: Write your code here
        int start = 0; int end = arr.length - 1;

        boolean isAscending = arr[end] > arr[start];


            while (start <= end){
                int mid = start + (end - start)/2;
                if (arr[mid] == key)
                    return mid;
                if (isAscending){


                if (arr[mid] > key){
                    end = mid - 1;
                }else {
                    start = mid + 1;
                }
            }else {
                if (arr[mid] < key){
                    end = mid - 1;
                }else {
                    start = mid + 1;
                }
            }
            }

        return -1; // element not found
    }

    //TODO Ceiling of a Number (medium)
    public static int searchCeilingOfANumber(int[] arr, int key) {
        // TODO: Write your code here
        int start = 0;
        int end = arr.length - 1;

        if (key > arr[end]){
            return -1;
        }
        while (start <= end){
            int mid = start + (end - start)/2;
            if (key > arr[mid]){
                start = mid + 1;
            }else if (key < arr[mid]){
                end = mid - 1;
            }else {
                return mid;
            }
        }


        return start;


    }

    //TODO Next Letter (medium)
    public static char searchNextLetter(char[] letters, char key) {
        // TODO: Write your code here
        if (key > letters[letters.length - 1]){
            return letters[0];
        }

        int start = 0;
        int end = letters.length - 1;

        while (start <= end){
            int mid = start + (end - start) / 2;
            if (key >= letters[mid]){
                start = mid + 1;
            }else {
                end = mid - 1;
            }
        }
        return letters[0];
    }

    //TODO Number Range (medium)
    public static int[] findRange(int[] arr, int key) {
        int[] result = new int[] { -1, -1 };
        // TODO: Write your code here
        result[0] = findResRange(arr, key, false);
        if (result[0] != -1)
            result[1] = findResRange(arr, key, true);
        return result;
    }

    private static int findResRange(int[] arr, int key, boolean isFindMaxIndex) {
        int keyIndex = -1;
        int start = 0;
        int end = arr.length - 1;

        while (start <= end){
            int mid = start + (end - start) / 2;
            if (key < arr[mid]){
                end = mid - 1;
            } else if (key > arr[mid]) {
                start = mid + 1;
            } else{
                keyIndex = mid;
                if (isFindMaxIndex){
                    start = mid + 1;
                }else {
                    end = mid - 1;
                }
            }
        }

        return keyIndex;
    }
    //is arr[mid] not mid !!!!!!!!!!!!

    //TODO Search in a Sorted Infinite Array (medium)
    class ArrayReader {
        int[] arr;

        ArrayReader(int[] arr) {
            this.arr = arr;
        }

        public int get(int index) {
            if (index >= arr.length)
                return Integer.MAX_VALUE;
            return arr[index];
        }
    }
    public static int searchInfiniteSortedArray(ArrayReader reader, int key) {
        // TODO: Write your code here
        return -1;
    }
}
