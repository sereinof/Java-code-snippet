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
        int n = board.length;
        int[] endCor = id2rc(n1, n);
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
                    if (nxt > n1) {
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

    public int minMutation(String startGene, String endGene, String[] bank) {
        char[] can = new char[]{'A', 'C', 'G', 'T'};
        HashSet set = new HashSet<>();
        HashSet visited = new HashSet<>();
        for (String i : bank) {
            set.add(i);
        }
        if (startGene.equals(endGene)) {
            return 0;
        }
        if (!set.contains(endGene)) {
            return -1;
        }
        Queue<String> queue = new ArrayDeque();
        queue.offer(startGene);
        visited.add(startGene);
        int steps = 0;
        while (!queue.isEmpty()) {
            steps++;
            int size = queue.size();
            for (int q = 0; q < size; q++) {
                String now = queue.poll();
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (now.charAt(i) != can[j]) {
                            StringBuilder next = new StringBuilder(now);
                            next.setCharAt(i, can[j]);
                            String nextR = next.toString();

                            //变化后的基因必须位于基因库中
                            if (set.contains(nextR)) {
                                if (nextR.equals(endGene)) {
                                    return steps;
                                } else {
                                    if (!visited.contains(nextR)) {
                                        queue.offer(nextR);
                                        visited.add(nextR);
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList();
        arr.add("most");
        arr.add("mist");
        arr.add("miss");
        arr.add("lost");
        arr.add("fist");
        arr.add("fish");
        new Graph().ladderLength("lost", "miss", arr);
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        HashSet set = new HashSet<>(wordList);
        HashSet visited = new HashSet<>();

        if (beginWord.equals(endWord)) {
            return 0;
        }
        if (set.size() == 0 || !set.contains(endWord)) {
            return 0;
        }
        set.remove(beginWord);
        Queue<String> queue = new ArrayDeque();
        queue.offer(beginWord);
        visited.add(beginWord);
        int steps = 0;
        while (!queue.isEmpty()) {
            steps++;
            int size = queue.size();
            for (int q = 0; q < size; q++) {
                String now = queue.poll();
                for (int i = 0; i < now.length(); i++) {
                    for (char j = 'a'; j <= 'z'; j++) {
                        if (now.charAt(i) != j) {
                            StringBuilder next = new StringBuilder(now);
                            next.setCharAt(i, j);
                            String nextR = next.toString();
                            if (set.contains(nextR)) {
                                if (nextR.equals(endWord)) {
                                    return steps + 1;
                                } else {
                                    if (!visited.contains(nextR)) {
                                        queue.offer(nextR);
                                        visited.add(nextR);
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
        return 0;
    }

    public int openLock(String[] deadends, String target) {
        String beginWord = "0000";
        HashSet set = new HashSet<String>();
        HashSet visited = new HashSet<>();
        for (String i : deadends) {
            set.add(i);
        }
        if (beginWord.equals(target)) {
            return -1;
        }
        if(set.contains(beginWord)){
            return -1;
        }
        if (set.size() == 0 || set.contains(target)) {
            return -1;
        }
        set.remove(beginWord);
        Queue<String> queue = new ArrayDeque();
        queue.offer(beginWord);
        visited.add(beginWord);
        int steps = 0;
        while (!queue.isEmpty()) {
            steps++;
            int size = queue.size();
            for (int q = 0; q < size; q++) {
                String now = queue.poll();
                for (int i = 0; i < 4; i++) {
                    //

                    char[] nexts = new char[2];
                    if (now.charAt(i) == '0') {
                        nexts[0] = '9';
                        nexts[1] = '1';
                    } else if (now.charAt(i) == '9') {
                        nexts[0] = '0';
                        nexts[1] = '8';
                    }else{
                        nexts[0] = (char) (now.charAt(i)-1);
                        nexts[1] = (char) (now.charAt(i)+1);
                    }
                    for (char j:nexts) {
                            StringBuilder next = new StringBuilder(now);
                            next.setCharAt(i, j);
                            String nextR = next.toString();
                            if (!set.contains(nextR)) {
                                if (nextR.equals(target)) {
                                    return steps + 1;
                                } else {
                                    if (!visited.contains(nextR)) {
                                        queue.offer(nextR);
                                        visited.add(nextR);
                                    }
                                }
                        }
                    }
                }
            }
        }

        return -1;
    }

}
