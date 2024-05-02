package person.man.zhouyuqing;

public class Sqrt {
    public static void main(String[] args) {
        int res = new Sqrt().mySqrt(1);
        System.out.println(res);
    }

    public int mySqrt(int x) {
        int l = 0;
        int r = x;
        int res = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (mid * mid == x) {
                return mid;
            } else if (mid * mid > x) {
                r = mid - 1;
            } else {
                res = mid;
                l = mid + 1;
            }
        }
        return res;
    }
}
