package person.man.zhouyuqing;

import java.util.HashMap;
import java.util.HashSet;

public class WordDictionary {
    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        boolean res = wordDictionary.search("pad");
        boolean res1 = wordDictionary.search("bad");
        boolean res2 = wordDictionary.search(".ad");
        boolean res3 = wordDictionary.search("b..");
        System.out.println("this is test!");
    }

    W root;

    public WordDictionary() {
        this.root = new W();
    }

    public void addWord(String word) {
        W p = root;
        for (int i = 0; i < word.length(); i++) {
            char s = word.charAt(i);

            if (!p.nodes.containsKey(s)) {
                W n = new W(s);
                p.nodes.put(s, n);
                if (i == word.length() - 1) {
                    n.endFlag = true;
                }
                p = n;
            } else {
                p = p.nodes.get(s);
                if (i == word.length() - 1) {
                    p.endFlag = true;
                }
            }

        }
    }

    public boolean search(String word) {
        return dosearch(word, 0, word.length() - 1, root);
    }

    public boolean dosearch(String word, int index1, int index2, W w) {
        if (index1 > index2) {
            if (w.nodes.isEmpty()||w.endFlag==true) {
                return true;
            } else {
                return false;
            }

        }
        if (word.charAt(index1) == '.') {
            boolean flag = false;
            for (char i = 'a'; i <= 'z'; i++) {
                if (w.nodes.containsKey(i)) {
                    if (dosearch(word, index1 + 1, index2, w.nodes.get(i))) {
                        flag = true;
                    }
                }
            }
            return flag;
        } else {
            char s = word.charAt(index1);
            if (w.nodes.containsKey(s)) {
                return dosearch(word, index1 + 1, index2, w.nodes.get(s));
            } else {
                return false;
            }

        }
    }

    class W {
        char val;
        HashMap<Character, W> nodes = new HashMap<>();
        boolean endFlag = false;

        public W() {

        }

        public W(char val) {
            this.val = val;
        }
    }
}
