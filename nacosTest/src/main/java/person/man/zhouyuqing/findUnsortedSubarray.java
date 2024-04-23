package person.man.zhouyuqing;

public class findUnsortedSubarray {

 public int findUnsortedSubarray(int[] nums) {
    int max = nums[0];
    int len = nums.length;
    int min = nums[len-1];
    int beging = 0;
    int end = len-1;
    for(int i=0;i<nums.length;i++){
        if(max <nums[i]){
            max =nums[i];
        }else{
            end = i;
        }

        if(nums[len-1-i]<min){
            min = nums[i];
        }else{
            beging = i;
        }
    }
    return end-beging+1;
 }
}
