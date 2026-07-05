package person.man.findjobs;

public class Palind {
    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        boolean res = new Palind().isPalindrome(s);
        System.out.println(res);
    }

    public boolean isPalindrome(String s) {
        StringBuilder str = new StringBuilder(s);
        int ptr = 0;
        int ptr1 = 0;
        while (ptr1 != s.length()) {
            char ch = str.charAt(ptr1);
            if ((ch <= 'z' & ch >= 'a') || (ch >= '0' && ch <= '9')) {
                str.setCharAt(ptr, ch);
                ptr++;
            } else if (ch <= 'Z' & ch >= 'A') {
                str.setCharAt(ptr, (char) (ch + 32));
                ptr++;
            }
            ptr1++;
        }
        ptr--;
        int index = 0;
        while (ptr > index) {
            if (str.charAt(index) == str.charAt(ptr)) {
                ptr--;
                index++;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isSubsequence(String s, String t) {

    }
}
