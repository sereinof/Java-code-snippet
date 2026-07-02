package person.man.findjobs;

import java.util.HashMap;
import java.util.Random;

public class RandomizedSet {
    private Random random = new Random();
    private int[] valList = new int[2000001];
    private HashMap<Integer, Integer> map = new HashMap<>();
    private int index = 0;

    public RandomizedSet() {

    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        } else {
            map.put(val, index);
            valList[index] = val;
            index++;
            return true;
        }
    }

    public boolean remove(int val) {//这块还是需要注意
        if (map.containsKey(val)) {
            int indexToVal = map.get(val);
            if (indexToVal == index - 1) {
                map.remove(val);
                index--;
                return true;
            }
            map.remove(val);
            valList[indexToVal] = valList[index - 1];
            map.put(valList[index - 1], indexToVal);
            valList[index - 1] = 0;
            index--;
            return true;
        } else {
            return false;
        }
    }

    public int getRandom() {
        return valList[random.nextInt(index)];
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int currentmax = nums[0];
        int glboblmax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currentmax = Math.max(nums[i], currentmax + nums[i]);
            glboblmax = Math.max(currentmax, glboblmax);
        }
        return glboblmax;
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas.length == 0 || gas == null) {
            return -1;
        }
        int n = gas.length;
        int currentSum = 0;
        int totalSum = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            currentSum += (gas[i] - cost[i]);
            totalSum += (gas[i] - cost[i]);
            if (currentSum < 0) {
                currentSum = 0;
                start = i + 1;
            }
        }
        if (totalSum < 0) {
            return -1;
        }
        return start;
    }
}
