package person.man.zhouyuqing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MayEight {

    public int minAddToMakeValid(String s) {
        int need = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            } else {
                count--;
            }
            if (count < 0) {
                need++;
                count = 0;
            }

        }
        return Math.abs(need - count);
    }

    public boolean checkValidString(String s) {
        int max = 0;
        int min = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                max++;
                min++;
            } else if (s.charAt(i) == '*') {
                min--;
                max++;
            } else {
                min--;
                max--;
            }
            if (min < 0) {
                min = 0;
            }
            if (min > max) {
                return false;
            }

        }

        return min == 0;
    }

    public String minRemoveToMakeValid(String s) {
        StringBuilder res = new StringBuilder();
        HashSet<Integer> con = new HashSet<Integer>();
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                cnt++;
            } else if (s.charAt(i) == ')') {
                cnt--;
                if(cnt<0){
                    con.add(i);
                    cnt=0;
                };
            }
        }
        while (cnt!=0){
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)=='('){
                    con.add(i);
                    cnt--;
                    if(cnt==0){
                        break;
                    }
                }
            }
        }
        for(int i = 0;i<s.length();i++){
            if(!con.contains(i)){
                res.append(s.charAt(i));
            }
        }

        return res.toString();
    }
}
