package person.man.zhouyuqing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class TopKFrequent {

    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int muiti = 0;
//栈用来保存状态
        LinkedList<Integer> stack_multi = new LinkedList<>();
        LinkedList<String> stack_res = new LinkedList<>();
        for (Character c : s.toCharArray()) {
            if (c == '[') {
                stack_multi.addLast(muiti);
                stack_res.addLast(res.toString());
                muiti = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                //出一次栈
                StringBuilder tmp = new StringBuilder();
                int cur_multi = stack_multi.removeLast();
                for (int i = 0; i < cur_multi; i++) {
                    tmp.append(res);
                }
            } else if (c >= '0' && c <= '9') {
                muiti = muiti * 10 + Integer.parseInt(c + "");
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap();
        int max = 0;
        ArrayList max_num = new ArrayList<>();
        for (int i : nums) {
            Integer re_nu = map.get(Integer.valueOf(i));
            if (re_nu != null) {
                map.put(i, re_nu + 1);
                if (re_nu + 1 > max) {
                    max = re_nu + 1;
                    max_num.clear();
                    max_num.add(re_nu);
                }
                if (re_nu + 1 == max) {

                } else {

                }
            } else {
                map.put(i, 1);
            }
        }
        return new int[]{};
    }
}
