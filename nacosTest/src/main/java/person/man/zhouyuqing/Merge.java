package person.man.zhouyuqing;

public class Merge {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int l = 0;
        int i = 0;
  while (i<m+n){
      int tmp = nums1[i];
      if(l>=n){
          i++;
          continue;
      }else if(nums1[i]<nums2[l]){
          i++;
          continue;

      }else if(nums1[i]>=nums2[l]){

      }
  }
    }
}
