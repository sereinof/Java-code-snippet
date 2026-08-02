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
        new LCHashMap().wordPattern(pattern, s);
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
            String key =new String(chars);
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

    }
}
