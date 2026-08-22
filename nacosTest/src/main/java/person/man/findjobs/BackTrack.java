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

    public static void main(String[] args) {
        BackTrack backTrack= new BackTrack();
        backTrack.permute(new int[]{1,2,3});
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

    }

}
