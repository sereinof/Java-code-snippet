package person.man.findjobs;

import org.checkerframework.checker.units.qual.A;

import java.util.*;

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
        //boolean res = new Board().isValidSudoku(board);
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        // List<Integer> result = new Board().spiralOrder(matrix);

        int[][] arr = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        new Board().rotate(arr);


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

    public List<Integer> spiralOrder(int[][] matrix) {
        int x = matrix[0].length;
        int y = matrix.length;
        List<Integer> res = new ArrayList<>();
        int i = 0;
        int j = 0;
        int[][] directions = {
                {1, 0},
                {0, 1},
                {-1, 0},
                {0, -1}
        };
        int ptr = 0;
        for (int k = 0; k < x * y; k++) {
            int val = matrix[j][i];
            res.add(val);
            matrix[j][i] = 101;
            int[] current = directions[ptr];
            int currentI = i + current[0];
            int currentJ = j + current[1];
            if (currentJ >= y || currentI >= x || currentJ < 0 || currentI < 0 || matrix[currentJ][currentI] == 101) {
                if (ptr == 3) {
                    ptr = 0;
                } else {
                    ptr++;
                }
                current = directions[ptr];
                currentI = i + current[0];
                currentJ = j + current[1];
            }
            i = currentI;
            j = currentJ;

        }
        return res;
    }

    public void rotate(int[][] matrix) {
        int m = matrix.length;
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < m; i++) {
            int l = 0;
            int r = m - 1;
            while (l != r) {
                int temp = matrix[i][l];
                matrix[i][l] = matrix[i][r];
                matrix[i][r] = temp;
                l++;
                r--;
            }
        }
    }

    public void setZeroes(int[][] matrix) {

    }

}
