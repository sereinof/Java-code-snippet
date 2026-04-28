package person.man.findjobs;

import java.util.*;

public class StringSummery {
    public static void main(String[] args) {
         String[] param = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
         groupAnagrams(param);
    }
    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

        //可以将字符串排序 然后用哈希表
        //排序消耗性能 将异位字符串提取共同特征乃是最优解
        for (int i = 0; i < strs.length; i++) {
            String sign = sign(strs[i]);
            if (map.containsKey(sign)) {
                map.get(sign).add(strs[i]);
            } else {
                ArrayList strArr = new ArrayList<String>();
                strArr.add(strs[i]);
                map.put(sign, strArr);
            }
        }
        List<List<String>> res = new ArrayList<>();
         map.forEach((key,value)->{
             res.add(value);
         });
        return res;
    }

    public static String sign(String string) {

        HashMap info = new HashMap<Character, Integer>();
        for (int i = 0; i < string.length(); i++) {
            char letter = string.charAt(i);
            if (info.containsKey(letter)) {
                info.put(letter, (int) info.get(letter) + 1);
            } else {
                info.put(letter, 1);
            }
        }
        String res = "";
        for (int i = 0; i < 26; i++) {
            Character character = (char) (i + 97);
            if (info.containsKey(character)) {
                String append = character + info.get(character).toString();
                res += append;
            }
            ;
        }
        return res;
    }
}