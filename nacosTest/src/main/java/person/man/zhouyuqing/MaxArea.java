package person.man.zhouyuqing;

public class MaxArea {
    public static void main(String[] args) {

    }

    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int x = matrix.length;
        int y = matrix[0].length;
        int [][]dp = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if(matrix[i][j]=='1'){
                    if(i==0||j==0){
                      dp[i][j] = 1;
                    }else {
                        dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1])) +1;
                        maxSide = Math.max(dp[i][j],maxSide);
                    }

                }


            }
        }


        return maxSide * maxSide;
    }
}
