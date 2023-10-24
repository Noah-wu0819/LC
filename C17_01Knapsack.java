public class C17_01Knapsack {
    //TODO 0/1 Knapsack (medium)
    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
        // TODO: Write your code here
        Integer[][] dp = new Integer[profits.length][capacity+1];
        return  this.knapsackRecursive(dp, profits, weights, capacity,0);
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
}
