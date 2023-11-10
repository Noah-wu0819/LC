import java.util.ArrayList;
import java.util.List;

public class C17_01Knapsack {
    //TODO 0/1 Knapsack (medium)
    //Chanllenge 没有完成
    

    private int knapsackRecursive(Integer[][] dp, int[] profits, int[] weights, int capacity, int currentIndex) {
        if (capacity <= 0 || currentIndex >= profits.length) return 0;
        if (dp[currentIndex][capacity] != null) return dp[currentIndex][capacity];
        //使用 Integer[][]排除null的情况，int[][]不能判断null
        int profit1 = 0;
        //注意 >=而不是>
        if (capacity >= weights[currentIndex]){
            profit1 = profits[currentIndex] + knapsackRecursive(dp, profits, weights, capacity - weights[currentIndex], currentIndex + 1);
        }
        //这里是去掉当前index的物品，所以不能capacity - weights[currentIndex]，只留capacity
        int profit2 = knapsackRecursive(dp, profits, weights, capacity, currentIndex + 1);
        dp[currentIndex][capacity] = Math.max(profit2, profit1);
        return dp[currentIndex][capacity];
    }

    private void printSelectedElements(int[][] dp, int[] weights, int[] profits, int capacity){
        List<Integer> elementsWeights = new ArrayList<>();
        int totalProfit = dp[weights.length-1][capacity];
        for (int i = weights.length-1 ;i > 0; i--) {
            if (totalProfit != dp[i-1][capacity]){
                elementsWeights.add(weights[i]);
                totalProfit -= profits[i];
                capacity -= weights[i];
            }
        }

        if (totalProfit != 0)
            elementsWeights.add(weights[0] );
    }
}
