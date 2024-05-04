package person.man.zhouyuqing;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class Insert {
    public static void main(String[] args) {
        int[] x1 = new int[]{1, 5};

        new Insert().insert(new int[][]{x1}, new int[]{6, 8});
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int p = 0;
        boolean flag =false;
        if (intervals.length == 0) {
            return new int[][]{newInterval};
        }
        ArrayList res = new ArrayList<>();
        while (p < intervals.length) {
            if (intervals[p][1] < newInterval[0]) {
                res.add(intervals[p]);
                p++;
            } else if (intervals[p][1] >= newInterval[0]) {
                int[] now = new int[2];
                if (intervals[p][0] <= newInterval[0]) {
                    now[0] = intervals[p][0];
                    flag = true;
                } else {
                    now[0] = newInterval[0];
                    flag =true;
                }
                while (p < intervals.length) {
                    if (intervals[p][1] < newInterval[1]) {
                        now[1] = newInterval[1];
                        p++;
                    } else if (newInterval[1] <= intervals[p][1] && newInterval[1] >= intervals[p][0]) {
                        now[1] = intervals[p][1];
                        p++;
                        break;
                    } else if (newInterval[1] <= intervals[p][0]) {
                        now[1] = newInterval[1];
                        break;
                    }
                }
                res.add(now);
                break;
            }
        }
        for (int i = p; i < intervals.length; i++) {
            res.add(intervals[i]);
        }
        if(!flag){
            res.add(newInterval);
        }
        int[][] res1 = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            res1[i] = (int[]) res.get(i);
        }

        return res1;
    }
}

