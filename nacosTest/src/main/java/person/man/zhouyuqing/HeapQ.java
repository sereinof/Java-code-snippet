package person.man.zhouyuqing;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class HeapQ {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return a[2] - b[2];
        });
        int n = nums1.length;
        int n2 = nums2.length;
        for (int i = 0; i < Math.min(n, k); i++) {
            queue.add(new int[]{i, 0, nums1[i] + nums2[0]});
        }
        int kk = 2;
        List res = new ArrayList<>();
        while (res.size() < k) {
            int[] now = (int[]) queue.poll();//弹出堆顶
            res.add(now);
            if (now[1] + 1 < n2) {
                queue.add(new int[]{now[0], now[1] + 1, nums1[now[0]] + nums2[now[1] + 1]});
            }


        }
        return res;
    }

    public int singleNumber1(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int n : nums) {
            count+=   n&1>>i+1;
            }
            if(count==1){
                res += 1>>i+1;
            }
        }
        return res;
    }
}
