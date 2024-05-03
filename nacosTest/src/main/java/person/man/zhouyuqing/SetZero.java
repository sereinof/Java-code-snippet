package person.man.zhouyuqing;

public class SetZero {
    public static void main(String[] args) {
        int[] x1 = new int[]{0,1,2,0};
        int[] x2 = new int[]{3,4,5,2};
        int[] x3 = new int[]{1,3,1,5};
        int[] x4 = new int[]{13,14,15,0};

        new SetZero().setZeroes(new int[][]{x1,x2,x3});
    }
    public void setZeroes(int[][] matrix) {
        if(matrix.length==0||matrix[0].length==0){
            return;
        }
        boolean markx = false;
        boolean marky =false;
        for(int i =0 ;i<matrix.length;i++){
            if(matrix[i][0]==0){
                markx=true;
                break;
            }
        }
        for(int i =0 ;i<matrix[0].length;i++){
            if(matrix[0][i]==0){
                marky=true;
                break;
            }
        }
        for(int i=1;i<matrix.length;i++){
            for (int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]==0){
                    matrix[i][0] = 0;
                    matrix[0][j]=0;
                }
            }
        }
        for(int i=1;i<matrix.length;i++){
            if(matrix[i][0]==0){
                for (int j=0;j<matrix[0].length;j++){
                   matrix[i][j] = 0;
                }
            }
        }
        for(int i=1;i<matrix[0].length;i++){
            if(matrix[0][i]==0){
                for (int j=0;j<matrix.length;j++){
                    matrix[j][i] = 0;
                }
            }
        }

        if (markx) {
            for (int i = 0; i < matrix.length; i++) {
               matrix[i][0]=0;
            }
        }
        if(marky){
            for(int i =0 ;i<matrix[0].length;i++){
                matrix[0][i]=0;
            }
        }

    }
}
