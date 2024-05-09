package person.man.zhou;

import org.checkerframework.checker.units.qual.A;
import person.man.zhouyuqing.ListNode;

import java.util.*;

public class newLearning {


    public int[] findErrorNums(int[] nums) {
        int xOry = 0;//存储多的那个数和小的那个数的异或结果
        int n = 1;
        for (int i = 0; i < nums.length; i++) {
            xOry ^= nums[i];
            xOry ^= n;
            n++;
        }
        int lowBit = xOry & (-xOry);
        int nums1 = 0;
        int nums2 = 0;
        for (int num : nums) {
            if ((num & lowBit) == 1) {
                nums1 ^= num;
            } else {
                nums2 ^= num;
            }
        }
        for (int i = 1; i < nums.length + 1; i++) {
            System.out.println(i & lowBit);
            if ((i & lowBit) == 1) {
                nums1 ^= i;
            } else {
                nums2 ^= i;
            }
        }
        for (int num : nums) {
            if (num == nums1) {
                return new int[]{nums1, nums2};
            }
        }
        return new int[]{nums2, nums1};
    }

    public void deleteNode(ListNode node) {
    }

    public List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> stuff = new ArrayList<>();
        dfs(stuff, nums, 0);

        return res;
    }

    public void dfs(List<Integer> stuff, int[] nums, int index) {
        if (index == nums.length) {
            List<Integer> item = new ArrayList<>(stuff.size());
            stuff.stream().forEach((a) -> {
                item.add(a);
            });
            res.add(item);
        }
        dfs(stuff, nums, index + 1);
        stuff.add(nums[index]);
        dfs(stuff, nums, index + 1);
        stuff.remove(nums[index]);
    }


    public static void main(String[] args) {
        new newLearning().generateParenthesis(3);
    }

    public List<String> generateParenthesis(int n) {
        List<List<String>> dp = new ArrayList<>(n + 1);
        ArrayList dp1 = new ArrayList();
        dp1.add("()");
        dp.add(null);
        dp.add(dp1);
        ArrayList dp2 = new ArrayList();
        dp1.add("()()");
        dp1.add("(())");
        dp.add(dp2);
        for (int i = 3; i <=n; i++) {
            List<String> thisTime = new ArrayList<String>();
            //这里要做一个遍历
            for (int p = 1; p <= i - 1; p++) {
                int q = i - 1 - p;
                for (String a : dp.get(p)) {
                    for (String b : dp.get(q)) {
                        StringBuilder val = new StringBuilder();
                        val.append("(");
                        val.append(a);
                        val.append(")");
                        val.append(b);
                        thisTime.add(val.toString());
                    }
                }
            }
            dp.add(thisTime);
        }

        return dp.get(n);
    }
}
