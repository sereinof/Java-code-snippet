package person.man.findjobs;

import java.util.*;

public class BackTrack {


    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        HashMap<Character, String> map = new HashMap<Character, String>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        StringBuilder tool = new StringBuilder();
        dfs(digits, 0, map, res, tool);

        return res;
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs1(n, k, res, 1, new ArrayList<>(), new boolean[n + 1]);
        return res;
    }

    private void dfs1(int n, int k, List<List<Integer>> res,
                      int i, ArrayList<Integer> list, boolean visited[]) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int j = i; j <= n; j++) {
            if (visited[j]) continue;
            visited[j] = true;
            list.add(j);
            dfs1(n, k, res, i + 1, list, visited);
            list.remove(list.size() - 1);
            visited[j] = false;
        }
    }

    private void dfs(String digits, int i, HashMap<Character, String> map, List<String> res, StringBuilder tool) {
        if (i == digits.length()) {
            res.add(tool.toString());
            return;
        }
        String cur = map.get(digits.charAt(i));

        for (int j = 0; j < cur.length(); j++) {
            char ch = cur.charAt(j);
            tool.append(ch);
            dfs(digits, i + 1, map, res, tool);
            tool.delete(i, i + 1);
        }

    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        dfs2(nums, 0, visited, res, new ArrayList<>());
        return res;
    }

    private void dfs2(int[] nums, int i, boolean[] visited, List<List<Integer>> res, ArrayList<Integer> path) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int j = 0; j < nums.length; j++) {
            if (visited[j]) continue;
            visited[j] = true;
            path.add(nums[j]);
            dfs2(nums, i + 1, visited, res, path);
            path.remove(path.size() - 1);
            visited[j] = false;
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfs3(candidates, target, 0, new ArrayList<>(), res, 0);
        return res;
    }

    private void dfs3(int[] candidates, int target, int sum, ArrayList<Integer> path, List res, int start) {
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (sum > target) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            int num = candidates[i];
            path.add(num);
            dfs3(candidates, target, sum + num, path, res, i);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        BackTrack backTrack = new BackTrack();
        backTrack.totalNQueens(4);
    }

    public int totalNQueens(int n) {
        boolean[] visted = new boolean[n];
        boolean[] sub = new boolean[2 * n];
        boolean[] add = new boolean[2 * n];
        return dfs4(n, 0, visted, sub, add);
    }

    private int dfs4(int n, int i, boolean[] visted, boolean[] sub, boolean[] add) {
        if (i == n) {
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {
            if (visted[j] || sub[i - j + n - 1] || add[i + j]) {
                continue;
            }
            visted[j] = true;
            sub[i - j + n - 1] = true;
            add[i + j] = true;
            res += dfs4(n, i + 1, visted, sub, add);
            visted[j] = false;
            sub[i - j + n - 1] = false;
            add[i + j] = false;
        }
        return res;
    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs5(n, new StringBuilder(), n, n, res);
        return res;
    }

    private void dfs5(int n, StringBuilder help, int i, int rightRemain, List<String> res) {
        if (help.length() == 2 * n) {
            res.add(help.toString());
            return;
        }
        if (i > 0) {
            help.append('(');
            dfs5(n, help, i - 1, rightRemain, res);
            help.deleteCharAt(help.length() - 1);
        }
        if (i > rightRemain) {
            help.append(')');
            dfs5(n, help, i, rightRemain - 1, res);
            help.deleteCharAt(help.length() - 1);
        }
    }

    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if(dfs6(board,0,i,j,word)){
                        return true;
                    };
                }
            }
        }
        return  false;
    }

    private boolean dfs6(char[][] board, int index, int i, int j,String word) {
        if(index==word.length()){
            return  true;
        }
        char re = board[i][j];
        if(word.charAt(index)==board[i][j]){
            if(i-1>=0){
                board[i][i]='#';
                if(dfs6(board,index+1,i-1,j,word)){
                    return true;
                }
                board[i][j]=re;
            }
            if(j-1>=0){
                board[i][i]='#';
                if(dfs6(board,index+1,i,j-1,word)){
                    return  true;
                }
                board[i][j]=re;
            }
            if(i+1<board.length){
                board[i][i]='#';
                if(dfs6(board,index+1,i+1,j,word)){
                    return true;
                }
                board[i][j]=re;
            }
            if(j+1<board[0].length){
                board[i][i]='#';
                if(dfs6(board,index+1,i,j+1,word)){
                    return  true;
                }
                board[i][j]=re;
            }
        }
        return  false;
    }
}
