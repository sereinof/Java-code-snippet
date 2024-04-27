package person.man.zhouyuqing;

import java.util.HashMap;

public class TonggouString {
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character,Character> sMap = new HashMap<Character, Character>();
        HashMap<Character,Character> tMap = new HashMap<Character, Character>();
         for (int i =0;i<s.length();i++){
             char ss = s.charAt(i);
             char tt = s.charAt(i);
               //  s  -> t
             if(sMap.containsKey(ss) && sMap.get(ss)!=tt){
                 return false;
             }else{
                 sMap.put(ss,tt);
             }
             if(tMap.containsKey(tt) && sMap.get(tt)!=ss){
                 return false;
             }else{
                 sMap.put(tt,ss);
             }
         }
     return true;
    }
}
