package person.man.findjobs;

import java.util.Arrays;

public class KMP {
    public static void main(String[] args) {
        String s = "issip";
        int[] next = new KMP().getNext(s);
        System.out.println(Arrays.toString(next));
        int index = new KMP().strStr("mississippi", "issip");
        System.out.println(index + "----------");
    }

    public int[] getNext(String s) {
        int[] res = new int[s.length()];
        res[0] = 0;
        int l = 0;
        for (int k = 1; k < s.length(); k++) {
            if (s.charAt(k) == s.charAt(l)) {
                res[k] = res[k - 1] + 1;
                l++;
            } else {
                while (l >= 0) {
                    if (s.charAt(l) == s.charAt(k)) {
                        if (l == 0) {
                            res[k] = 1;
                        } else {
                            res[k] = l + 1;
                        }
                        l++;
                        break;
                    } else {
                        if (l == 0) {
                            res[k] = 0;
                            break;
                        }
                        l = res[l - 1];//这句好难理解
                    }
                }
            }
        }
        return res;
    }

    public int strStr(String haystack, String needle) {
        //使用kmp实现
        int n = haystack.length(), m = needle.length();
        int[] kmpArr = getNextII(needle);
        if (m > n) {
            return -1;
        }
        int L = 0;
        int l = 0;
        while (L != n) {
            while (L < n && l < m && haystack.charAt(L) == needle.charAt(l)) {
                l++;
                L++;
            }
            if (l == needle.length()) {
                return L - l;
            } else {
                if (L == n) {
                    return -1;
                }
                if (l == 0) {
                    L++;
                }else {
                    l = kmpArr[l-1];
                }
            }
        }
        return -1;
    }

    public int[] getNextII(String s) {
        int[] kmp = new int[s.length()];
        int l = 0;
        for (int i = 1; i < s.length(); i++) {
            while (s.charAt(i) != s.charAt(l) && l > 0) {
                l = kmp[l - 1];
            }
            if (s.charAt(i) != s.charAt(l)) {
                kmp[i] = 0;
            } else {
                kmp[i] = l + 1;
                l++;
            }
        }
        return kmp;
    }
}
