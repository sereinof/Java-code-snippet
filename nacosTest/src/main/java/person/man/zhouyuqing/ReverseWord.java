package person.man.zhouyuqing;

import java.util.HashMap;

public class ReverseWord {


    public String reverseWords(String s) {
        String res = "";
        String tmp = "";
        int l = 0;
        int r = 0;
        while (r < s.length()) {
            while (l < s.length() && s.charAt(l) == ' ') {
                l++;
                r++;
            }
            while (r < s.length() && s.charAt(r) != ' ') {
                r++;
            }
            if (r > l) {
                tmp = s.substring(l, r);
            }

            res = tmp + " " + res;
            l = r;
        }
        return res.substring(0, res.length() - 1);
    }

    public int[] twoSum(int[] numbers, int target) {
        int len = numbers.length;
        int l = 0;
        int r = len - 1;
        while (l < r) {
            if (numbers[l] + numbers[r] == target) {
                return new int[]{l, r};
            } else if (numbers[l] + numbers[r] < target) {
                l++;
            } else {
                r--;
            }
        }
        return new int[]{-1, -1};
    }


    public static void main(String[] args) {
        int res = new ReverseWord().minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3});
        System.out.println(res);

    }

    public int minSubArrayLen1(int target, int[] nums) {
        int len = nums.length;
        int l = 0;
        int r = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        while (l <= r && r < len) {
            boolean flag = false;
            while (r < len && sum < target) {
                sum += nums[r];
                r++;
            }
            if (r == len) {
                if (sum >= target) {
                    res = Math.min(res, r - l);
                }
                r = l;
                l++;
                r++;
                sum = 0;
                continue;
            }
            while (sum >= target) {
                sum -= nums[l];
                flag = true;
                l++;
            }
            if (flag == true) {
                res = Math.min(res, r - l + 1);
            } else {
                res = Math.min(res, r - l);
                l++;
            }

        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;//还是用闭区间来做吧
        int l = 0;
        int r = 0;
        int sum = nums[0];
        int res = Integer.MAX_VALUE;
        while (l <= r && r < len) {
            while (r < len && sum < target) {
                r++;
                sum += nums[r];
            }
            if (r==len){
                if (sum >= target) {
                    res = Math.min(res, r - l+1);
                    r = l;
                    l++;
                    r++;
                    sum = 0;
                    continue;
                }else {
                    break;
                }

            }

        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
