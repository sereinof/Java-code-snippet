package person.man.zhou;


import java.util.*;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class May_19 {
    int min = Integer.MAX_VALUE;
    List<String> res = new ArrayList<>();
    String deleted = "";
    int lenght = 0;

    public static void main(String[] args) {
        //  List<String> res = new May_19().removeInvalidParentheses("()())()");

        // int[] res = maxSlidingWindow(new int[]{1, 3, 1, 2, 0, 5}, 3);
        List<Integer> res = new May_19().findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"});
        System.out.println(res);

    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        if(k==1){
            return head;
        }
        ListNode p = head;
        int len = 0;
        while (p != null) {
            p = p.next;
            len++;
        }
        int times = len / k;
        int exactlytimes = 0;
        ListNode dummy = new ListNode(-1);
        ListNode res = dummy;
        dummy.next = head;
        int count = 1;
        ListNode curPre = head;
        ListNode cur = head.next;
        while (cur != null) {
            ListNode curNext = cur.next;
            cur.next = dummy.next;
            dummy.next = cur;
            curPre.next = curNext;
            cur = curNext;
            count++;
            if (count % k == 0) {
                exactlytimes++;
                count = 1;
                dummy = curPre;
                curPre = cur;
                if(cur==null){
                    break;
                }
                cur = cur.next;
                if(exactlytimes==times){
                    break;
                }
            }
        }
        return res.next;
    }

    public int calculate(String s) {
        int ret = 0;
        Deque<Integer> deque = new ArrayDeque();
        deque.push(1);
        int sign = 1;
        int i = 0;
        int n = s.length();
        while (i < n) {

            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                sign = deque.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -deque.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                deque.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                deque.pop();
                i++;
            } else {
                int num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                ret += sign * num;
            }
        }
        return ret;
    }


    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (words.length == 0) return res;
        HashMap<String, Integer> map = new HashMap();
        int singleWordLen = words[0].length();
        for (String g : words) {
            map.put(g, map.getOrDefault(g, 0) + 1);
        }
        int everyL = words.length * words[0].length();
        for (int i = 0; i < s.length() - everyL + 1; i++) {
            HashMap<String, Integer> mapa = new HashMap<>();
            for (int j = i; j < i + everyL; j += singleWordLen) {
                String key = s.substring(j, j + singleWordLen);
                mapa.put(key, mapa.getOrDefault(key, 0) + 1);
            }
            if (equals_map(map, mapa)) {
                res.add(i);
            }
        }

        return res;
    }

    public boolean equals_map(HashMap<String, Integer> a, HashMap<String, Integer> b) {
        for (Map.Entry<String, Integer> entry : a.entrySet()) {
            String key = entry.getKey();
            int value = a.get(key);
            if (!b.containsKey(key)) {
                return false;
            }
            if (value != b.get(key)) {
                return false;
            }
        }
        return true;
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        int l = 0;
        int r = 0;
        int least = 0;
        int wordL = 0;
        List<String> res = new ArrayList<>();
        while (r < words.length) {
            least--;
            while (r < words.length && least < maxWidth) {
                wordL += words[r].length();
                least += words[r].length();
                least++;
                if (least > maxWidth) {
                    wordL -= words[r].length();
                    break;
                }
                r++;
            }
            r--;
            res.add(produce(r - l + 1, maxWidth, l, r, wordL, words));
            wordL = 0;
            least = 0;
            r++;
            l = r;
        }
        return res;
    }

    public String produce(int n, int maxwidth, int l, int r, int wordL, String[] words) {
        StringBuilder res = new StringBuilder();
        int spaceN = maxwidth - wordL;
        int[] arr = new int[n - 1];
        if (r == words.length - 1) {
            for (int i = 0; i <= r; i++) {
                res.append(words[i]);
                if (i != r) {
                    res.append(" ");
                }
            }
            for (int i = 0; i < maxwidth - res.length(); i++) {
                res.append(" ");
            }
            return res.toString();
        }
        if (n == 1) {
            res.append(words[l]);
            for (int i = 0; i < maxwidth - words[l].length(); i++) {
                res.append(" ");
            }
            return res.toString();
        }
        int everySpace = spaceN / (n - 1);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = everySpace;
        }
        int remain = spaceN % (n - 1);
        for (int i = 0; i < remain; i++) {
            arr[i]++;
        }
        for (int i = 0; i < n - 1; i++) {
            res.append(words[l + i]);
            for (int j = 0; j < arr[i]; j++) {
                res.append(" ");
            }
        }
        res.append(words[r]);
        return res.toString();
    }

    public int candy(int[] ratings) {
        int[] L = new int[ratings.length];
        int[] R = new int[ratings.length];
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                L[i] = L[i - 1] + 1;
            } else {
                L[i] = 1;
            }
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                R[i] = R[i + 1] + 1;
            } else {
                R[i] = 1;
            }
        }
        int res = 0;
        for (int i = 0; i < ratings.length; i++) {
            res += Math.max(R[i], L[i]);
        }
        return res;
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1) {
            return nums;
        }
        Deque<Integer> deque = new ArrayDeque();
        int[] res = new int[nums.length - k + 1];
        int j = 0;
        for (int i = 0; i < k; i++) {
            if (deque.isEmpty()) {
                deque.add(i);
            } else {
                if (nums[deque.getLast()] < nums[i]) {
                    while (!deque.isEmpty() && nums[deque.getLast()] < nums[i]) {
                        deque.pollLast();
                    }
                    deque.add(i);
                } else {
                    deque.add(i);
                }
            }
        }
        res[j++] = nums[deque.getFirst()];
        for (int i = k; i < nums.length; i++) {
            int newl1 = i;
            int needToremove = i - k;
            int dequenL = deque.getFirst();
            if (dequenL == needToremove) {
                deque.pollFirst();
            }
            //队列前部分处理完毕
            if (nums[newl1] > nums[deque.getLast()]) {
                while (!deque.isEmpty() && nums[newl1] > nums[deque.getLast()]) {
                    deque.pollLast();
                }
                deque.add(newl1);
            } else {
                deque.add(newl1);
            }
            res[j++] = nums[deque.getFirst()];
        }
        return res;
    }


    public List<String> removeInvalidParentheses(String s) {
        return BFS(s);
    }

    public List<String> BFS(String s) {
        List<String> rees = new ArrayList<>();
        rees.add("");
        Queue<String> queue = new ArrayDeque<>();
        queue.add(s);
        while (!queue.isEmpty()) {
            Set<String> res = new HashSet<>();
            int size = queue.size();
            HashSet<String> set = new HashSet<>();
            for (int i = 0; i < size; i++) {
                String str = queue.poll();
                set.add(str);
                if (isValid(str)) {
                    res.add(str);
                }
            }
            if (!res.isEmpty()) {
                return res.stream().toList();
            }
            for (String str : set) {
                for (int j = 0; j < str.length(); j++) {
                    if (str.charAt(j) == '(' || str.charAt(j) == ')') {
                        if (j > 0 && str.charAt(j) == s.charAt(j - 1))
                            continue;
                        if (j == str.length() - 1) {
                            queue.add(str.substring(0, j));
                        } else {
                            queue.add(str.substring(0, j) + str.substring(j + 1, str.length()));
                        }
                    }
                }
            }

        }
        return rees;
    }

    public boolean isValid(String str) {
        // 这个方法有问题需要改写
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                cnt++;
            } else if (str.charAt(i) == ')') {
                cnt--;
                if (cnt < 0) {
                    return false;
                }
            }
        }

        return cnt == 0;
    }
}
