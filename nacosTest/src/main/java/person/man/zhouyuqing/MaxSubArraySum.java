package person.man.zhouyuqing;

public class MaxSubArraySum {
    public int maxSubarraySumCircular(int[] nums) {
        if(nums.length==1){
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int maxSubarry = dp[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum+=nums[i];
            if (dp[i - 1] <= 0) {
                dp[i] = nums[i];
                maxSubarry =Math.max(dp[i],maxSubarry);
            } else {
                dp[i] = dp[i - 1] + nums[i];
                maxSubarry =Math.max(dp[i],maxSubarry);
            }
        }

        //求的普通情况的最大和
        //接下来求环里面的
        int[] dp1 = new int[nums.length];
        dp1[0] = nums[0];
        int minSubArray =dp1[0];
        for (int i = 1; i < nums.length-1; i++) {
            if (dp1[i - 1] >= 0) {
                dp1[i] = nums[i];
                minSubArray = Math.min(dp1[i],minSubArray);
            } else {
                dp1[i] = dp1[i - 1] + nums[i];
                minSubArray = Math.min(dp1[i],minSubArray);
            }
        }
        int[] dp2 =new int[nums.length];
        dp2[1] =nums[1];
        for (int i = 2; i < nums.length; i++) {
            if (dp2[i - 1] >= 0) {
                dp2[i] = nums[i];
                minSubArray = Math.min(dp2[i],minSubArray);
            } else {
                dp2[i] = dp2[i - 1] + nums[i];
                minSubArray = Math.min(dp2[i],minSubArray);
            }
        }
        return Math.max(maxSubarry,sum-minSubArray);
    }
}
