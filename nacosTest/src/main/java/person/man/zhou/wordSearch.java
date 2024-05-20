package person.man.zhou;

import java.util.*;

public class wordSearch {



    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int[][] info = new int[profits.length][2];
        for (int i = 0; i < profits.length; i++) {
            info[i][0] = profits[i];
            info[i][1] = capital[i];
        }
        boolean[] dealede = new boolean[profits.length];
        Arrays.sort(info, (a, b) -> {
            if (a[1] == b[1]) {
                return b[0] - a[0];
            } else {
                return a[1] - b[1];
            }
        });
        int index = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> {
            return y - x;//使用大根堆
        });
        while (k > 0) {//每次在可以买的里面选择最大那个买
            while (index < profits.length && w >= info[index][1]) {
                pq.add(info[index][0]);
                index++;
            }
            if (!pq.isEmpty()) {
                w += pq.poll();
                k--;
            } else {
                break;
            }
        }
        return w;
    }

    StringBuilder string = new StringBuilder();
    Trie trie = new Trie();
    Set<String> res = new HashSet<>();
    int[][] dirtions = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    int x = 0;
    int y = 0;

    public List<String> findWords(char[][] board, String[] words) {
        if (board.length == 0 || board[0].length == 0) {
            return new ArrayList<String>(res);
        }
        for (int i = 0; i < words.length; i++) {
            trie.insert(words[i]);
        }
        x = board.length;
        y = board[0].length;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                string.append(board[i][j]);
                if (trie.startsWith(string.toString())) {
                    dfs(i, j, board);
                }
                string.deleteCharAt(string.length() - 1);
            }
        }
        return new ArrayList<String>(res);
    }

    public void dfs(int i, int j, char[][] board) {
        if (trie.search(string.toString())) {
            res.add(string.toString());
            // return;
        }
        char ch = board[i][j];
        board[i][j] = '#';
        for (int p = 0; p < dirtions.length; p++) {
            int newx = i + dirtions[p][0];
            int newy = j + dirtions[p][1];
            if (newx < 0 || newy < 0 || newx >= x || newy >= y) continue;
            string.append(board[newx][newy]);
            if (trie.startsWith(string.toString())) {
                dfs(newx, newy, board);
            }
            string.deleteCharAt(string.length() - 1);
        }
        board[i][j] = ch;
    }
}


class Trie {
    private Trie[] children;
    private boolean isEnd;

    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }

    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private Trie searchPrefix(String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }
}
