package person.man.zhouyuqing;

import java.util.ArrayList;

public class ConverString {
    public static void main(String[] args) {
        String result = new ConverString().convert("PAYPALISHIRING",3);
        System.out.println(result);
    }
    public String convert(String s, int numRows) {
        if(numRows<2){
            return  s;
        }
        StringBuilder res = new StringBuilder();
        ArrayList<StringBuilder> rows = new ArrayList<StringBuilder>(numRows);
        for(int i = 0; i < numRows; i++) rows.add(new StringBuilder());
        int n = 0;
        boolean flag = true;
        for (int i = 0; i < s.length(); i++) {
            rows.get(n).append(s.charAt(i));
            if (n == numRows - 1) {
                flag = false;
            }
            ;
            if (n == 0) {
                flag = true;
            }
            if (flag) {
                n++;
            } else {
                n--;
            }
        }
        for (StringBuilder t : rows) {
            res.append(t.toString());
        }
        return res.toString();
    }
}
