package person.man.zhouyuqing;

import java.util.HashSet;

public class sequence {
    public static void main(String[] args) {
  new sequence().longestConsecutive(new int[]{100,4,200,1,3,2});
    }

    public int longestConsecutive(int[] nums) {
        HashSet set = new HashSet();
        int res = 0;
        for (int i : nums) {
            set.add(i);
        }
        for (int i =0;i<nums.length;i++){
            if(set.contains(nums[i]-1)){
                continue;
            }
            int current = nums[i];
            int statics = 1;
            while(set.contains(current+1)){
                 statics++;
                 current++;
            }

            res  = statics>res?statics:res;
        }
        return res;
    }
}
