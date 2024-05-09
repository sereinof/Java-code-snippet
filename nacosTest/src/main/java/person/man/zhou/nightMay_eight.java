package person.man.zhou;

import java.util.ArrayList;
import java.util.List;

public class nightMay_eight {


    public static void main(String[] args) {
        new newLearning().generateParenthesis(3);
    }
    public List<String> generateParenthesis(int n) {
        List<List<String>> dp = new ArrayList<>(n + 1);
        ArrayList dp0 = new ArrayList();
        dp0.add("");
        dp.add(dp0);
        ArrayList dp1 = new ArrayList();
        dp1.add("()");
        dp.add(dp1);
        ArrayList dp2 = new ArrayList();
        dp1.add("()()");
        dp1.add("(())");
        dp.add(dp2);
        for (int i = 3; i <=n; i++) {
            List<String> thisTime = new ArrayList<String>();
            //这里要做一个遍历
            for (int p = 1; p <= i - 1; p++) {
                int q = i - 1 - p;
                for (String a : dp.get(p)) {
                    for (String b : dp.get(q)) {
                        StringBuilder val = new StringBuilder();
                        val.append("(");
                        val.append(a);
                        val.append(")");
                        val.append(b);
                        thisTime.add(val.toString());
                    }
                }
            }
            dp.add(thisTime);
        }

        return dp.get(n);
    }
}
