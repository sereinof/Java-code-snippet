package person.man.zhouyuqing;

import java.util.ArrayList;
import java.util.HashMap;
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
                count += n & 1 >> i + 1;
            }
            if (count == 1) {
                res += 1 >> i + 1;
            }
        }
        return res;
    }

    public int[] singleNumber(int[] nums) {
        int yihuoRes = 0;//存储a,b 异或的结果
        for (int i = 0; i < nums.length; i++) {
            yihuoRes ^= nums[i];
        }
        //得到异或的结果之后
        //那么获取到这个异或结果上随便以为位1 的比特位
        int bit_position = yihuoRes == Integer.MIN_VALUE ? yihuoRes : yihuoRes & (-yihuoRes);
        int a = 0;
        int b = 0;
        for (int n : nums) {
            if ((n & bit_position) == 0) {
                a ^= n;
            } else {
                b ^= n;
            }
        }
        return new int[]{a, b};
    }

    public static void main(String[] args) {
        new HeapQ().rangeBitwiseAnd(1, 10);
    }

    public int rangeBitwiseAnd(int m, int n) {
        int shift = 0;
        // 找到公共前缀
        while (m < n) {
            m >>= 1;
            n >>= 1;
            ++shift;
        }
        return m << shift;
    }

    public double myPow(double x, int n1) {
        double sum = x;
        double asn = 1;
        int n = n1;
        boolean flag = false;
        if (n < 0) {
            n = -n;
            flag = true;
        }
        while (n != 0) {
            if ((n & 1) == 1) {
                asn *= sum;
            }
            sum *= sum;
            n = n >> 1;
        }
        if (flag) {
            return 1 / asn;
        } else {
            return asn;
        }

    }

    public int minimumTotal(List<List<Integer>> triangle) {
        //定义dp函数 的参数位 到达该行该列的最小路径是多少 结果取dp数组中最后一行的的最小值即可
        int maxLen = triangle.get(triangle.size() - 1).size();
        int[][] dp = new int[triangle.size()][maxLen];
        dp[0][0] = triangle.get(0).get(0);
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < triangle.size(); i++) {//从第二层开始遍历
            for (int j = 0; j < triangle.get(i).size(); j++) {
                List<Integer> lastRow = triangle.get(i - 1);
                int value = triangle.get(i).get(j);
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + value;
                } else if (j == lastRow.size()) {
                    dp[i][j] = dp[i - 1][j - 1] + value;
                } else {
                    dp[i][j] = value + Math.min(dp[i - 1][j], dp[i - 1][j - 1]);
                }
                if (i == triangle.size() - 1) {
                    res = Math.min(dp[i][j], res);
                }
            }
        }
        return res==Integer.MAX_VALUE?dp[0][0]:res;
    }

}
