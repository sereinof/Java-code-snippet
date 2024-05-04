package person.man.zhouyuqing;

import java.util.Arrays;

public class sortAndMerge {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, (a, b) -> {
            return a[0] > b[0] ? 1 : -1;
        });
        //这玩意是求交集 不是求并集
        int res = 0;
        int bord = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i - 1][1] < points[i][0]) {
                res++;
                bord = points[i][1];
            } else {
                //判断一下这里
                if (points[i][0] <= bord) {
                    bord = Math.min(bord, points[i][1]);
                } else {
                    bord = points[i][1];
                    res++;
                }

            }
        }
        return res;
    }
}
