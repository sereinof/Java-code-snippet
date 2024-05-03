package person.man.zhouyuqing;

public class LifeGame {
    public static void main(String[] args) {
  int[] x1 = new int[]{1,1};
  int[] x2 = new int[]{1,0};
  int[] x3 = new int[]{1,1,1};
  int[] x4 = new int[]{0,0,0};
  new LifeGame().gameOfLife(new int[][]{x1,x2});
    }

    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int num = queryLives(board, i, j);
                if ((board[i][j] & 1) == 1) {//活
                    if (num < 2 || num > 3) {
                        board[i][j] |= 1;//标记死亡
                    } else {
                        board[i][j] = 3;//11标记复活
                    }
                } else {//死
                    if (num == 3) {
                        board[i][j] = 2;//新状态为活
                    } else {
                        board[i][j] = 0;
                    }
                }

            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
              board[i][j] = board[i][j]>>1;
            }
        }
    }

    public int queryLives(int[][] board, int i, int j) {
        int res = 0;
        int x = board.length;
        int y = board[0].length;
        if (i - 1 >= 0 && j - 1 >= 0) {
            res += board[i - 1][j - 1] & 1;
        }
        if (i - 1 >= 0 && j + 1 < y) {
            res += board[i - 1][j + 1] & 1;
        }
        if (i - 1 >= 0) {
            res += board[i - 1][j] & 1;
        }

        if (j - 1 >= 0) {
            res += board[i][j - 1] & 1;
        }
        if (j + 1 < y) {
            res += board[i][j + 1] & 1;
        }

        //-------
        if (i + 1 < x && j - 1 >= 0) {
            res +=  board[i + 1][j - 1]&1;
        }
        if (i + 1 < x) {
            res += board[i + 1][j]&1;
        }
        if (j + 1 < y && i + 1 < x) {
            res +=board[i + 1][j + 1]&1;
        }
        return res;
    }
}
