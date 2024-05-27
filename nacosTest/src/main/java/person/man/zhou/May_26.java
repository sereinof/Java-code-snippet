package person.man.zhou;

import java.util.HashMap;

public class May_26 {
    public boolean isHappy(int n) {
        HashMap map = new HashMap();
        map.put(n,1);
        while (n!=1){
            int x = getNext(n);
            if(map.containsKey(x)){
                return false;
            }else {
                map.put(x,1);
                n = x;
            }
        }
        return true;
    }

    public int getNext(int x) {
        int res = 0;
        while (x != 0) {
            int last = x % 10;
            res += (last * last);
            x = x/10;
        }
        return res;
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) return false;
//子结构够的判断情况有 以当前节点是否一样  不然就是
        return recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    public boolean recur(TreeNode A, TreeNode B) {
        if (A == null || B == null) return false;
        if (A.val != B.val) return false;
        return recur(A.right, B.right) && recur(A.left, B.left);

    }

    public int longestValidParentheses(String s) {
        if (s.length() == 0) return 0;
        int[] dp = new int[s.length()];
        dp[0] = 0;
//dp 函数 为第i个字符的最大有效括号的长度是？
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                dp[i] = 0;
            } else {//')'
                if (s.charAt(i - 1) == '(') {
                    if (i - 2 >= 0) {
                        dp[i] = dp[i - 2] + 2;
                    } else {
                        dp[i] = 2;
                    }
                } else {//')'结尾  判断 i- dp[i-1] -1的
                    //这种情况的  ()(())
                    if (i - dp[i - 1] - 1 >= 0) {
                        if (s.charAt(i - dp[i - 1] - 1) == '(') {
                            if (i - dp[i - 1] - 2 >= 0) {
                                dp[i] = dp[i - 1] + 2 + dp[i - dp[i - 1] - 2];
                            } else {
                                dp[i] = dp[i - 1] + 2;
                            }

                        }
                    } else {
                        dp[i] = 0;
                    }

                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}

