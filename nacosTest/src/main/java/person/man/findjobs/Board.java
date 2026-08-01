package person.man.findjobs;

import java.util.Arrays;
import java.util.HashMap;

public class Board {

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        boolean res = new Board().isValidSudoku(board);
        System.out.println(res);


    }

    //TODO  可以升级使用位运算 以及通过坐标定位到小宫格来进一步提高算法的效率
    public boolean isValidSudoku(char[][] board) {
        int[] map = new int[10];
        int[] map1 = new int[10];
        for (int i = 0; i < 9; i++) {
            Arrays.fill(map, 0);
            Arrays.fill(map1, 0);
            for (int j = 0; j < 9; j++) {
                char val = board[i][j];
                if (val != '.' && map[val - '0'] != 0) {
                    return false;
                }
                if (val != '.') {
                    map[val - '0'] = 1;
                }

                char val1 = board[j][i];
                if (val1 != '.' && map1[val1 - '0'] != 0) {
                    return false;
                }
                if (val1 != '.') {
                    map1[val1 - '0'] = 1;
                }
            }
        }
        //----------
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                Arrays.fill(map, 0);
                for (int k = i; k < i + 3; k++) {
                    for (int l = j; l < j + 3; l++) {
                        char val = board[k][l];
                        if (val != '.' && map[val - '0'] != 0) {
                            return false;
                        }
                        if (val != '.') {
                            map[val - '0'] = 1;
                        }
                    }
                }
            }
        }
        return true;
    }


}
