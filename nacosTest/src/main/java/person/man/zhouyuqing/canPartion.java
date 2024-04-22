package person.man.zhouyuqing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class canPartion {
    public static void main(String[] args) {
        LinkedList kist  = new LinkedList();
        ArrayList list = new ArrayList();
        list.get(0);
        kist.get(3);


    }
    public boolean canPartition(int[] nums) {
        int target = 0;
  for(int i=0;i< nums.length;i++){
       target+=nums[i];
  }
  if(target/2!=0){
      return false;
  }
  target = target/2;
  //用回溯吧
 boolean[][] dp = new boolean[nums.length-1][target];//表示0-i个序列里  target =target 能否成立
        dp[0][0] = false;
   for(int i=1;i<nums.length;i++){
      for(int j = 1;j<target;j++){

          if(nums[i]==target){
              dp[i][j]=true;

          }else if(nums[i]<target){
              dp[i][j] = dp[i-1][j-nums[i]];
          }
           if(dp[i-1][j]){
               dp[i][j] = true;
           }

      }
   }
        return  dp[nums.length-1][target];
    }
    public int pathSum(TreeNode root, int targetSum) {
        if(root ==null){
            return 0;
        }
        int res= rootSum(root,targetSum);
        res += pathSum(root.left,targetSum);
        res+= pathSum(root.right,targetSum);
        return res;
    }
    public int rootSum(TreeNode root, long targetSum){
        int ret = 0;
        if(root ==null){
            return 0;

        }
        int val = root.val;
        if(val == targetSum){
            ret++;
        }
        ret += rootSum(root.left,targetSum-val);
        ret += rootSum(root.right,targetSum-val);
        return ret;
    }

}
