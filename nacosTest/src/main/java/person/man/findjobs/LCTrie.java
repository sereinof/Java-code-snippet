package person.man.findjobs;

import java.util.*;

public class LCTrie {
    Node root;

    public LCTrie() {
        this.root = new Node();
    }

    Set<String> res = new HashSet<>();
    public List<String> findWords(char[][] board, String[] words) {

        LCTrie trie = new LCTrie();
        for (int i = 0; i < words.length; i++) {
            trie.insert(words[i]);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(trie, i, j, board, new StringBuilder().append(board[i][j]));
            }
        }
        return new ArrayList<>(res);
    }
    private void dfs(LCTrie trie, int i, int j, char[][] board, StringBuilder stringBuilder) {
        String ans = stringBuilder.toString();
        if (trie.search(ans)) {
            res.add(ans);
        }
        if (trie.startsWith(ans)) {

            if (i - 1 >= 0) {
                stringBuilder.append(board[i - 1][j]);
                dfs(trie, i - 1, j, board, stringBuilder);
                stringBuilder.delete(ans.length(), ans.length() + 1);
            }
            if (j - 1 >= 0) {
                stringBuilder.append(board[j][j - 1]);
                dfs(trie, i, j - 1, board, stringBuilder);
                stringBuilder.delete(ans.length(), ans.length() + 1);
            }
            if (i + 1 < board.length) {
                stringBuilder.append(board[i + 1][j]);
                dfs(trie, i + 1, j, board, stringBuilder);
                stringBuilder.delete(ans.length(), ans.length() + 1);
            }
            if (j + 1 < board[0].length) {
                stringBuilder.append(board[i][j + 1]);
                dfs(trie, i, j + 1, board, stringBuilder);
                stringBuilder.delete(ans.length(), ans.length() + 1);
            }
        }


    }


    public void insert(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (cur.next[index] == null) {
                cur.next[index] = new Node();
            }
            Node next = cur.next[index];
            next.path++;
            cur = next;
        }
        cur.end++;
    }

    public boolean search(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (cur.next[index] == null) return false;
            cur = cur.next[index];
        }
        return cur.end != 0;
    }

    public boolean startsWith(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (cur.next[index] == null) return false;
            cur = cur.next[index];
        }
        return true;
    }

    class Node {
        Node[] next;
        int path;
        int end;

        public Node() {
            next = new Node[26];
        }
    }


    public boolean search1(String word) {//word里可能出现点通配符
        if (root.path == 0) return false;
        Stack<Object[]> stack = new Stack<>();
        stack.push(new Object[]{root, 0});
        while (!stack.isEmpty()) {
            Object[] cur = stack.pop();
            int j = (Integer) cur[1];
            Node noeCur = (Node) cur[0];
            if (j == word.length() && noeCur != null) return true;
            if (noeCur == null) continue;
            char ch = word.charAt(j);
            if (ch == '.') {
                for (int i = 0; i < 26; i++) {
                    Node next = noeCur.next[i];
                    if (next != null) {
                        Object[] item = new Object[]{next, j + 1};
                        stack.push(item);
                    }
                }
            } else {
                Object[] item = new Object[]{noeCur.next[ch - 'a'], j + 1};
                stack.push(item);
            }
        }
        return false;
    }
}
