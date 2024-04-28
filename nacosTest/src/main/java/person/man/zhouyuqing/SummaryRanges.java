package person.man.zhouyuqing;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {
    public static void main(String[] args) {
        List<String> res = new SummaryRanges().summaryRanges(new int[]{0, 2, 3, 4, 6, 8, 9});
        System.out.println("finish");
    }

    public List<String> summaryRanges(int[] nums) {

        List<String> res = new ArrayList<>(nums.length);
        int point = 0;
        int divid = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                res.add(nums[0] + "");
            } else if (nums[i] == nums[i - 1] + 1) {
                divid++;
                if(i ==nums.length-1){
                    res.set(point, res.get(point) + "->" + nums[i]);
                }
            } else {
                if (divid > 0) {
                    res.set(point, res.get(point) + "->" + nums[i - 1]);
                }
                point++;
                res.add(nums[i] + "");
                divid = 0;
            }

        }
        return res;
    }
}
