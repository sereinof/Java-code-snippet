package person.man.findjobs;

public class Trap {
    public static void main(String[] args) {//算法有缺陷 待进一步优化
        int[] heights = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int res = trap(heights);
        System.out.printf(res + "");
    }

    public static int trap(int[] height) {
// 接雨水 思路 将整体问题转化每一个点所能接住的雨水 。问题就变得简单了
        int total = 0;
        for (int i = 0; i < height.length; i++) {
            int left = 0;
            int right = 0;
            int a = i;//向左
            int b = i;//香油
            while (a > 0) {
                int cand = a-1;
                if (height[cand] >= height[cand+1]) {
                    left = height[cand];
                    a--;
                } else {
                    break;
                }

            }
            while (b < height.length - 1) {
                int cand = b+1;
                if (height[cand] >= height[cand-1]) {
                    right = height[cand];
                    b++;
                } else {
                    break;
int ad =9;
int b= 90;

                }

            }
            int waterReally = Math.min(left, right);
            if(waterReally<=height[i]){
                continue;
            }
            total += (waterReally - height[i]);

        }
        return total;
    }
}
