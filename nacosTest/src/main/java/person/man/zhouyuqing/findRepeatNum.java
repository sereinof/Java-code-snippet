package person.man.zhouyuqing;

public class findRepeatNum {

    public int findDuplicate(int[] nums) {
        //快拍解法
        int l =1;
        int r = nums.length -1;

        int i = 0;

        while(i<r){
            int count = 0;
            int mid = (r-l)>>1;
            for(int j : nums){
                if(nums[j]<=mid){
                    count++;
                }
            }
            if(count>mid-1){//
                l= mid;
            }else{
                r= mid-1;
            }

        }
return 6;
    }
}
