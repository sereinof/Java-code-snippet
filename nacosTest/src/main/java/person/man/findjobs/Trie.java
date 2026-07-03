package person.man.findjobs;

public class Trie {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return null;
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            for (int j = 0; j < strs.length; j++) {
                if (i >= strs[j].length()) {
                    return res.toString();
                }
                if (strs[j].charAt(i) != strs[0].charAt(i)) {
                    return res.toString();
                }
            }
            String a = "niggg";

            res.append(strs[0].charAt(i));
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s = "zhou   yu  qing     yasuo";//
        String res = new Trie().reverseWords(s);
        System.out.println(res);
    }

    public String compact(StringBuilder s) {
        int p = 0;
        int q = 0;
        while (s.charAt(p) != ' ') {
            p++;
            q++;
        }
        p++;
        while (q != (s.length() - 1)) {
            while (!(s.charAt(q) == ' ' && s.charAt(q + 1) != ' ')) {
                q++;
            }
            q++;
            while (q < s.length() && s.charAt(q) != ' ') {
                s.setCharAt(p, s.charAt(q));
                p++;
                q++;
            }
            if (q == s.length()) {
                break;
            }
            s.setCharAt(p, ' ');
            p++;
        }
        return s.substring(0, p);
    }

    public String reverseWords(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (s.charAt(l) == ' ') {
            l++;
        }
        while (s.charAt(r) == ' ') {
            r--;
        }
        StringBuilder res = new StringBuilder();
        for (int i = l; i <= r; i++) {
            if (s.charAt(i) == ' ') {
                if (s.charAt(i + 1) != ' ') {
                    res.append(s.charAt(i));
                }
            } else {
                res.append(s.charAt(i));
            }
        }
        res.reverse();
        int start = 0;
        for (int i = 0; i < res.length(); i++) {
            if (res.charAt(i) == ' ') {
                System.out.println(res.toString());
                reverseRange(res, start, i);
                start = i + 1;
            }
        }
        reverseRange(res, start, res.length());
        return res.toString();
    }

    public static void reverseRange(StringBuilder sb, int start, int end) {
        // 确保索引合法
        if (start < 0 || end > sb.length() || start >= end) return;
        int left = start;
        int right = end - 1;
        if (end == sb.length() - 1) {
            right = end + 1;
        }
        while (left < right) {
            char temp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, temp);
            left++;
            right--;
        }
    }

    public String convert(String s, int numRows) {
        //虽然但是 再次见到还是觉得很新颖


    }
}
