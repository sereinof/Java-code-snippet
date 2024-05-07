package person.man.zhouyuqing;

public class DP {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int x = obstacleGrid.length;
        int y = obstacleGrid[0].length;
        if (x == 0 || y == 0) {
            return 0;
        }
        int[][] dp = new int[x][y];
        dp[0][0] = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                //i-1,j   i j-1
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                if (i - 1 >= 0 && j - 1 >= 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                } else if (i - 1 < 0 && j - 1 >= 0) {
                    dp[i][j] = dp[i][j - 1];
                } else if (i - 1 >= 0 && j - 1 < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {

                }
            }
        }
        return dp[x - 1][y - 1];
    }
}
