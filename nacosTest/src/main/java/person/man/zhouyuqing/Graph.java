package person.man.zhouyuqing;

import java.util.*;

public class Graph {
    public HashMap<Node, Node> map = new HashMap();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        if (map.containsKey(node)) {
            return map.get(node);
        }
        Node newly = new Node(node.val);
        map.put(node, newly);
        for (Node n : node.neighbors) {
            newly.neighbors.add(cloneGraph(n));
        }
        return newly;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<Integer> inN = new ArrayList<>(numCourses);
        ArrayList<List<Integer>> pointer = new ArrayList<>(numCourses);
        for(int i =0;i<numCourses;i++){
            inN.add(0);
            pointer.add(new ArrayList<>());
        }
        ArrayList<Integer> res = new ArrayList<>();
        for (int[] info : prerequisites) {
            inN.set(info[0], inN.get(info[0]) + 1);//构建入度信息
            pointer.get(info[1]).add(info[0]);//构建指向信息
        }
        Queue<Integer> queue = new ArrayDeque();
        for (int i = 0; i < numCourses; i++) {
            if (inN.get(i) == 0) {
                queue.offer(i);//将入度为0 的推入队列中
            }
        }
        while (!queue.isEmpty()) {//广度优先遍历？
            int course = queue.poll();
            res.add(course);
            for (int i : pointer.get(course)) {
                if (inN.get(i) == 1) {//入度变成0了 加入队列
                    inN.set(i, 0);
                    queue.offer(i);
                } else {
                    inN.set(i, inN.get(i) - 1);
                }
            }
        }
        int[] Res = new int[numCourses];
        if (res.size() == numCourses) {
            for (int i = 0; i < numCourses; i++) {
                Res[i] = res.get(i);
            }
            return Res;
        } else {
            return new int[]{};
        }
    }
}
