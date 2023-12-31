import java.util.ArrayList;
import java.util.List;

public class C17_01Knapsack {
    //TODO 0/1 Knapsack (medium)
    //Chanllenge 没有完成
    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
        // TODO: Write your code here
        //Integer[][] dp = new Integer[profits.length][capacity+1];
        //return  this.knapsackRecursive(dp, profits, weights, capacity,0);
        if (capacity <= 0 || profits.length == 0 || weights.length == 0 || profits.length != weights.length) return 0;
        //i行索引，考虑物品数量，c列索引，考虑背包当前容量
        int[][] dp = new int[profits.length][capacity+1];
        for (int i = 0; i < weights.length; i++) {
            dp[i][0] = 0;
        }

        for (int c = 0; c < capacity+1; c++) {

            if (weights[0] <= c)// <= c not <= capacity, weights[0]<=c而不是weights[c]<=c
                dp[0][c] = profits[0];
            /*
            * if (weights[c] <= c)// <= c not <= capacity
                dp[0][c] = profits[c];c对应的是背包capacity，profits对应每个物品的利润，物品数量和背包容量无关，是0不是c
            * */
        }
        for (int i = 1; i < weights.length; i++) {
            for (int c = 1; c < capacity+1; c++) {
                int profit1 = 0;
                if (weights[i] <= c){
                    profit1 = profits[i] + dp[i-1][c - weights[i]];//错了 不是dp[i-1][capacity - j]而是[j - weights[i]]
                }
                int profit2 = dp[i-1][c];

                dp[i][c] = Math.max(profit1, profit2);
            }
        }

        return dp[profits.length-1][capacity];
    }

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

    //TODO Equal Subset Sum Partition (medium)
    //method 1 brute force
    public boolean canPartition(int[] num) {
        //TODO: Write - Your - Code
        int sum = 0;
        for (int i = 0; i < num.length; i++) {
            sum += num[i];
        }
        if (sum % 2 != 0)
            return false;


        return this.canPartitionRecursive(num, sum/2, 0);
    }

    public boolean canPartitionRecursive(int[] num, int sum, int currentIndex){
        if (sum == 0) return true;
        if (num.length ==0 || currentIndex >= num.length) return  false;

        if (num[currentIndex] <= sum){
            if (canPartitionRecursive(num, sum - num[currentIndex], currentIndex + 1)){
                return true;
            }
        }
        return canPartitionRecursive(num, sum, currentIndex+1);
    }
}
