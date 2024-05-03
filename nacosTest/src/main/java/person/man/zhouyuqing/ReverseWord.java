package person.man.zhouyuqing;

public class ReverseWord {
    public static void main(String[] args) {
        String res = new ReverseWord().reverseWords("  hello world  ");
        System.out.println(res);

    }

    public String reverseWords(String s) {
        String res = "";
        String tmp = "";
        int l = 0;
        int r = 0;
        while (r < s.length()) {
            while (l < s.length() && s.charAt(l) == ' ') {
                l++;
                r++;
            }
            while (r < s.length() && s.charAt(r) != ' ') {
                r++;
            }
            if (r > l) {
                tmp = s.substring(l, r);
            }

            res = tmp + " " + res;
            l = r;
        }
        return res.substring(0, res.length() - 1);
    }

    public int[] twoSum(int[] numbers, int target) {

    }
}
