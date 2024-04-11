package person.man.zhouyuqing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class wordDivide {
    public static void main(String[] args) {
        ArrayList wordDict  = new ArrayList();
        wordDict.add("leet");
        wordDict.add("code");
        new wordDivide().wordBreak("leetcode",wordDict);
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet set = new HashSet();
        boolean[] dp = new boolean[s.length() + 1];
        for (String i : wordDict) {
            set.add(i);
        }
        dp[0] = true;
        for (int i = 1; i < s.length()+1; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
                ;
            }
        }
        return dp[s.length()];
    }
}
