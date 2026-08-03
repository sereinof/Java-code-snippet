package person.man.findjobs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class LCHashMap {


    public static void main(String[] args) {
        String pattern = "abba";
        String s = "dog cat cat dog";
        // new LCHashMap().wordPattern(pattern, s);
        int[] nums = new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        int res = new LCHashMap().longestConsecutive(nums);
    }

    public boolean wordPattern(String pattern, String s) {
        String[] t = s.split(" ");
        if (pattern.length() != t.length) {
            return false;
        }

        HashMap<Character, String> SToC = new HashMap<>();
        HashMap<String, Character> SCtoS = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char sch = pattern.charAt(i);
            String tch = t[i];
            if (SToC.containsKey(sch)) {
                if (!SToC.get(sch).equals(tch)) {
                    return false;
                }
            } else {
                SToC.put(sch, tch);
            }
            if (SCtoS.containsKey(tch)
            ) {
                if (SCtoS.get(tch) != sch) {
                    return false;
                }

            } else {
                SCtoS.put(tch, sch);
            }
        }
        return true;
    }


    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] map = new int[128];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
        }
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            if (map[ch] == 0) {
                return false;
            }
            map[ch]--;
        }
        return true;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String s = strs[i];
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if (map.containsKey(key)) {
                List<String> value = map.get(key);
                value.add(strs[i]);
            } else {
                List value = new ArrayList<>();
                value.add(strs[i]);
                map.put(key, value);
            }

        }
        return new ArrayList<>(map.values());
    }

    public boolean isHappy(int n) {
        int num = n;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(num, 1);
        while (true) {
            int nextNum = 0;
            while (num != 0) {
                nextNum += (num % 10) * (num % 10);
                num /= 10;
            }
            ;
            if (map.containsKey(nextNum)) {
                return false;
            }
            if (nextNum == 1) {
                return true;
            }
            map.put(nextNum, 1);
            num = nextNum;
        }
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(num)) {
                if (i - map.get(num) <= k) {
                    return true;
                }
            }
            map.put(num, i);
        }
        return false;
    }

    public int longestConsecutive(int[] nums) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], nums[i]);
            }

        }
        for (int key : map.keySet()) {
            int begin = key;
            if (!map.containsKey(key - 1)) {
                int len = 0;
                while (map.containsKey(begin++)) {
                    len++;
                }
                res = Math.max(res, len);
            }
        }

        return res;
    }

    public List<String> summaryRanges(int[] nums) {
        if(nums.length==0){
            return  null;
        }
        List<String> res = new ArrayList<>();
        int l = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] + 1 != nums[i]) {
                StringBuilder range = new StringBuilder();
                if (i - 1 == l) {
                    range.append(nums[l]);
                } else {
                    range.append(nums[l]);
                    range.append("->");
                    range.append(nums[i - 1]);
                }
                res.add(range.toString());
                l = i;
            }
        }
        StringBuilder finalRange = new StringBuilder()
        if (l == nums.length - 1) {
            finalRange.append(nums[l]);
        } else {
            finalRange.append(nums[l]);
            finalRange.append("->");
            finalRange.append(nums[nums.length - 1]);
        }
        return res;
    }
}
