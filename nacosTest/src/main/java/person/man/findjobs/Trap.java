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
        //超时优化
        int total = 0;
        int[] left = new int[height.length];
        int[] right = new int[height.length];
        int leftMax = height[0];
        left[0] = 0;
        for (int i = 1; i < height.length; i++) {
            left[i] = Math.max(leftMax, height[i - 1]);
            leftMax = left[i];
        }

        int rightMax = height[height.length - 1];
        right[right.length - 1] = 0;
        for (int i = height.length - 2; i >= 0; i--) {
            right[i] = Math.max(height[i + 1], rightMax);
            rightMax = right[i];
        }
        for (int i = 1; i < height.length - 1; i++) {//不遍历边缘的点
            int low = Math.min(left[i], right[i]);
            if (low > height[i]) {
                total += (low-height[i]);
            }
        }
        return total;
    }
}
