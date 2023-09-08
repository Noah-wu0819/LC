import java.util.*;

public class c10_DFS {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    };
    //TODO Binary Tree Path Sum (easy)
    public static boolean hasPath(TreeNode root, int sum) {
        // TODO: Write your code here
        if( root == null) return false;
        if (root.val == sum && root.left == null && root.right == null) return true;

        return hasPath(root.left, sum - root.val) || hasPath(root.right, sum - root.val);
    }

    //TODO All Paths for a Sum (medium)
    public static List<List<Integer>> findPaths(TreeNode root, int sum) {
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        onePath(root, sum, allPaths, path);
        // TODO: Write your code here
        return allPaths;
    }

    public static void onePath(TreeNode root, int sum, List<List<Integer>> allPaths, List<Integer> path){
        if (root == null) return;

        path.add(root.val);
        if (root.val == sum && root.left == null && root.right == null) {
            allPaths.add(new ArrayList<Integer>(path));
        }else{
            onePath(root.left, sum - root.val, allPaths, path);
            onePath(root.right, sum - root.val, allPaths, path);
        }
        path.remove(path.size() - 1);
    }

    //TODO Sum of Path Numbers (medium)
    public static int findSumOfPathNumbers(TreeNode root) {
        // TODO: Write your code here
       return findRootToLeafPathNumbers(root, 0);


//        List<List<Integer>> allPaths = new ArrayList<>();
//        List<Integer> path = new ArrayList<>();
//        onepath2(root, allPaths, path);
//        int sum = 0;
//        for(List<Integer> num : allPaths){
//            int pathNum = 0;
//            for (int i = 0; i < num.size(); i++) {
//                pathNum += num.get(i) * Math.pow(10, i);
//            }
//            sum += pathNum;
//        }
    }

    private static int findRootToLeafPathNumbers(TreeNode root, int curSum) {
        if (root == null) return 0;

        curSum += 10 * curSum + root.val;

        if (root.left == null && root.right == null)
            return curSum;

        return findRootToLeafPathNumbers(root.left, curSum) + findRootToLeafPathNumbers(root.right, curSum);
    }

//    private static void onepath2(TreeNode root, List<List<Integer>> allPaths, List<Integer> path) {
//        if (root == null) return;
//        path.add(0, root.val);
//        if (root.left == null && root.right == null){
//            allPaths.add(new ArrayList<Integer>(path));
//        }else {
//            onepath2(root.left, allPaths, path);
//            onepath2(root.right, allPaths, path);
//        }
//        path.remove(0);
//    }

    //TODO Path With Given Sequence (medium)
    public static boolean findPath(TreeNode root, int[] sequence) {
        // TODO: Write your code here
        if (root == null) return sequence.length == 0;
        return fp(root, sequence, 0);
    }

    public static boolean fp(TreeNode root, int[] sequence, int index) {
        if (root == null) return false;
        if (index >= sequence.length || sequence[index] != root.val) return false;

        if (index == sequence.length - 1 &&  root.left == null && root.right == null){
            return true;
        }
        return fp(root.left, sequence, index + 1) || fp(root.right, sequence, index + 1);
    }

    //TODO Count Paths for a Sum (medium)
    public static int countPaths(TreeNode root, int S) {
        // TODO: Write your code here
        List<Integer> curPath = new ArrayList<>();

        return countPathRecursive(root, S, curPath);
    }

    private static int countPathRecursive(TreeNode root, int s, List<Integer> curPath) {
        if (root == null) return 0;
        curPath.add(root.val);

        int pathCount = 0;
        double pathSum = 0;
        ListIterator<Integer> listIterator = curPath.listIterator(curPath.size());
        while (listIterator.hasPrevious()){
            pathSum += listIterator.previous();
            if (pathSum == s){
                pathCount++;
            }
        }

        pathCount += countPathRecursive(root.left, s, curPath);
        pathCount += countPathRecursive(root.right, s, curPath);

        curPath.remove(curPath.size() - 1);
        return pathCount;
    }

}
