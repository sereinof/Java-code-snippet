package person.man.zhouyuqing;

import java.util.*;

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
                if (cnt < 0) {
                    con.add(i);
                    cnt = 0;
                }
                ;
            }
        }
        while (cnt != 0) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    con.add(i);
                    cnt--;
                    if (cnt == 0) {
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (!con.contains(i)) {
                res.append(s.charAt(i));
            }
        }

        return res.toString();
    }

    public String mergeAlternately(String word1, String word2) {
//写个简单字符串压压惊吧
        int i = 0;
        int j = 0;
        boolean flag = true;
        StringBuilder res = new StringBuilder();
        while (i < word1.length() || j < word2.length()) {
            if (flag) {
                if (i < word1.length()) {
                    res.append(word1.charAt(i));
                    i++;
                }
                flag = false;
            } else {
                if (j < word2.length()) {
                    res.append(word2.charAt(j));
                    i++;
                }
                flag = true;
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        new MayEight().longestValidParentheses(")()())()()(");
    }
    public int longestValidParentheses(String s) {
        //和回文有关呢这个题目
        if (s.length() == 0) {
            return 0;
        }f
        int len = s.length();
        int dp[] = new int[len];
        dp[0] = 0;
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == '(') {
                dp[i] = 0;
            } else if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    if (i - 2 >= 0) {
                        dp[i] = dp[i - 2] + 2;
                    } else {
                        dp[i] = 2;
                    }

                } else {//')'
                    if (i - dp[i - 1] - 1 >= 0) {
                        if (s.charAt(i - dp[i - 1] - 1) == '(') {
                            if (i - dp[i - 1] - 2 >= 0) {
                                dp[i] = dp[i - 1] + 2 + dp[i - dp[i - 1] - 2];
                            } else {
                                dp[i] = dp[i - 1] + 2;
                            }

                        } else {
                            dp[i] = 0;
                        }
                    } else {
                        dp[i] = 0;
                    }
                }

            }
        }
        OptionalInt res = Arrays.stream(dp).max();
        return res.getAsInt();

    }
}
