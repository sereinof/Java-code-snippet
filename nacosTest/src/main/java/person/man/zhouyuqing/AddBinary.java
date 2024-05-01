package person.man.zhouyuqing;

public class AddBinary {
    public static void main(String[] args) {
        String res = new AddBinary().addBinary("101111", "10");
        System.out.println(res);

    }

    public String addBinary(String a, String b) {
        String res = "";
        boolean hasN = false;
        int len1 = a.length() - 1;
        int len2 = b.length() - 1;
        while (len1 >= 0 && len2 >= 0) {
            if (a.charAt(len1) != b.charAt(len2)) {
                if (hasN) {
                    res = "0" + res;
                    hasN = true;
                } else {
                    res = "1" + res;
                }
                len1--;
                len2--;
            } else {
                if ('0' == (a.charAt(len1))) {
                    if (hasN) {
                        res = "1" + res;
                        hasN = false;
                    } else {
                        res = "0" + res;
                    }
                    len1--;
                    len2--;
                } else {
                    if (hasN) {
                        res = "1" + res;
                        hasN = true;
                    } else {
                        res = "0" + res;
                        hasN = true;
                    }
                    len1--;
                    len2--;
                }
            }
        }
        if (len1 < 0) {
            while (len2 >= 0) {
                if (b.charAt(len2) == '0') {
                    if (hasN) {
                        res = "1" + res;
                        len2--;
                        hasN = false;
                    } else {
                        res = "0" + res;
                        len2--;
                    }
                } else {
                    if (hasN) {
                        res = "0" + res;
                        len2--;
                        hasN = true;
                    } else {
                        res = "1" + res;
                        len2--;
                    }
                }
            }
        } else {
            while (len1 >= 0) {
                if (a.charAt(len1) == '0') {
                    if (hasN) {
                        res = "1" + res;
                        hasN = false;
                           len1--;
                    } else {
                        res = "0" + res;
                        len1--;
                    }
                } else {
                    if (hasN) {
                        res = "0" + res;
                        len1--;
                        hasN = true;
                    } else {
                        res = "1" + res;
                        len1--;
                    }
                }
            }
        }
        if (hasN) {
            res = "1" + res;
        }
        return res;
    }
}
