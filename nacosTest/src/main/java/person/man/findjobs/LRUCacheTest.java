package person.man.findjobs;
import java.util.*;

public class LRUCacheTest {
    public static void main(String[] args) {
        // 操作序列
        String[] actions = {"LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"};
        // 对应的参数，每个元素是一个数组
        Integer[][] params = {
                {2},           // LRUCache 初始化容量
                {1, 0},        // put(1, 0)
                {2, 2},        // put(2, 2)
                {1},           // get(1)
                {3, 3},        // put(3, 3)
                {2},           // get(2)
                {4, 4},        // put(4, 4)
                {1},           // get(1)
                {3},           // get(3)
                {4}            // get(4)
        };

        // 预期输出（对照力扣示例）
        Object[] expected = {null, null, null, 0, null, -1, null, -1, 3, 4};

        // 实际输出列表
        List<Object> actual = new ArrayList<>();

        LRUCache cache = null;

        for (int i = 0; i < actions.length; i++) {
            String action = actions[i];
            Integer[] p = params[i];
            switch (action) {
                case "LRUCache":
                    cache = new LRUCache(p[0]);
                    actual.add(null);
                    break;
                case "put":
                    cache.put(p[0], p[1]);
                    actual.add(null);
                    break;
                case "get":
                    int val = cache.get(p[0]);
                    actual.add(val);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown action: " + action);
            }
        }

        // 打印实际结果
        System.out.println("Actual output:   " + actual);
        System.out.println("Expected output: " + Arrays.toString(expected));

        // 验证是否一致
        boolean pass = actual.equals(Arrays.asList(expected));
        System.out.println("Test " + (pass ? "✅ PASSED" : "❌ FAILED"));
    }
}
