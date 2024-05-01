package person.man.zhouyuqing;

import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    public int hammingWeight(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if (n == 0) {
                return res;
            }
            int now = n & 1;
            if (now == 1) {
                res++;
            }

            n >>>= 1;
        }
        return res;
    }

    public int[] plusOne(int[] digits) {
        if (digits[digits.length - 1] < 9) {
            digits[digits.length - 1]++;
            return digits;
        } else {
            digits[digits.length - 1] = digits[digits.length - 1] + 1 - 10;
        }
        boolean has = true;
        for (int i = digits.length - 2; i >= 0; i--) {
            if (has == true) {
                if (digits[i] + 1 > 9) {
                    digits[i] = digits[i] + 1 - 10;
                    has = true;
                } else {
                    digits[i] = digits[i] + 1;
                    has = false;
                }
            } else {
                break;
            }
        }
        if (has) {

            int[] newArray = new int[digits.length + 1];

// 在新数组的第一个位置插入 1
            newArray[0] = 1;

// 使用 System.arraycopy 将原始数组复制到新数组的后面
            System.arraycopy(digits, 0, newArray, 1, digits.length);
            return newArray;
        }
        return digits;
    }
}
