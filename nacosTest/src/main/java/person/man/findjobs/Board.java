package person.man.findjobs;

import java.util.Arrays;
import java.util.HashMap;

public class Board {

    public boolean isValidSudoku(char[][] board) {
        boolean res = true;
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
        Arrays.fill(map, 0);
        //----------
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                for (int k = i; k < k + 3; k++) {
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


            return res;
        }
    }
