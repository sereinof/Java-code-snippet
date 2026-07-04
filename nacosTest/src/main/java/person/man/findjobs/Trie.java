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

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";//
        String res = new Trie().convert(s, 3);
        System.out.println(res);
    }

    public String convert(String s, int numRows) {
        //下面这种方法太low了 只需要一个索引变量就行了 哥
        if(s.length()==1){
            return s;
        }
        if(numRows==1){
            return  s;
        }
        StringBuilder res = new StringBuilder();
        //虽然但是 再次见到还是觉得很新颖
        int len = (2 * s.length()) / 3;
        char[][] arr = new char[numRows][len];

        boolean isDown = true;
        int indexA = 0;
        int indexB = 0;
        for (int i = 0; i < s.length(); i++) {
            arr[indexA][indexB] = s.charAt(i);

            if (indexA == 0 && indexB != 0) {
                isDown = true;
            }
            if (indexA == arr.length - 1) {
                isDown = false;
            }

            if (isDown) {
                indexA++;
            } else {
                indexA--;
                indexB++;
            }

        }


        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < len; j++) {
                if (arr[i][j] != 0) {
                    res.append(arr[i][j]);
                }
            }
        }
        return res.toString();
    }
}
