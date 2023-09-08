import java.util.*;

public class c2_ISLAND {
    //TODO Number of Islands (easy)
    public static int countIslands(int[][] matrix) {
        int totalIslands = 0;
        // TODO: Write your code here


//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[0].length; j++) {
//                if (matrix[i][j] == 1){
//                    totalIslands++;
//
//                    DFS(matrix, i, j);
//                }
//            }
//        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1){
                    totalIslands++;

                    BFS(matrix,i, j);
                }
            }
        }
        return totalIslands;
    }

    public static void DFS(int[][] matrix, int i, int j){
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length)
            return;
        if (matrix[i][j] == 0)
            return;

        matrix[i][j] = 0;//设置已经标记这一步很重要，只能放在DFS或者BFS函数里面，如果放在for循环里面，就会漏掉很多点
        //或者设置一个二维visit数组

        DFS(matrix, i+1, j);
        DFS(matrix, i-1, j);
        DFS(matrix, i, j+1);
        DFS(matrix, i, j-1);
    }
    public static void BFS(int[][] matrix, int i, int j){
        Queue<int[]> queue = new ArrayDeque<>();

        queue.add(new int[]{i, j});

        while (!queue.isEmpty()){

            i = queue.peek()[0];
            j = queue.peek()[1];
            queue.remove();
            if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length)
                continue;
            if (matrix[i][j] == 0)
                continue;

            matrix[i][j] = 0;

            queue.add(new int[]{ i+1, j});
            queue.add(new int[]{ i-1, j});
            queue.add(new int[]{ i, j+1});
            queue.add(new int[]{ i, j-1});

        }
    }

    //TODO Biggest Island (easy)
    public static int maxAreaOfIsland(int[][] matrix) {
        int biggestIslandArea = 0;
        // TODO: Write your code here
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1){
                    int size = DFS2(matrix, i, j);
                    biggestIslandArea = Math.max(biggestIslandArea, size);
                }
            }
        }
        return biggestIslandArea;
    }

    private static int DFS2(int[][] matrix, int i, int j) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length)
            return 0;
        if (matrix[i][j] == 0)
            return 0;

        matrix[i][j] = 0;
        int count = 1;
        count += DFS2(matrix, i+1, j);
        count += DFS2(matrix, i-1, j);
        count += DFS2(matrix, i, j+1);
        count += DFS2(matrix, i, j-1);

        return count;
    }

    //TODO Flood Fill (easy)
    public static int[][] floodFill(int[][] matrix, int x, int y, int newColor) {
        // TODO: Write your code here
        int oldColor = matrix[x][y];
        if (oldColor != newColor)
            BFS2(matrix, x, y, newColor, oldColor);

        return matrix;
    }

    private static void BFS2(int[][] matrix, int i, int j, int newColor, int oldColor) {


        Queue<int[]> neighbors = new ArrayDeque<>();
        neighbors.add(new int[]{i, j});
        while (!neighbors.isEmpty()){
            i = neighbors.peek()[0];
            j = neighbors.peek()[1];
            neighbors.remove();

            if (i >= matrix.length || i < 0 || j >= matrix[0].length || j < 0){
                continue;
            }
            if (matrix[i][j] != oldColor) continue;
            matrix[i][j] = newColor;
            neighbors.add(new int[]{i+1, j});
            neighbors.add(new int[]{i-1, j});
            neighbors.add(new int[]{i, j+1});
            neighbors.add(new int[]{i, j-1});
            for (int a = 0; a < matrix.length;  a++){
                for (int b = 0; b < matrix[0].length; b++){
                    System.out.print(matrix[a][b]+" ");
                }
                System.out.println();
            }
            System.out.println("+++++++++++++++++++");
        }
    }

    //TODO Number of Closed Islands (easy)
    public static int countClosedIslands(int[][] matrix) {
        int countClosedIslands = 0;
        // TODO: Write your code here
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1){
                    countClosedIslands++;
                    boolean isValid = DFS4(matrix, i, j);
                    if (!isValid) countClosedIslands--;
                }
            }
        }
        return countClosedIslands;
    }

    private static boolean DFS4(int[][] matrix, int i, int j) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) return false;
        if (matrix[i][j] == 0) return true;

        matrix[i][j] = 0;
        boolean l = DFS4(matrix, i+1, j);
        boolean r = DFS4(matrix, i-1, j);
        boolean u = DFS4(matrix, i, j+1);
        boolean d = DFS4(matrix, i, j-1);
        return l&&r&&u&&d;
    }

    //TODO Problem Challenge 1
    public static int findIslandPerimeter(int[][] matrix) {
        // TODO: Write your code here
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1)return DFS5(matrix, i, j);
            }
        }
        return count;
    }

    public static int DFS5(int[][] matrix, int i, int j){
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] == 0) return 1;
        if (matrix[i][j] == 2) return 0;

        matrix[i][j] = 2;
        int count = 0;
        count += DFS5(matrix, i+1, j);
        count += DFS5(matrix, i-1, j);
        count += DFS5(matrix, i, j+1);
        count += DFS5(matrix, i, j-1);

        return count;
    }

    //TODO Problem Challenge 2
    public static int findDistinctIslandsDFS(int[][] matrix) {
        // TODO: Write your code here
        HashSet<String> set = new HashSet<>();
        boolean[][] isVisited = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1 && !isVisited[i][j]){
                    StringBuilder stringBuilder = new StringBuilder();
                    DFS6(matrix, i, j, set, "O", stringBuilder, isVisited);
                    set.add(stringBuilder.toString());
                }
            }
        }
        return set.size();
    }

    private static void DFS6(int[][] matrix, int i, int j, HashSet<String> set, String direction, StringBuilder stringBuilder, boolean[][] isVisited) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length)
            return;
        if (matrix[i][j] == 0 || isVisited[i][j])
            return;

        isVisited[i][j] = true;
        stringBuilder.append(direction);
        DFS6(matrix, i+1, j, set, "R", stringBuilder, isVisited);
        DFS6(matrix, i-1, j, set, "L", stringBuilder, isVisited);
        DFS6(matrix, i, j+1, set, "U", stringBuilder, isVisited);
        DFS6(matrix, i, j-1, set, "D", stringBuilder, isVisited);
        stringBuilder.append("B");
    }
    //TODO Problem Challenge 3
    public static boolean hasCycle(char[][] matrix) {
        boolean[][] isVisited = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (!isVisited[i][j])//!!!!!!!!!!!!!!!!!!!!!!!注意在DFS之前要判断这个点是否遍历过
                    if (DFS7(matrix, i, j, isVisited, i, j, matrix[i][j]))
                        return true;
            }
        }

        // TODO: Write your code here
        return false;
    }

    private static boolean DFS7(char[][] matrix, int i, int j, boolean[][] isVisited, int prex, int prey, char key) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] != key)
            return false;
        if (isVisited[i][j])
            return true;

        isVisited[i][j] = true;
        boolean isCycle = false;
        if (i+1 != prex)
            isCycle =  isCycle || DFS7(matrix, i+1, j, isVisited, i, j, key);
        if (i-1 != prex)
            isCycle =  isCycle || DFS7(matrix, i-1, j, isVisited, i, j, key);
        if (j+1 != prey)
            isCycle =  isCycle || DFS7(matrix, i, j+1, isVisited, i, j, key);
        if (j-1 != prey)
            isCycle =  isCycle || DFS7(matrix, i, j-1, isVisited, i, j, key);

        return isCycle;
    }

    public static void main(String[] args) {

        System.out.println(Arrays.deepToString(c2_ISLAND.floodFill(
                new int[][] {
                        { 0, 1, 1, 1, 0 },
                        { 0, 0, 0, 1, 1 },
                        { 0, 1, 1, 1, 0 },
                        { 0, 1, 1, 0, 0 },
                        { 0, 0, 0, 0, 0 }
                }, 1, 3, 2)));

        System.out.println(Arrays.deepToString(c2_ISLAND.floodFill(
                new int[][] {
                        { 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0 },
                        { 0, 0, 1, 1, 0 },
                        { 0, 0, 1, 0, 0 },
                        { 0, 0, 1, 0, 0 }
                }, 3, 2, 5)));
    }
}
