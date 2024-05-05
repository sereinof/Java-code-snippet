package person.man.zhouyuqing;

import java.util.HashMap;

public class Graph {
    public HashMap<Node, Node> map = new HashMap();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        if(map.containsKey(node)){
            return map.get(node);
        }
        Node newly = new Node(node.val);
        map.put(node,newly);
        for (Node n : node.neighbors) {
            newly.neighbors.add(cloneGraph(n));
        }
        return newly;
    }
}
