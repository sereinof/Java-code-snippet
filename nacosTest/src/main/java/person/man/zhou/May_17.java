package person.man.zhou;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class May_17 {
    public static void main(String[] args) {
        permuteUnique(new int[]{1, 2, 3});
    }

    static List<List<Integer>> res = new ArrayList<>();
    static boolean used[];
    static ArrayList<Integer> every = new ArrayList<>();

    public static List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        used = new boolean[nums.length];
        backtracking(nums, 0);
        return res;
    }

    public static void backtracking(int[] nums, int level) {
        if (level == nums.length) {
            List<Integer> thisTurn = new ArrayList<>();
            every.stream().forEach((a) -> {
                thisTurn.add(a);
            });
            res.add(thisTurn);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) continue;
                every.add(nums[i]);
                used[i] = true;
                backtracking(nums, level + 1);
                every.remove(every.size() - 1);
                used[i] = false;
            }
        }
    }
}
