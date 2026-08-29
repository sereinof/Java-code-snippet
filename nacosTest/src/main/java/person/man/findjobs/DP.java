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
            };
        }
        return right;
    }

    public int minimumTotal(List<List<Integer>> triangle) {

    }
}
