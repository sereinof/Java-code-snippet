package person.man.zhouyuqing;

import java.util.HashMap;

public class TonggouString {
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> sMap = new HashMap<Character, Character>();
        HashMap<Character, Character> tMap = new HashMap<Character, Character>();
        for (int i = 0; i < s.length(); i++) {
            char ss = s.charAt(i);
            char tt = s.charAt(i);
            //  s  -> t
            if (sMap.containsKey(ss) && sMap.get(ss) != tt) {
                return false;
            } else {
                sMap.put(ss, tt);
            }
            if (tMap.containsKey(tt) && sMap.get(tt) != ss) {
                return false;
            } else {
                sMap.put(tt, ss);
            }
        }
        return true;
    }

    public boolean wordPattern(String pattern, String s) {
        HashMap<Character, String> sMap = new HashMap<Character, String>();
        HashMap<String, Character> tMap = new HashMap<String, Character>();
        String[] t = s.split(" ");
        for (int i = 0; i < pattern.length(); i++) {
            char ss = pattern.charAt(i);
            String tt = t[i];
            //  s  -> t
            if (sMap.containsKey(ss) && !sMap.get(ss).equals(tt)) {
                return false;
            } else {
                sMap.put(ss, tt);
            }
            if (tMap.containsKey(tt) && !tMap.get(tt).equals(ss)) {
                return false;
            } else {
                tMap.put(tt, ss);
            }
        }
        return true;
    }

    public boolean isAnagram(String s, String t) {
        int[] hashmap = new int[26];
        int[] hashmap1 = new int[26];
        for (int i = 0; i < s.length(); i++) {
            hashmap[s.charAt(i) - 97]++;
        }
        for (int i = 0; i < s.length(); i++) {
            hashmap1[t.charAt(i) - 97]++;
        }
        for (int i = 0; i < 26; ) {
            if (hashmap[i] != hashmap1[i]) {
                return false;
            }
        }
        return true;
    }
    public boolean isHappy(int n) {
  return true;
    }
}
