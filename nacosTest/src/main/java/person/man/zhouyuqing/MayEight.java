package person.man.zhouyuqing;

import java.util.*;

public class MayEight {

    public int minAddToMakeValid(String s) {
        int need = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            } else {
                count--;
            }
            if (count < 0) {
                need++;
                count = 0;
            }

        }
        return Math.abs(need - count);
    }

    public boolean checkValidString(String s) {
        int max = 0;
        int min = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                max++;
                min++;
            } else if (s.charAt(i) == '*') {
                min--;
                max++;
            } else {
                min--;
                max--;
            }
            if (min < 0) {
                min = 0;
            }
            if (min > max) {
                return false;
            }

        }

        return min == 0;
    }

    public String minRemoveToMakeValid(String s) {
        StringBuilder res = new StringBuilder();
        HashSet<Integer> con = new HashSet<Integer>();
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                cnt++;
            } else if (s.charAt(i) == ')') {
                cnt--;
                if (cnt < 0) {
                    con.add(i);
                    cnt = 0;
                }
                ;
            }
        }
        while (cnt != 0) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    con.add(i);
                    cnt--;
                    if (cnt == 0) {
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (!con.contains(i)) {
                res.append(s.charAt(i));
            }
        }

        return res.toString();
    }

    public String mergeAlternately(String word1, String word2) {
//写个简单字符串压压惊吧
        int i = 0;
        int j = 0;
        boolean flag = true;
        StringBuilder res = new StringBuilder();
        while (i < word1.length() || j < word2.length()) {
            if (flag) {
                if (i < word1.length()) {
                    res.append(word1.charAt(i));
                    i++;
                }
                flag = false;
            } else {
                if (j < word2.length()) {
                    res.append(word2.charAt(j));
                    i++;
                }
                flag = true;
            }
        }
        return res.toString();
    }


    public int longestValidParentheses(String s) {
        //和回文有关呢这个题目
        if (s.length() == 0) {
            return 0;
        }
        int max = 0;
        int len = s.length();
        int dp[] = new int[len];
        dp[0] = 0;
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == '(') {
                dp[i] = 0;
            } else if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    if (i - 2 >= 0) {
                        dp[i] = dp[i - 2] + 2;
                    } else {
                        dp[i] = 2;
                    }

                } else {//')'
                    if (i - dp[i - 1] - 1 >= 0) {
                        if (s.charAt(i - dp[i - 1] - 1) == '(') {
                            if (i - dp[i - 1] - 2 >= 0) {
                                dp[i] = dp[i - 1] + 2 + dp[i - dp[i - 1] - 2];
                            } else {
                                dp[i] = dp[i - 1] + 2;
                            }

                        } else {
                            dp[i] = 0;
                        }
                    } else {
                        dp[i] = 0;
                    }
                }

            }
            max = Math.max(max, dp[i]);
        }

        return dp[len - 1];

    }

    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while ((r != l)) {
            int mid = l + (r - l) >> 1;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }


    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        ;
        int len1 = num1.length();
        int len2 = num2.length();
        int len = Math.max(len1, len2);
        int[] arr = new int[len * 2];
        int n = 0;
        if (num1.length() < num2.length()) {
            for (int i = len1 - 1; i >= 0; i--) {
                for (int j = len2 - 1; j >= 0; j--) {
                    int a = num1.charAt(i) - '0';
                    int b = num2.charAt(j) - '0';
                    int res = a * b;
                    arr[len + j - n] += res;
                }
                n++;
            }
        } else {
            for (int i = len2 - 1; i >= 0; i--) {
                for (int j = len1 - 1; j >= 0; j--) {
                    int a = num2.charAt(i) - '0';
                    int b = num1.charAt(j) - '0';
                    int res = a * b;
                    arr[len + j - n] += res;
                }
                n++;
            }
        }
        int index = 0;
        while (index < arr.length && arr[index] == 0) {
            index++;
        }
        StringBuilder res = new StringBuilder();
        for (int i = arr.length - 1; i >= index; i--) {
            res.insert(0, arr[i] % 10);
            if (i != index) {
                arr[i - 1] += (arr[i] / 10);
            } else {
                if (arr[i] / 10 != 0) {
                    res.insert(0, arr[i] / 10);
                }
            }
        }
        return res.toString();
    }


    public int reverse(int x) {
        int res = 0;
        try {
            while (x != 0) {
                int now = x % 10;
                res = 10 * res + now;
                x /= 10;
                if (res > Integer.MAX_VALUE / 10) {
                    return 0;
                }
                if (res < Integer.MAX_VALUE / 10) {
                    return 0;
                }
            }
            return res;
        } catch (Exception e) {
            return 0;
        }

    }

    public static void main(String[] args) {
        boolean res = new MayEight().repeatedSubstringPattern("ababba");
        System.out.println(res);
    }

    public boolean repeatedSubstringPattern(String s) {
        for (int i = 1; i <= s.length() / 2; i++) {
            int dd = s.length() % i;
            if (dd == 0) {
                boolean flag = false;
                int index = 0;
                for (int j = 0; j < i; j++) {
                    char thistime = s.charAt(index);
                    for (int k = index; k < s.length(); k += i) {
                        if (s.charAt(k) != thistime) {
                            flag = true;
                        }
                    }
                    index++;
                }
                if (!flag) {
                    return true;
                }
            }
        }
        return false;
    }

    public void reorderList(ListNode head) {
        //链表逆序 然后
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //找到中点
        //断开 逆序后面的
        ListNode secHead = slow.next;
        slow.next = null;
        ListNode head1 = null;
        while (secHead != null) {
            if(secHead.next==null){
                secHead.next =head1;
                head1 =secHead;
                break;
            }
           ListNode next = secHead.next;
           secHead.next=head1;
           head1 = secHead;
           secHead=next;
        }
        //已逆
        //合并 一个是head  一个是和ad
        boolean flag = true;
        ListNode res = new ListNode(-1);
        ListNode res1 = res;
        while (head != null || secHead != null) {
            if (flag) {
                if (head != null) {
                    ListNode tmp = head.next;
                    res.next = head;
                    res = res.next;
                    head.next = null;
                    head = tmp;
                }
                flag = false;
            } else {
                if (head1 != null) {
                    ListNode tmp = head1.next;
                    res.next = head1;
                    res=res.next;
                    head1.next = null;
                    head1 = tmp;
                }
                flag =true;
            }
        }
    }
}
