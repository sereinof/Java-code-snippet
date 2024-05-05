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
        for (int i = 0; i < numCourses; i++) {
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


    public int snakesAndLadders(int[][] board) {
        int n1 = board.length * board[0].length;
        int n = board.length ;
        int[] endCor = id2rc(n1 , n);
        int endP = board[endCor[0]][endCor[1]];
        if (endP < n1 && endP != -1) {
            return -1;//终点有蛇
        }
        boolean[] viisted = new boolean[n1 + 1];//记录是否访问过
        Queue<Integer> queue = new ArrayDeque();
        queue.offer(1);
        int times = 0;
        viisted[1] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            times++;
            for (int i = 0; i < size; i++) {
                int posiotn = queue.poll();
                for (int j = 1; j <= 6; j++) {
                    int[] rc = id2rc(posiotn + j, n);
                    int nxt = posiotn + j;
                    if(nxt>n1){
                        break;
                    }
                    if (posiotn + j >= n1) {
                        return times;
                    }
                    int t = board[rc[0]][rc[1]];
                    if (t == -1) {
                    } else {
                        if (t == n1) {
                            return times;
                        }
                        nxt = t;
                    }
                    //还要判断当前的节点是否为-1 不然还多了一个可能
                    if (viisted[nxt] == false) {
                        viisted[posiotn] = true;
                        queue.offer(nxt);
                    }
                }

            }


        }
        return -1;

    }

    public int[] id2rc(int id, int n) {
        int r = (id - 1) / n, c = (id - 1) % n;
        if (r % 2 == 1) {
            c = n - 1 - c;
        }
        return new int[]{n - 1 - r, c};
    }

}
