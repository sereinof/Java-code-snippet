package person.man.zhouyuqing;

import java.util.HashMap;

public class K_subArray {
    public int subarraySum(int[] nums, int k) {
//维护数组0-i 区间内前缀和为某一值的个数 遍历的时候需要前面某一前缀和的次数而已
        HashMap<Integer,Integer> map = new HashMap();
        int res = 0;
        int sum = 0;
        for(int i =0;i< nums.length;i++){
     sum += nums[i];
     map.put(sum,map.getOrDefault(sum,0)+1);
    int  target = sum - k;
    if(map.containsKey(target)){
        res += map.get(target);
    };
        }
        return res;
    }
}
