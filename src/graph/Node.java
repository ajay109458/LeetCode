package graph;

import java.util.List;

public class Node {
    public int val;
    public int cost;
    public List<Node> neighbors;

    public Node(int val, List<Node> list) {
        this.val = val;
        this.neighbors = list;
    }

    public Node(int i) {
        this.val  = i;
    }
}
