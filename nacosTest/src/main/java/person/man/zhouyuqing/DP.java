package person.man.zhouyuqing;

import org.checkerframework.checker.nullness.Opt;

import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

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
                } else if (s1.charAt(i - 1) == s3.charAt(i + j - 1) && s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
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

    public int maxProfit1(int[] prices) {
//又来买股票喽
        int[][] dp = new int[prices.length][5];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];//第一次买入
        dp[0][2] = 0;//第一次卖出
        dp[0][3] = -prices[0];//第二次买入
        dp[0][4] = 0;//第二次卖出
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = Math.max(-prices[i], dp[i - 1][1]);
            dp[i][2] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][2]);
            dp[i][3] = Math.max(dp[i - 1][2] - prices[i], dp[i - 1][3]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        return Math.max(dp[prices.length - 1][2], dp[prices.length - 1][4]);
    }

    public int maxProfit(int k, int[] prices) {
//又来买股票喽
        int[][] dp = new int[prices.length][2 * k + 1];
        for (int i = 1; i <= 2 * k; i += 2) {
            dp[0][i] = -prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= 2 * k; j += 2) {
                dp[i][j] = Math.max(dp[i - 1][j - 1] - prices[i], dp[i - 1][j]);
                dp[i][j + 1] = Math.max(dp[i - 1][j] + prices[i], dp[i - 1][j]);
            }
        }

        OptionalInt res = Arrays.stream(dp[prices.length - 1]).max();
        return res.getAsInt();
    }

    public int trap(int[] height) {
        int[] left = new int[height.length];
        int[] right = new int[height.length];
        left[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }
        right[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            int min = Math.min(left[i], right[i]);
            if (min > height[i]) {
                res += min - height[i];
            }
        }
        return res;
    }

    public String intToRoman(int num) {
        int[] value = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] reps = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < value.length; i++) {
            int count = num / value[i];
            for (int j = 0; j < count; j++) {
                res.append(reps[i]);
            }
            num = num / value[i];
        }
        return res.toString();
    }

    int count = 0;
    boolean flag = false;
    int index1 = 0;
    int index2 = 0;

    public static void main(String[] args) {
          new DP().generateMatrix(3);
    }
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        while (!flag) {
            print(res, '1', n);
            print(res, '2', n);
            print(res, '3', n);
            print(res, '4', n);
        }

        return res;
    }

    public void print(int[][] matrix, char signal, int len) {
        if (flag) {
            return;
        }
        if (signal == '1') {
            while (index2 < len && matrix[index1][index2] == 0) {
                matrix[index1][index2] = ++count;
                index2++;
            }
            index2--;
            index1++;

        } else if (signal == '2') {
            while (index1 < len && matrix[index1][index2] == 0) {
                matrix[index1][index2] = ++count;
                index1++;
            }
            index1--;
            index2--;
        } else if (signal == '3') {
            while (index2 >= 0 && matrix[index1][index2] == 0) {
                matrix[index1][index2] = ++count;
                index2--;
            }
            index2++;
            index1--;
        } else {
            while (index1 >= 0 && matrix[index1][index2] == 0) {
                matrix[index1][index2] = ++count;
                index1--;
            }
            index1++;
            index2++;
        }
        if (count == len * len) {
            flag = true;
        }
    }
}
