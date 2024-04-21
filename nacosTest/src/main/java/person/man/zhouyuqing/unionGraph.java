package person.man.zhouyuqing;

import java.util.HashMap;
import java.util.List;

public class unionGraph {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, Integer> map = new HashMap();
        UninFind uninFind = new UninFind(equations.size() * 2);
        int id = 0;
        for (int j = 0; j < equations.size(); j++) {
            String var1 = equations.get(j).get(0);
            String var2 = equations.get(j).get(1);

            if (map.get(var1) == null) {
                map.put(var1, id);
                id++;
            }
            if (map.get(var2) == null) {
                map.put(var2, id);
                id++;
            }
            uninFind.union(map.get(var1), map.get(var2), values[j]);
        }
        int queriseSize = queries.size();
        double[] res = new double[queriseSize];
        for (int i = 0; i < queriseSize; i++) {
            String var1 = queries.get(i).get(0);
            String var2 = queries.get(i).get(0);
            Integer id1 = map.get(var1);
            Integer id2 = map.get(var2);
            if (id1 == null || id2 == null) {
                res[i] = -1.0d;
            } else {
                res[i] = uninFind.isConnected(id1, id2);
            }

        }
        return res;
    }

    class UninFind {
        int[] parent;
        double[] weight; //每个节点其实指向该集合的父节点

        UninFind(int size) {
            parent = new int[size];
            weight = new double[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;//父亲是自己
                weight[i] = 1.0d;//权重默认是1
            }
        }

        //这个应该是有方向的 醉了
        public void union(int x, int y, double val) {
            int rooX = find(x);
            int rooY = find(y);
            if (rooX == rooY) {
                return;
            }
            parent[x] = rooY;
            weight[rooX] = weight[y] * val / weight[x];
        }

        public int find(int x) {
            if (x != parent[x]) {
                //这里处理路径压缩
                int orign = parent[x];
                parent[x] = find(parent[x]);
                //解释下 就当前节点的weight的
                weight[x] = x * weight[orign];
            }
            return parent[x];
        }

        public double isConnected(int x, int y) {
            int rooX = find(x);
            int rooY = find(y);
            if (rooX == rooY) {

                return weight[x] / weight[y];
            } else {
                return -1.0d;
            }
        }
    }
}

