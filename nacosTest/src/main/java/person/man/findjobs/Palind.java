package person.man.findjobs;

import java.util.*;

public class Palind {
    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        boolean res = new Palind().isPalindrome(s);
        System.out.println(res);
    }

    public boolean isPalindrome(String s) {
        StringBuilder str = new StringBuilder(s);
        int ptr = 0;
        int ptr1 = 0;
        while (ptr1 != s.length()) {
            char ch = str.charAt(ptr1);
            if ((ch <= 'z' & ch >= 'a') || (ch >= '0' && ch <= '9')) {
                str.setCharAt(ptr, ch);
                ptr++;
            } else if (ch <= 'Z' & ch >= 'A') {
                str.setCharAt(ptr, (char) (ch + 32));
                ptr++;
            }
            ptr1++;
        }
        ptr--;
        int index = 0;
        while (ptr > index) {
            if (str.charAt(index) == str.charAt(ptr)) {
                ptr--;
                index++;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) {
            return true;
        }
        if (t.length() == 0) {
            return false;
        }
        int ptr = 0;
        int ptr1 = 0;
        while (ptr1 < t.length()) {
            if (s.charAt(ptr) == t.charAt(ptr1)) {
                ptr++;
                ptr1++;
            } else {
                ptr1++;
            }
            if (ptr == s.length()) {
                return true;
            }
        }
        return false;
    }

    public int[] twoSum(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;
        while (l != r) {
            if (numbers[l] + numbers[r] == target) {
                return new int[]{l + 1, r + 1};
            } else if (numbers[l] + numbers[r] > target) {
                r--;
            } else {
                l++;
            }
        }
        return new int[]{-1, -1};
    }

    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int max = 0;
        while (l != r) {
            max = Math.max(max, Math.min(height[l], height[r]) * (r - l));
            if (height[l] > height[r]) {
                r--;
            } else {
                l++;
            }
        }
        return max;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Set<Integer> map = new HashSet<>();
        Set<List<Integer>> res = new HashSet<>();
        for (int i = 0; i <= nums.length - 3; i++) {
            int remain = 0 - nums[i];
            map.clear();
// i+1 -- nums.length-3
            for (int j = i + 1; j < nums.length; j++) {
                int need = remain - nums[j];
                if (map.contains(need)) {
                    List<Integer> item = new ArrayList<>();
                    item.add(nums[i]);
                    item.add(need);
                    item.add(nums[j]);
                    res.add(item);
                } else {
                    map.add(nums[j]);
                }


            }
        }

        return new ArrayList<>(res);
    }


    public List<List<Integer>> threeSumII(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int l = 0;
        for (int i = 0; i < nums.length - 3; i++) {
            if (nums[i] > 0) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;// 去重
            }
            //i+1 -
            int L = i + 1;
            int R = nums.length - 1;
            int target = -nums[i];
            while (L != R) {
                int sum = nums[L] + nums[R];
                if (sum == target) {
                    List<Integer> item = new ArrayList<>();
                    item.add(nums[i]);
                    item.add(nums[L]);
                    item.add(nums[R]);
                    res.add(item);
                    //去重了
                    while (L <R && nums[L] == nums[L + 1]) {
                        L++;
                    }

                    while (R > L && nums[R] == nums[R - 1]) {
                        R--;
                    }
                }
                if (sum > target) {
                    R--;
                }
                if (sum < target) {
                    L++;
                }
            }

        }

        return null;
    }
}
