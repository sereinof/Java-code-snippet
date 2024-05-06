package person.man.zhouyuqing;

import org.checkerframework.checker.units.qual.A;

import java.util.*;

public class HuiSuo {
    List<List<Integer>> res = new ArrayList<>();
    int k;
    LinkedList<Integer> s = new LinkedList<Integer>();


    public List<List<Integer>> combine(int n, int k) {
        this.k = k;
        backtracking1(n - 1, s);//
        s.push(n);
        backtracking1(n - 1, s);
        s.pop();

        return res;
    }

    public void backtracking1(int n, LinkedList<Integer> g) {
        if (s.size() == k) {
            List<Integer> reV = new ArrayList<>();
            for (int i = 0; i < s.size(); i++) {
                reV.add(g.get(i));
            }
            res.add(reV);
            return;
        }
        if (n == 0) {
            return;
        }

        backtracking1(n - 1, s);
        s.push(n);
        backtracking1(n - 1, s);
        s.pop();

    }

    public static void main(String[] args) {
        HuiSuo a = new HuiSuo();
       List res = a.solveNQueens(4);
        System.out.println("finish test!");
    }

    int target;
    List<List<String>> res1  =new ArrayList<>();
    HashSet<Integer> x = new HashSet<>();
    HashSet<Integer> y = new HashSet<>();
    HashSet<Integer> z = new HashSet<>();

    public List<List<String>> solveNQueens(int n) {
        target = n;
        LinkedList arr = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row,'.');
            row[i] = 'Q';
            String add = new String(row);
            arr.push(add);
            mark(0, i);
            backtracking(n - 1, arr);
            unmark(0, i);
            arr.pop();
        }
        return res1;
    }

    public void backtracking(int n, LinkedList<String> s) {
        if (s.size() == target) {
            ArrayList<String> candi = new ArrayList<>();
            for(String q:s){
                candi.add(q);
            }
            res1.add(candi);
            return;
        }
        if (n <= 0) {
            return;
        }
        for (int i = 0; i < target; i++) {
            if (aviable(s.size(), i)) {
                char[] row = new char[target];
                Arrays.fill(row,'.');
                row[i] = 'Q';
                String add = new String(row);
                s.push(add);
                mark(s.size() - 1, i);
                backtracking(n - 1, s);
                unmark(s.size() - 1, i);
                s.pop();
            }
        }
    }

    public void mark(int x1, int x2) {//标记皇后的位置
        x.add(x2);
        int lineL = x2 - x1;
        y.add(lineL);
        int lineR = x1 + x2;
        z.add(lineR);
    }

    public boolean aviable(int x1, int x2) {
        int lineL = x2 - x1;
        int lineR = x1 + x2;
        return !x.contains(x2) && !y.contains(lineL) && !z.contains(lineR);
    }

    public void unmark(int x1, int x2) {
        x.remove(x2);
        int lineL = x2 - x1;
        int lineR = x1 + x2;
        y.remove(lineL);
        z.remove(lineR);
    }

}
