package person.man.findjobs;

import java.util.HashMap;

public class Binary {

    public String addBinary(String a, String b) {
        int aI = a.length() - 1;
        int bI = b.length() - 1;
        StringBuilder res = new StringBuilder();
        int carry = 0;
        while (aI >= 0 || bI >= 0) {
            int vala = 0;
            int valb = 0;
            if (aI >= 0 && bI >= 0) {
                valb = b.charAt(bI) == '0' ? 0 : 1;
                vala = a.charAt(aI) == '0' ? 0 : 1;
            } else if (aI >= 0) {
                vala = a.charAt(aI) == '0' ? 0 : 1;
            } else {
                valb = b.charAt(bI) == '0' ? 0 : 1;
            }
            if ((vala + valb + carry % 2 == 1)) {
                res.insert(0, '1');
            } else {
                res.insert(0, '0');
            }
            aI--;
            bI--;
            carry = (vala + valb + carry) / 2;
        }
        if (carry != 0) {
            res.insert(0, '1');
        }
        return res.toString();
    }

    public int reverseBits(int n) {
        int res = 0;
        int count = 31;
        while (count != 0) {
            int maslVal = n & 1;
            if (maslVal > 0) {
                res |= 1;
            }
            res <<= 1;
            n >>= 1;
            count--;
        }
        res |= (n & 1);
        return res;
    }

    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            int val = n & 1;
            if (val == 1) count++;
            n >>>= 1;
        }
        return count;
    }

    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = res ^ nums[i];
        }
        return res;
    }

    public int rangeBitwiseAnd(int left, int right) {
        while (left < right) {
            right &= (right - 1);
        }
        return right;
    }

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int reverse = 0;
        int num = x;
        while (num != 0) {
            reverse += ((num % 10) + reverse * 10);
            num /= 10;
        }
        return reverse == x;
    }

    public int[] plusOne(int[] digits) {
        if (digits[digits.length - 1] < 9) {
            digits[digits.length - 1]++;
            return digits;
        }
        int carry = 1;
        for (int i = digits[digits.length - 1]; i >= 0; i--) {
            int sum = digits[i] + carry;
            digits[i] = sum % 10;
            carry = sum / 10;
        }
        if (carry == 0) {
            return digits;
        } else {
            int[] res = new int[digits.length + 1];
            res[0] = carry;
            for (int i = 1; i < res.length; i++) {
                res[i] = digits[i - 1];
            }
            return res;
        }
    }

    public int trailingZeroes(int n) {
        int count = 0;
        while (n != 0) {
            n /= 5;
            count++;
        }
        return count;
    }

    public int mySqrt(int x) {
        int l = -1;
        int r = x == Integer.MAX_VALUE ? Integer.MAX_VALUE - 1 : x;
        while (l + 1 != r) {
            int mid = l + (r - l) / 2;
            int val = mid * mid;
            if (val == x) {
                return mid;
            } else if (val < x) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return l == -1 ? 0 : l;
    }

    public double myPow(double x, int n) {
        boolean flag = false;
        if (n < 0) {
            flag = true;
            n = -n;
        }
        double val = x;
        double res = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                res += val;
            }
            val = val * val;
            n >>>= 1;
        }
        if (flag) {
            return 1 / res;
        }
        return res;
    }

    public int maxPoints(int[][] points) {
        if (points.length <= 1) return points.length;
        int res = 2;
        for (int i = 0; i < points.length; i++) {
            HashMap<String, Integer> map = new HashMap<>();
            for (int j = i + 1; j < points.length; j++) {
                String key = getKey(points, i, j);
                if (map.containsKey(key)) {
                    res = Math.max(res, map.get(key) + 1);
                    map.put(key, map.get(key) + 1);
                } else {
                    map.put(key, 1);
                }
            }

        }
        return res;
    }

    private String getKey(int[][] points, int i, int j) {
        int dy = points[i][1] - points[j][1];
        int dx = points[i][0] - points[i][0];
        int x = dx;
        int y = dy;
        if (dx == 0) {
            return "0/1";
        }
        if (dy == 0) {
            return "1/0";
        }
        if (dx < 0) {
            x = -dx;
            y = -dy;
        }
        int gcd = gcd(Math.abs(dx), Math.abs(dy));
        x /= gcd;
        y /= gcd;
        return x + "/" + y;
    }

    public int gcd(int a, int b) {
        return b == 0 ? Math.abs(a) : gcd(b, a % b);
    }

}
