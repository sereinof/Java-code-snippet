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

    public static void main(String[] args) {
        new DP().isInterleave("aabc","abad","aabadabc");
    }
    public boolean isInterleave(String s1, String s2, String s3) {

        int size1 = s1.length();
        int size2 = s2.length();
        int size3 = s3.length();
        if (size1 + size2 != size3) {
            return false;
        }
        boolean[][] dp = new boolean[size1 + 1][size2 + 1];
        dp[0][0] = true;
        for (int i = 1; i <= size1; i++) {
            dp[i][0] = s1.substring(0, i).equals(s3.substring(0, i));
        }
        for (int j = 1; j <= size2; j++) {
            dp[0][j] = s2.substring(0, j).equals(s3.substring(0, j));
        }
        for (int i = 1; i <= size1; i++) {
            for (int j = 1; j <= size2; j++) {
                if (s1.charAt(i - 1) != s3.charAt(i + j - 1) && s2.charAt(j - 1) != s3.charAt(i + j - 1)) {
                    dp[i][j] = false;
                } else if (s1.charAt(i - 1) == s3.charAt(i + j - 1) && s2.charAt(j - 1) == s3.charAt(i + j - 1)){
                    dp[i][j] = dp[i - 1][j]||dp[i][j - 1];
                } else if (s1.charAt(i - 1) == s3.charAt(i + j - 1)) {
                    dp[i][j] = dp[i - 1][j];
                } else if (s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    System.out.println("iftere?");
                }
            }
        }
        return dp[size1][size2];
    }

    public int maxProfit(int[] prices) {

    }

}
