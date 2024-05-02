package person.man.zhouyuqing;

public class lunzhuanArray{

        public void rotate_bak(int[] nums, int k) {
            int len = nums.length;
            int point = k % len;
            int temp = nums[len - 1];
            //这个题目看起来没这么简单！！
            for (int i = 0; i < point; i++) {
                temp = nums[len - 1];
                int j = len - 1;
                while (j > 0) {
                    nums[j] = nums[j - 1];
                    j--;
                }
                nums[0] = temp;
            }
        }

        public void rotate(int[] nums, int k) {
            int len = nums.length;
            int point = k % len;
            int[] tmp = new int[len - point];//是前半部分的数组


        }
}
