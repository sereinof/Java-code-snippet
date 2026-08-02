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
        //int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        // List<Integer> result = new Board().spiralOrder(matrix);

        int[][] arr = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] matrix = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        new Board().gameOfLife(matrix);


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
        int n = matrix[0].length;
        int m = matrix.length;
        boolean isFirstRowZero = false;
        boolean isFirstColumZero = false;
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0) {
                isFirstRowZero = true;
            }
        }
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                isFirstColumZero = true;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }

            }
        }
        if (isFirstRowZero) {
            Arrays.fill(matrix[0], 0);
        }
        if (isFirstColumZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }


    }

    public void gameOfLife(int[][] board) {
        int row = board.length;
        int colum = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colum; j++) {
                int liveCellCount = 0;

                if (i - 1 >= 0) {
                    liveCellCount += make(i - 1, j, board);
                    if (j - 1 >= 0) {
                        liveCellCount += make(i - 1, j - 1, board);

                    }
                    if (j + 1 < colum) {
                        liveCellCount += make(i - 1, j + 1, board);

                    }
                }
                if (j - 1 >= 0) {
                    liveCellCount += make(i, j - 1, board);
                }
                if (j + 1 < colum) {
                    liveCellCount += make(i, j + 1, board);

                }
                if (i + 1 < row) {
                    liveCellCount += make(i + 1, j, board);
                    if (j - 1 >= 0) {
                        liveCellCount += make(i + 1, j - 1, board);
                    }
                    if (j + 1 < colum) {
                        liveCellCount += make(i + 1, j + 1, board);
                    }
                }
                if (liveCellCount < 2 || liveCellCount > 3) {

                } else if (liveCellCount == 3 && (board[i][j] & 1) == 0) {
                    board[i][j] |= 2;
//复活
                } else {
                    if((board[i][j] & 1) ==1){
                        board[i][j] |= 2;
                    }

                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colum; j++) {
                board[i][j] >>= 1;
            }
        }
    }

    private int make(int i, int j, int[][] board) {
        return board[i][j] & 1;
    }



}
