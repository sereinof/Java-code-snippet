package person.man.zhou;

import org.checkerframework.checker.units.qual.A;

import java.util.*;

public class May_19 {
    int min = Integer.MAX_VALUE;
    List<String> res = new ArrayList<>();
    String deleted = "";
    int lenght = 0;

    public static void main(String[] args) {
        List<String> res = new May_19().removeInvalidParentheses("()())()");
        System.out.println(res);
    }
    public int[] maxSlidingWindow(int[] nums, int k) {

    }


    public List<String> removeInvalidParentheses(String s) {
        return BFS(s);
    }

    public List<String> BFS(String s) {
        List<String> rees = new ArrayList<>();
        rees.add("");
        Queue<String> queue = new ArrayDeque<>();
        queue.add(s);
        while (!queue.isEmpty()) {
            Set<String> res = new HashSet<>();
            int size = queue.size();
            HashSet<String> set = new HashSet<>();
            for (int i = 0; i < size; i++) {
                String str = queue.poll();
                set.add(str);
                if (isValid(str)) {
                    res.add(str);
                }
            }
            if (!res.isEmpty()) {
                return res.stream().toList();
            }
            for (String str:set) {
                for (int j = 0; j < str.length(); j++) {
                    if (str.charAt(j) == '(' || str.charAt(j) == ')') {
                        if (j > 0 && str.charAt(j) == s.charAt(j - 1))
                            continue;
                        if (j == str.length() - 1) {
                            queue.add(str.substring(0, j));
                        } else {
                            queue.add(str.substring(0, j) + str.substring(j + 1, str.length()));
                        }
                    }
                }
            }

        }
        return rees;
    }

    public boolean isValid(String str) {
        // 这个方法有问题需要改写
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                cnt++;
            } else if (str.charAt(i) == ')') {
                cnt--;
                if (cnt < 0) {
                    return false;
                }
            }
        }

        return cnt == 0;
    }
}
