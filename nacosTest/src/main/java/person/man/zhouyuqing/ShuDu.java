package person.man.zhouyuqing;

import java.util.List;

public class ShuDu {
    public boolean isValidSudoku(char[][] board) {
        int[][] xMap = new int[9][10];//   为啥是10？ 因为零下标没有意义
        int[][] yMap = new int[9][10];
        int[][] boxMap = new int[9][10];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                char num = board[i][j];
                if (num == ',') {
                    continue;
                } else {
                    int n = num - '0';
                    if (xMap[i][n] > 0) {
                        return false;
                    }
                    if (yMap[j][n] > 0) {
                        return false;
                    }
                    int[] ints = boxMap[i / 3 + (j / 3) * 3];
                    if (ints[n] > 0) {
                        return false;
                    }
                    xMap[i][n]++;
                    yMap[j][n]++;

                    ints[n]++;
                }
            }
        }
        return true;
    }
    public List<Integer> spiralOrder(int[][] matrix) {

    }

}
