package person.man.findjobs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DP {

    public int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        int prepre = 1;
        int pre = 2;
        for (int i = 3; i <= n; i++) {
            int res = pre + prepre;
            prepre = pre;
            pre = res;
        }
        return pre;
    }

    public static void main(String[] args) {
        new DP().rob(new int[]{1, 2, 3, 1});
    }

    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int preRob = nums[0];
        int preNotRob = 0;
        int max = preRob;
        for (int i = 1; i < nums.length; i++) {
            int rob = preNotRob + nums[i];
            int notRob = Math.max(preRob, preNotRob);
            max = Math.max(rob, Math.max(max, notRob));
            preRob = rob;
            preNotRob = notRob;
        }
        return max;

    }

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        Set<String> set = new HashSet<>();
        for (String n : wordDict) {
            set.add(n);
        }
        dp[0] = true;
        for (int i = 1; i < dp.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                String cur = s.substring(j, i);
                if (set.contains(cur) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {

                int indx = i - coins[i];
                if (indx >= 0 && dp[indx] != -1) {
                    min = Math.min(min, dp[indx] + 1);
                }
            }
            dp[i] = min == Integer.MAX_VALUE ? -1 : min;
        }
        return dp[amount];
    }

    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length + 1];
        tails[1] = nums[0];
        int left = 1;
        int right = 1;
        for (int i = 1; i < nums.length; i++) {
            int l = left - 1;
            int r = right + 1;
            while (l + 1 != r) {
                int mid = l + (r - l) / 2;
                if (mid >= nums[i]) {
                    r = mid;
                } else {
                    l = mid;
                }
            }
            if (l == right) {
                right++;
                tails[right] = nums[i];
            } else {
                tails[r] = nums[i];
            }
            ;
        }
        return right;
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 1) return triangle.get(0).get(0);
        List<Integer> pre = triangle.get(0);
        for (int i = 1; i < triangle.size(); i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (j == 0) {
                    triangle.get(i).set(j, pre.get(0) + triangle.get(i).get(j));
                } else if (j == triangle.get(i).size() - 1) {
                    triangle.get(i).set(j, pre.get(pre.size() - 1) + triangle.get(i).get(j));
                } else {
                    int a = pre.get(j);
                    int b = pre.get(j - 1);
                    triangle.get(i).set(j, Math.min(a, b) + triangle.get(i).get(j));
                }

            }
            pre = triangle.get(i);
        }
        int res = Integer.MAX_VALUE;
        List<Integer> lastRow = triangle.get(triangle.size() - 1);
        for (int i = 0; i < lastRow.size(); i++) {
            Integer val = lastRow.get(i);
            if (val < res) {
                res = val;
            }
        }
        return res;
    }

    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 1; i < rows; i++) {
            grid[i][0] = grid[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < cols; i++) {
            grid[0][i] = grid[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                grid[i][j] = grid[i][j] + Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[rows - 1][cols - 1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;
        int[][] dp = new int[rows][cols];
        for (int i = 1; i < rows; i++) {
            if (obstacleGrid[i][0] == 1) break;
            dp[i][0] = 1;
        }
        for (int i = 1; i < cols; i++) {
            if (obstacleGrid[0][i] == 1) break;
            dp[0][i] = 1;
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                if (obstacleGrid[i - 1][j] != 1 && obstacleGrid[i][j - 1] != 1) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                } else if (obstacleGrid[i - 1][j] == 1 && obstacleGrid[i][j - 1] == 1) {
                    dp[i][j] = 0;
                } else if (obstacleGrid[i - 1][j] == 1) {
                    dp[i][j] = dp[i][j - 1];
                } else if (obstacleGrid[i][j - 1] == 1) {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[rows - 1][cols - 1];
    }

    public String longestPalindrome(String s) {
        StringBuilder ss = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            ss.append('#');
            ss.append(s.charAt(i));
        }
        ss.append('#');
        int[] info = new int[s.length()];
        int C = 0;
        int R = 0;//这俩变量表示大蘑菇
        for (int i = 1; i < s.length(); i++) {
            if (R == i) {

            } else if (R > i) {//分情况这里

            } else if (R < i) {
                int l = i - 1;
                int r = i + 1;
                while (i >= 0 && r <= s.length()) {


                }
            }


            int l = i + 1;

        }


    }
}
