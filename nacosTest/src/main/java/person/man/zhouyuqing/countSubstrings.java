package person.man.zhouyuqing;

public class countSubstrings {
    public int countSubstrings(String s) {
        int len = s.length();
        int res = 0;
        boolean dp[][] = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (i == j) {
                        dp[i][j] = true;
                    } else if (j == i + 1) {
                        dp[i][j] = s.charAt(i) == s.charAt(j);
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                    if (dp[i][j] == true) {
                        res++;
                    }
                }
            }
        }
        return res;
    }
}
