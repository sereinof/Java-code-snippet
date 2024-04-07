package person.man.zhouyuqing;

import java.util.HashMap;

public class exist {
    public boolean flag = false;
    public static void main(String[] args) {
        char[] a1 = new char[]{'a','a'};
        char[][] board  =  new char[][]{a1,};
        System.out.println( new exist().exist(board,"aaa"));
    }


    public boolean exist(char[][] board, String word) {
        int x = board.length;
        int y = board[0].length;
        HashMap hashMap = new HashMap<>();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
               ifOrnot(i, j, "", board, 0, word, "",hashMap);
               hashMap.clear();
            }
        }
        return flag;
    }

    public void ifOrnot(int x, int y, String from, char[][] board, int level, String word, String msg, HashMap<String,String> grow) {

        if (word.charAt(level) == board[x][y]) {
            if (level == word.length() - 1) {
                flag = true;
                return ;
            } else {
                from = from + board[x][y];
                grow.put(String.valueOf(x)+String.valueOf(y),"exist");
                if (x - 1 >= 0&&msg!="top"&&!grow.containsKey(String.valueOf(x-1)+String.valueOf(y))) {
                    ifOrnot(x - 1, y, from, board, level + 1, word,"bottom",grow);
                }
                if (y - 1 >= 0&&msg!="left"&&!grow.containsKey(String.valueOf(x)+String.valueOf(y-1))) {
                    ifOrnot(x, y - 1, from, board, level + 1, word,"right",grow);
                }
                if (x + 1 < board.length&&msg!="bottom"&&!grow.containsKey(String.valueOf(x+1)+String.valueOf(y))) {
                    ifOrnot(x + 1, y, from, board, level + 1, word,"top",grow);
                }
                if (y + 1 < board[0].length&&msg!="right"&&!grow.containsKey(String.valueOf(x)+String.valueOf(y+1))) {
                    ifOrnot(x, y + 1, from, board, level + 1, word,"left",grow);
                }
                grow.remove(String.valueOf(x)+String.valueOf(y));

            }
        } else {

        }


    }
}
