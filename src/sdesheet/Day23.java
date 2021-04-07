package sdesheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day23 {

    private static class Node {
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

    public Map<Node, Node> visitedMap = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null)
            return null;

        if (visitedMap.containsKey(node))
            return visitedMap.get(node);

        Node curr = new Node(node.val);

        visitedMap.putIfAbsent(node, curr);

        for(Node neigh : node.neighbors) {
            curr.neighbors.add(cloneGraph(neigh));
        }

        return curr;
    }
}
