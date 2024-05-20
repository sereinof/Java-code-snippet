package person.man.zhou;

import java.util.HashMap;
import java.util.PriorityQueue;

public class May_20 {
    public static void main(String[] args) {
        System.out.println(gcd(12, 24));
        PriorityQueue a = new PriorityQueue();
    }

    public int maxPoints(int[][] points) {
        int res = 1;
        for (int i = 0; i < points.length; i++) {
            HashMap<String, Integer> map = new HashMap();
            for (int j = i + 1; j < points.length; j++) {
                int dx = points[i][0] - points[j][0];
                int dy = points[i][1] - points[j][1];
                int gcd = gcd(Math.abs(dx), Math.abs(dy));
                dx = dx / gcd;
                dy = dy / gcd;
                String sign = "+";
                if (dx * dy < 0) {
                    sign = "-";
                }
                String key = sign + Math.abs(dx) + "_" + Math.abs(dy);
                map.put(key, map.getOrDefault(key, 0) + 1);
                res = Math.max(res, map.get(key) + 1);
            }
        }
        return res;
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
