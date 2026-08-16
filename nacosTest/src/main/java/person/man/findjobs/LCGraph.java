package person.man.findjobs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
