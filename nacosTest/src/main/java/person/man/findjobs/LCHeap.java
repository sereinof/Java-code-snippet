package person.man.findjobs;

import java.util.Random;

public class LCHeap {
    public static void main(String[] args) {
        LCHeap lcHeap = new LCHeap();
        lcHeap.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4);
    }

    public int findKthLargest(int[] nums, int k) {
        if (nums.length == 0) return 0;
        return findK(nums, k, 0, nums.length - 1);
    }

    private int findK(int[] nums, int k, int l, int r) {
        if (l == r) return nums[l];
        int cadiate;
        int ptrL = l;
        int ptrR = r;
        int i;
        int j;
        Random random = new Random();
        while (true) {
            if (ptrL == ptrR && k == 1) return nums[ptrL];
            i = ptrL;
            j = ptrR;
            int pivotIdx = l + random.nextInt(r - l + 1);
            int atmp = nums[pivotIdx];
            nums[pivotIdx] = nums[j];
            nums[j] = atmp;
            cadiate = nums[j];
            int sortInde = i;
            for (int m = i; m <= j; m++) {
                if (nums[m] > cadiate) {
                    int tmp = nums[sortInde];
                    nums[sortInde] = nums[m];
                    nums[m] = tmp;
                    sortInde++;
                }
            }
            int tmp = nums[j];
            nums[j] = nums[sortInde];
            nums[sortInde] = tmp;

            if (sortInde - ptrL + 1 == k) {
                return nums[sortInde];
            } else if (sortInde - ptrL + 1 > k) {
                ptrR = sortInde - 1;
            } else {
                k = k - (sortInde - ptrL + 1);
                ptrL = sortInde + 1;

            }
        }
    }
}
