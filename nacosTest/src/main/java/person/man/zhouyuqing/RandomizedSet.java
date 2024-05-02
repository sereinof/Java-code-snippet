package person.man.zhouyuqing;

import java.util.HashMap;
import java.util.Random;

public class RandomizedSet {
    public int[] arr = new int[200001];
    public HashMap<Integer, Integer> val_index = new HashMap<>();
    int id = -1;
Random random = new Random();
    public RandomizedSet() {

    }

    public boolean insert(int val) {
        if (val_index.containsKey(val)) {
            return false;
        } else {
            val_index.put(val, id++);
            arr[id] = val;
            return true;
        }
    }

    public boolean remove(int val) {
        if (!val_index.containsKey(val)) {
            return false;
        } else {
            int loc = val_index.get(val);
            if (loc != id) {
                arr[loc] = arr[id];
                val_index.remove(val);
                val_index.put(arr[loc], loc);
                id--;
            }else{
                val_index.remove(val);
                id--;
            }
            return true;
        }
    }

    public int getRandom() {
  return   arr[random.nextInt(id+1)];
    }
}