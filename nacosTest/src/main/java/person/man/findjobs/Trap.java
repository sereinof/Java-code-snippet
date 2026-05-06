package person.man.findjobs;

public class Trap {
    private static int total;

    public static void main(String[] args) {//算法有缺陷 待进一步优化
        int[] heights = new int[]{4, 2, 0, 3, 2, 5};
        int res = trap(heights);
        System.out.printf(res + "");
    }

    public static int trap(int[] height) {
        //思路错了
        //对与每一根柱子 都需要找他左右两边的最大值，然后取最小值才是它实际能到达的高度
// 接雨水 思路 将整体问题转化每一个点所能接住的雨水 。问题就变得简单了
        //
        int total = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int left = height[i];
            int right = height[i];
            int Lpiv = i - 1;
            int Rpiv = i + 1;
            while (Lpiv >= 0) {
                if (height[Lpiv] > left) {
                    left = height[Lpiv];
                }
                Lpiv--;
            }
            ;
            while (Rpiv <= height.length - 1) {
                if (height[Rpiv] > right) {
                    right = height[Rpiv];
                }
                Rpiv++;
            }
            int c = Math.min(left, right) - height[i];
            total += c;
        }
        return total;
    }
}
