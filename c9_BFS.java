import java.util.*;

public class c9_BFS {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    };
    //TODO Binary Tree Level Order Traversal (easy)
    public static List<List<Integer>> traverse(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // TODO: Write your code here

        if( root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()){
            int length = q.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                TreeNode cur = q.poll();
                list.add(cur.val);

                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            result.add(list);
        }
        return result;
    }

    //TODO Reverse Level Order Traversal (easy)
    public static List<List<Integer>> traverse1(TreeNode root) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        // TODO: Write your code here
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> layer = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                layer.add(cur.val);

                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            result.add(0,layer);
        }

        return result;
    }

    //TODO Zigzag Traversal (medium)
    public static List<List<Integer>> traverse2(TreeNode root) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        // TODO: Write your code here

        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int layer = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            layer++;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (layer%2 == 1){
                    list.add(cur.val);
                }else {
                    list.add(0, cur.val);
                }
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            result.add(list);
        }
        return result;
    }

    //TODO Level Averages in a Binary Tree (easy)

    public static List<Double> findLevelAverages(TreeNode root) {
        List<Double> result = new ArrayList<>();
        // TODO: Write your code here

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            double mid = 0;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                mid += cur.val;

                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            result.add(mid/size);
        }
        return result;
    }

    //TODO Minimum Depth of a Binary Tree (easy)
    public static int findDepth(TreeNode root) {
        int minimumTreeDepth = Integer.MAX_VALUE;
        // TODO: Write your code here
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            minimumTreeDepth++;

            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.right == null && cur.left == null){
                    return minimumTreeDepth ;
                }

                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);

            }
        }
        return minimumTreeDepth;
    }

    //TODO Level Order Successor (easy)
    public static TreeNode findSuccessor(TreeNode root, int key) {
        Queue<TreeNode> queue = new LinkedList<>();
        // TODO: Write your code here
        queue.offer(root);

        while (!queue.isEmpty()){
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();


                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
                if (cur.val == key ){
                    break;
                }
            }
        }
        return queue.peek();
    }
    //TODO Connect Level Order Siblings (medium)
    public static List<List<Integer>> connect(TreeNode root) {
        // TODO: Write your code here
        Queue<TreeNode> queue = new LinkedList<>();
        // TODO: Write your code here
        queue.offer(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            TreeNode pre = null;
            for (int i = 0; i < size; i++) {

                TreeNode cur = queue.poll();

                if (pre != null){
                    pre.next = cur;
                }
                pre = cur;
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
        }
        // Do not modify this
        return printLevelOrder(root);
    }
    //TODO Problem Challenge 1: Connect All Level Order Siblings (medium)
    public static List<Integer> connect(TreeNode root) {
        // TODO: Write your code here
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode pre = null;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (pre != null){
                    pre.next = cur;
                }
                pre = cur;
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
        }
        // Do no modify this
        return printLevelOrder(root);
    }

    //TODO Problem Challenge 2: Right View of a Binary Tree (easy)
    public static List<Integer> traverse4(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        // TODO: Write your code here
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (i == size - 1){
                    result.add(cur.val);
                }
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
        }
        return result;
    }

}
