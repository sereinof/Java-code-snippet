package person.man.findjobs;

import java.util.HashMap;
import java.util.Random;

public class RandomizedSet {
    public static Random random = new Random();
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
            index++;
            return true;
        }
    }

    public boolean remove(int val) {
        if (map.containsKey(val)) {
            int indexToVal = map.get(val);
            map.remove(val);
            valList[indexToVal] = valList[--index];
            return true;
        } else {
            return false;
        }
    }

    public int getRandom() {
   return  random.nextInt(index);
    }
}
