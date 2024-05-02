package person.man.zhouyuqing;

public class Sqrt {
    public static void main(String[] args) {
       // int res = new Sqrt().mySqrt(1);
        //System.out.println(res);
        new Sqrt().removeDuplicates(new int[]{1,1,1,2,2,3});
    }

    public int mySqrt(int x) {
        int l = 0;
        int r = x;
        int res = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (mid * mid == x) {
                return mid;
            } else if (mid * mid > x) {
                r = mid - 1;
            } else {
                res = mid;
                l = mid + 1;
            }
        }
        return res;
    }

    int len = 0;

    public int removeDuplicates(int[] nums) {
        int i = 0;
        int j = 0;
        len = nums.length;
        while (j < len) {
            while ( j<len&& nums[i] == nums[j]) {
                j++;
            }
          int num =   remove(nums, i, j);//处理区间I ，j之间的重复数字，
            i = j;
            i-=num;
            j-=num;
        }
        return len;

    }

    public int remove(int[] nums, int index1, int index2) {
        if (index2 - index1 <= 2) {
            return 0;//超出2才处理
        }
        int need = index2 - index1 - 2;
        for (int i = index2; i < len; i++) {
            nums[i - need] = nums[i];
        }
        len= len-need;
        return  need;
    }
}
