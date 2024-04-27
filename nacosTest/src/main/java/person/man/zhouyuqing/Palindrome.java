package person.man.zhouyuqing;

import java.util.Locale;

public class Palindrome {
    public static void main(String[] args) {
       boolean res =    new Palindrome().isPalindrome(" ");
        System.out.println(res);
    }

    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int l = 0;
        int r = s.length() - 1;
        while (l <= r) {
            while ( l<=r&& !Character.isLetterOrDigit(s.charAt(l))) {
                l++;
            }
            while (  l<=r&& !Character.isLetterOrDigit(s.charAt(l))) {
                r--;
            }
            if( l<=r){
                if (  s.charAt(l) == s.charAt(r)) {
                    r--;
                    l++;
                } else {
                    return false;
                }
            }

        }
        return true;
    }
}
