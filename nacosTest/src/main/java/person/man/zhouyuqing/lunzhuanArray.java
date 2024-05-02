package person.man.zhouyuqing;

public class lunzhuanArray {

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
        //  int[] tmp = new int[len - point];//是前半部分的数组
        revers(nums, 0, len - 1);
        revers(nums, 0, point - 1);
        revers(nums, point, len - 1);

    }

    public void revers(int[] nums, int index1, int index2) {
        if (index1 >= index2) {
            return;
        }
        while (index1 <= index2) {
            int tmp = nums[index1];
            nums[index1] = nums[index2];
            nums[index2] = tmp;
            index1++;
            index2--;
        }
    }

    public int maxProfit(int[] prices) {
        int ase = 0;
        for (int i = 0; i < prices.length - 1; i++) {

            if (prices[i + 1] > prices[i]) {
                ase += prices[i - 1] - prices[i];
            }
        }
        return ase;
    }
}
