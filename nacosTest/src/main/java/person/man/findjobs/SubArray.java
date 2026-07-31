package person.man.findjobs;

import java.util.*;

public class SubArray {

    public static void main(String[] args) {
        String s = "wordgoodgoodgoodbestword";
        String[] words = new String[]{"word","good","best","good"};
        List<Integer> res = new SubArray().findSubstring(s, words);
        System.out.println(res);


    }

    public int minSubArrayLen(int target, int[] nums) {
        int res = Integer.MAX_VALUE;
        int n = nums.length;
        int l = 0;
        int r = 0;
        int sum = nums[0];
        while (l <= r && r < n) {
            while (sum < target && r < n - 1) {
                r++;
                sum += nums[r];
            }
            while (sum >= target) {
                res = Math.min(res, r - l + 1);
                sum -= nums[l];
                l++;
            }
            if (r < n - 1) {
                r++;
                sum += nums[r];
            } else {
                break;
            }

        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }

    //TODO 改写滑动窗口的代码 使用 for+while改写
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        int l = 0;
        int r = 0;
        int[] map = new int[100];
        int len = 0;
        while (l <= r) {
            while (r < s.length() && map[(int) s.charAt(r)] == 0) {
                map[(int) s.charAt(r)]++;
                r++;
                len++;
            }
            res = Math.max(res, len);
            while (l < s.length() && r < s.length() && s.charAt(l) != s.charAt(r)) {
                map[s.charAt(l)]--;
                l++;
                len--;
            }
            if (l < s.length()) {
                map[s.charAt(l)]--;
                l++;
                len--;
            } else {
                break;
            }

        }
        return res;
    }


    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        int windowCount = words[0].length();
        int wordLen = words[0].length();
        int totalLen = words[0].length() * words.length;
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);
        }

        for (int i = 0; i < windowCount; i++) {
            int Left = i;
            HashMap<String, Integer> windowinfo = new HashMap<>();
            for (int j = i; j < s.length() - wordLen; j += wordLen) {
                String cur = s.substring(j, j + wordLen);
                //A 该词为无关词
                if (!map.containsKey(cur)) {
                    j += wordLen;
                    Left = j;
                    windowinfo.clear();
                    continue;
                }
                windowinfo.put(cur, windowinfo.getOrDefault(cur, 0) + 1);
                while (map.get(cur) < windowinfo.get(cur)) {
                    String cur1 = s.substring(Left, Left + wordLen);
                    windowinfo.put(cur1, windowinfo.getOrDefault(cur1, 0) - 1);
                    Left += wordLen;
                }
                if ((j - Left + wordLen) == totalLen) {
                    res.add(Left);
                }
            }
        }

        return res;
    }
}
