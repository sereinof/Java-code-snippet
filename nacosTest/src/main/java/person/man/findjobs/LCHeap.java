package person.man.findjobs;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
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

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<int[]> heap1 = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (int i = 0; i < profits.length; i++) {
            heap1.add(new int[]{profits[i], capital[i]});
        }
        PriorityQueue<int[]> big = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < k; i++) {
            while (!heap1.isEmpty() && heap1.peek()[1] <= w) {
                big.add(heap1.poll());
            }
            if (!big.isEmpty()) {
                w += big.poll()[0];
            }
        }
        return w;
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int i = 0; i < nums1.length; i++) {
            queue.add(new int[]{i, 0, nums1[i] + nums2[0]});
        }
        for (int i = 0; i < k; i++) {
            int[] cur = queue.poll();
            List<Integer> item = new ArrayList<>();
            item.add(nums1[cur[0]]);
            item.add(nums2[cur[1]]);
            res.add(item);
            if ((cur[1] + 1) < nums2.length) {
                queue.add(new int[]{cur[0], cur[1] + 1, nums1[cur[0]] + nums2[cur[1] + 1]});
            }
        }
        return res;
    }

    public int kthSmallest(int[][] matrix, int k) {
        return 0;
    }
}
