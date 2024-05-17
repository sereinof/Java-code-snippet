package person.man.zhou;


import java.util.*;
import java.util.stream.Stream;

public class May_10 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int has = 0;
        ListNode res = new ListNode(-1);
        ListNode res1 = res;
        while (l1 != null || l2 != null) {
            int sum = l1 == null ? 0 : l1.val + (l2 == null ? 0 : l2.val) + has;
            has = sum / 10;
            ListNode newly = new ListNode(sum % 10);
            res.next = newly;
            res = res.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (has == 1) {
            ListNode new1 = new ListNode(1);
            res.next = new1;
            res = res.next;
        }


        return res1.next;
    }

    public static void main(String[] args) {
        //
        //lengthOfLongestSubstring("abcabcff");
        //  int res = minEatingSpeed(new int[]{3, 6, 7, 11}, 8);
        convert("PAYPALISHIRING", 3);
        System.out.println("nihao");

    }

    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 2; i < p.length() + 1; i += 2) {
            dp[0][i] = dp[0][i - 2] && p.charAt(i - 1) == '*';
        }
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < p.length() + 1; j++) {
                if (p.charAt(i - 1) == '*') {
                    dp[i][j] =
                } else {
                    dp[i][j] = dp[i - 1][j - 1] && (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1));
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public int romanToInt(String s) {
        int res = 0;
        HashMap<String, Integer> map = new HashMap();
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        int i = 0;
        while (i < s.length()) {
            if (map.containsKey(s.substring(i, i + 2)) && i <= s.length() - 2) {
                res += map.get(s.substring(i, i + 2));
                i++;
                i++;
            } else {
                res += map.get(s.substring(i, i + 1));
                i++;
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum(int[] nums) {
//排序 对于剩下的做两数之和处理；
        List res = new ArrayList<>();
        Arrays.sort(nums);
        if (nums.length == 0) {
            return res;
        }
        if (nums[0] > 0) {
            return res;
        }

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;//去除nums[i]
            int l = i + 1;
            int r = nums.length - 1;
            int target = -nums[i];
            while (l < r) {
                if (nums[l] + nums[r] > target) {
                    r--;
                } else if (nums[l] + nums[r] < target) {
                    l++;
                } else {
                    //处理重复值
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    res.add(new int[]{nums[i], nums[l], nums[r]});
                    r--;
                    l++;
                }
            }
        }
        return res;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) {
            return strs[0];
        }
        String longesCommonprefix = strs[0];
        for (int i = 0; i < strs.length; i++) {
            int l = 0;
            while (l < longesCommonprefix.length() && l < strs[i].length()) {
                if (longesCommonprefix.charAt(l) == strs[i].charAt(l)) {
                    l++;
                } else {
                    break;
                }
            }
            longesCommonprefix = longesCommonprefix.substring(0, l);
        }
        return longesCommonprefix;
    }

    public int myAtoi(String s) {
        int res = 0;//先去除空格 然后读入符号位  然后读入数字 用double来看是否溢出了 这坑比题目
        int l = 0;
        boolean signed = true;
        while (s.charAt(l) == ' ') {
            l++;
        }
        if (s.charAt(l) == '-' || s.charAt(l) == '+') {
            if (s.charAt(l) == '-') {
                signed = false;
            }
            ;
            l++;
        }
        while (l < s.length() && Character.isDigit(s.charAt(l))) {
            res = res * 10 + s.charAt(l) - '0';
            if (signed) {
                if (res > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
            } else {
                if (-res < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
            }
            l++;
        }
        return signed ? res : -res;
    }

    public int reverse(int x) {
        boolean negtive = x < 0 ? true : false;
        int res = 0;
        while (x != 0) {
            if (negtive) {
                if (res < Integer.MIN_VALUE / 10) {
                    return 0;
                }
                res = res * 10 + x % 10;
                x /= 10;
            } else {
                if (res > Integer.MAX_VALUE / 10) {
                    return 0;
                }
                res = res * 10 + x % 10;
                x /= 10;
            }
        }
        return res;
    }

    public static String convert(String s, int numRows) {
        StringBuilder[] tmp = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            tmp[i] = new StringBuilder();
        }
        int i = 1;
        tmp[0].append(s.charAt(0));
        boolean flag = true;
        while (i < s.length()) {
            if (flag) {
                for (int j = 1; j < numRows; j++) {
                    if (i == s.length()) break;
                    tmp[j].append(s.charAt(i));
                    i++;
                }
                flag = false;
            } else {
                for (int j = numRows - 2; j >= 0; j--) {
                    if (i == s.length()) break;
                    tmp[j].append(s.charAt(i));
                    i++;
                }
                flag = true;
            }

        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder g : tmp) {
            res.append(g.toString());
        }
        return res.toString();
    }

    public int maxArea(int[] height) {
        int maxArea = 0;
        int l = 0;
        int r = height.length;
        while (l < r) {
            maxArea = Math.max(Math.min(height[l], height[r]) * (r - l), maxArea);
            if (height[l] == height[r]) {
                break;
            } else {
                if (height[l] > height[r]) {
                    r--;
                } else {
                    l++;
                }
                ;
            }
        }
        return maxArea;
    }


    public static int minEatingSpeed(int[] piles, int h) {
        int max = 0;
        for (int i = 0; i < piles.length; i++) {
            max = Math.max(max, piles[i]);
        }
        int l = 0;
        int r = max;
        while (r > l) {
            int midSpeed = l + (r - l) / 2;
            if (eated(piles, h, midSpeed)) {
                r = midSpeed;
            } else {
                l = midSpeed + 1;
            }
        }
        return l;

    }

    public static boolean eated(int[] piles, int h, int speed) {
        int time = 0;
        for (int n : piles) {
            time += n / speed + 1;
        }

        ArrayList<String> res = new ArrayList();
        String[] s;
        s = res.toArray(new String[0]);

        Integer a = 10;
        a.toString();
        return time <= h;
    }


    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = (nums1.length + nums2.length + 1) / 2;
        int n2 = (nums2.length + nums1.length + 2) / 2;
        return (getK(nums1, nums2, 0, nums1.length - 1, 0, nums2.length - 1, n1) +
                getK(nums1, nums2, 0, nums1.length - 1, 0, nums2.length - 1, n2)) * 0.5;
    }

    public static double getK(int[] nums1, int[] nums2, int start1, int end1, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        if (len2 < len1) {//保证一边长度更大
            return getK(nums1, nums2, start2, end2, start1, end1, k);
        }
        if (len1 == 0) {
            return nums2[start2 + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }
        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;
        if (nums1[i] > nums2[j]) {
            return getK(nums1, nums2, start1, end1, j + 1, end2, k - (j - start2 + 1));
        } else {
            return getK(nums1, nums2, i + 1, end1, start2, end2, k - (i - start1 + 1));
        }
    }


    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            if (nums2.length % 2 == 1) {
                return nums2[(nums2.length - 1) / 2];
            } else {
                return ((nums2[nums2.length / 2 - 1] + (double) nums2[nums2.length / 2]) * 0.5);
            }
        }
        if (nums2.length == 0) {
            if (nums1.length % 2 == 1) {
                return nums1[(nums1.length - 1) / 2];
            } else {
                return (double) ((nums1[nums1.length / 2 - 1] + (double) nums1[nums1.length / 2]) * 0.5);
            }
        }
        int totolLen = nums1.length + nums2.length;
        int k = (totolLen) / 2;
        boolean isOdd = false;
        if (totolLen % 2 == 1) {
            k = (totolLen + 1) / 2;//偶数就减一除二

        } else {
            isOdd = true;
            k = totolLen / 2;
        }
        double res = 0;
        int nums1P = 0;
        int nums2P = 0;
        while (k > 0) {
            int p1 = k / 2;
            int p2 = k - p1;
            if (p1 == 0) {
                // res =
                res = Math.min(nums1[nums1P], nums2[nums2P]);
                break;
            }
            if (nums1[p1 + nums1P - 1] > nums2[p2 + nums2P - 1]) {
                nums2P += p2;
                k -= p2;
                if (nums2P >= nums2.length - 1) {
                    if (k == 1) {
                        res = Math.min(nums1[nums1P], nums2[nums2P]);
                        break;
                    } else if (k == 2) {
                        res = Math.max(nums1[nums1P], nums2[nums2P]);
                        break;
                    } else {
                        res = nums1[k - (nums2.length - nums2P) - nums1P];
                        break;
                    }
                }
            } else {
                nums1P += p1;
                k -= p1;
                if (nums1P >= nums1.length - 1) {//指到了数组最后一个数
                    if (k == 1) {
                        res = Math.min(nums1[nums1P], nums2[nums2P]);
                        break;
                    } else if (k == 2) {
                        res = Math.max(nums1[nums1P], nums2[nums2P]);
                        break;
                    } else {
                        res = nums2[k - (nums1.length - nums1P) - nums2P];
                        break;
                    }

                }
            }
        }
        if (isOdd) {
            return (double) ((res + Math.max(nums1[nums1P], nums2[nums2P])) * 0.5);
        } else {
            return res;
        }
        // return res;

    }


    public static int lengthOfLongestSubstring(String s) {
        if ("".equals(s)) return 0;
        int res = 1;//哈希表的最大容量
        int size = 1;
        HashMap map = new HashMap();
        map.put(s.charAt(0), 1);
        int l = 0;
        int r = 0;
        while (r < s.length() - 1) {
            r++;
            char key = s.charAt(r);
            if (!map.containsKey(key)) {
                map.put(key, 1);
                size++;
                res = Math.max(size, res);
            } else {
                while (map.containsKey(key)) {
                    char key1 = s.charAt(l);
                    map.remove(key1);
                    size--;
                    l++;
                }
                map.put(key, 1);
                size++;

            }
        }
        return res;
    }

    class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
