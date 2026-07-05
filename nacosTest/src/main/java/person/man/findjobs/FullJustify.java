package person.man.findjobs;

import java.util.ArrayList;
import java.util.List;

public class FullJustify {
    public static void main(String[] args) {
        String[] word = new String[]{"This", "is", "an", "example", "of", "text", "justification."};

        List<String> res = new FullJustify().fullJustify(word, 16);
        System.out.println(res);
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        int l = 0;
        List<String> res = new ArrayList<>();
        while (l != words.length) {
            l = readLine(words, maxWidth, res, l);
        }
        return res;
    }

    private int readLine(String[] words, int maxWidth, List<String> res, int start) {
        List<String> line = new ArrayList<>();
        int lineRemain = maxWidth;
        int nextStart = start;
        for (int i = start; i < words.length; i++) {
            if (i == words.length - 1) {
                line.add(words[i]);
                nextStart = words.length;
                lineRemain -= words[i].length();
                prettyLine(line, lineRemain, true);
                res.add(String.join("", line));
                break;
            } else {
                if (words[i].length() < lineRemain
                        && words[i + 1].length() <= (lineRemain - (words[i].length() + 1))) {
                    line.add(words[i] + ' ');
                    lineRemain -= (words[i].length() + 1);
                } else {
                    line.add(words[i]);
                    lineRemain -= words[i].length();
                    nextStart = i + 1;
                    prettyLine(line, lineRemain, false);
                    res.add(String.join("", line));
                    break;
                }

            }

        }
        return nextStart;
    }

    private void prettyLine(List<String> line, int lineRemain, boolean b) {

        if (b || line.size() == 1) {//尾行
            for (int i = 0; i < lineRemain; i++) {
                line.add(" ");
            }
        } else {
            int index = 0;
            while (lineRemain != 0) {
                line.set(index, line.get(index) + " ");
                lineRemain--;
                if (index == line.size() - 2) {
                    index = 0;
                } else {
                    index++;
                }
            }
        }
    }


}
