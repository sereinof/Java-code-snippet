package person.man.zhouyuqing;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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


    public static void main(String[] args) {
        int[] x1 = new int[]{1, 2, 3, 4};
        int[] x2 = new int[]{5, 6, 7, 8};
        int[] x3 = new int[]{9, 10, 11, 12};
        int[][] p = new int[][]{x1, x2, x3};
        List res = new ShuDu().spiralOrder(p);
        System.out.println(res);
    }

    int startX = 0;
    int startY = -1;
    boolean Bflag = true;
    int top = 0;
    int l = 0;
    int r;
    int b;
    ArrayList<Integer> res = new ArrayList<>();

    public List<Integer> spiralOrder(int[][] matrix) {
        b = matrix.length;
        r = matrix[0].length;

        while (Bflag) {
            print(matrix, 0);
            print(matrix, 1);
            print(matrix, 2);
            print(matrix, 3);
        }
        return res;
    }

    public void print(int[][] matrix, int flag) {
        if (!Bflag) {
            return;

        }
        if (flag == 0) {
            while (startY < r - 1) {
                startY++;
                res.add(matrix[startX][startY]);

            }
            top++;
        } else if (flag == 1) {
            while (startX < b - 1) {
                startX++;
                res.add(matrix[startX][startY]);

            }
            r--;
        } else if (flag == 2) {
            while (startY > l) {
                startY--;
                res.add(matrix[startX][startY]);

            }
            b--;
        } else if (flag == 3) {
            while (startX > top) {
                startX--;
                res.add(matrix[startX][startY]);

            }
            l++;
        }
        if (top == b || l == r) {
            Bflag = false;
            return;
        }
    }

    ;

}
