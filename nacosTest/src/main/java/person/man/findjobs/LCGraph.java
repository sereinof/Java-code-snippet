package person.man.findjobs;

import java.util.*;

public class LCGraph {
    HashMap<Integer, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Node res = null;
        if (map.containsKey(node.val)) {
            return map.get(node.val);
        } else {
            res = new Node(node.val);
            map.put(node.val, res);
        }
        for (Node node1 : node.neighbors) {
            res.neighbors.add(cloneGraph(node1));
        }
        return res;
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, Integer> idMap = new HashMap<>();
        int id = 0;
        for (List<String> equation : equations) {
            if (!idMap.containsKey(equation.get(0))) {
                idMap.put(equation.get(0), id++);
            }
            if (!idMap.containsKey(equation.get(1))) {
                idMap.put(equation.get(1), id++);
            }
        }
        double[][] dist = new double[id][id];
        for (int i = 0; i < id; i++) {
            dist[i][i] = 1;
        }
        for (int i = 0; i < equations.size(); i++) {
            int a = idMap.get(equations.get(i).get(0));
            int b = idMap.get(equations.get(i).get(1));
            double val = values[i];
            dist[a][b] = val;
            dist[b][a] = 1 / val;
        }
        for (int k = 0; k < id; k++) {
            for (int i = 0; i < id; i++) {
                for (int j = 0; j < id; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String a = queries.get(i).get(0);
            String b = queries.get(i).get(1);
            if (!idMap.containsKey(a) || !idMap.containsKey(b)) {
                res[i] = -1.0;
            } else {
                res[i] = dist[idMap.get(a)][idMap.get(b)];
            }
        }
        return res;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] help = new int[numCourses];
        for (int[] de : prerequisites) {
            help[de[1]]++;
        }
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < help.length; i++) {
            if (help[i] == 0) {
                queue.offer(i);
            }
        }
        int couont = 0;
        while (!queue.isEmpty()) {
            Integer nodeCur = queue.poll();
            couont++;
            List<Integer> neibhors = graph.get(nodeCur);
            for (int i = 0; i < neibhors.size(); i++) {
                if (help[neibhors.get(i)] == 1) {
                    queue.offer(neibhors.get(i));
                    help[neibhors.get(i)]--;
                }
            }
        }
        return couont == numCourses;

    }

    public static void main(String[] args) {
        LCGraph g = new LCGraph();
        String start = "AACCGGTT";
        String end = "AACCGGTA";
        String[] bank = new String[]{"AACCGGTA"};
    }



    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList.size() == 0) return -1;
        if (beginWord.equals(endWord)) {
            return 0;
        }
        HashSet<String> bankSet = new HashSet<>();
        for (int i = 0; i < wordList.size(); i++) {
            bankSet.add(wordList.get(i));
        }
        HashSet<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        if (!bankSet.contains(endWord)) {
            return -1;
        }
        ;
        char[] arr = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        queue.offer(beginWord);
        int times = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                for (int j = 0; j < cur.length(); j++) {
                    StringBuilder next1 = new StringBuilder(cur);
                    char chCur = next1.charAt(j);
                    for (char ch : arr) {
                        if (chCur != ch) {
                            next1.setCharAt(j, ch);
                            if (next1.toString().equals(endWord)) {
                                return times + 1;
                            }
                            if (bankSet.contains(next1.toString()) && !visited.contains(next1.toString())) {
                                queue.offer(next1.toString());
                                visited.add(next1.toString());
                            }
                        }
                    }
                }
            }
            times++;
        }
        return -1;
    }

    public int snakesAndLadders(int[][] board) {
        int res = -1;
        int num = board[0].length * board[0].length;
        Queue<Integer> queue = new ArrayDeque<>();
        Queue<Integer> queueCount = new ArrayDeque<>();
        queue.offer(1);
        queueCount.offer(0);
        if (num <= 7) return 1;
        int curIndex = 1;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int count = queueCount.poll();
            if (cur == num) {
                return count;
            }
            int val = getVal(cur, board);
            for (int i = cur + 1; i <= Math.min(num, cur + 6); i++) {
                val = getVal(i, board);
                if (val == -1) {
                    queue.offer(i);
                    queueCount.offer(count + 1);
                }
                if (val > i) {
                    queue.offer(val);
                    queueCount.offer(count + 1);
                }
            }
        }
        return res;
    }

    public int getVal(int num, int[][] board) {
        int id = board[0].length;
        int n = board[0].length;
        int r = (id - 1) / n, c = (id - 1) % n;
        if (r % 2 == 1) {
            c = n - 1 - c;
        }
        return board[n - 1 - r][c];
    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
