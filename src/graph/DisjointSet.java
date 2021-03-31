package graph;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet {

    Map<Integer, Integer> parent = new HashMap<>();

    void makeSet(int N) {
        for(int i = 0; i < N; i++) {
            parent.put(i, i);
        }
    }

    int find(int k) {
        if (parent.get(k) == k)
            return k;

        return find(parent.get(k));
    }

    void union(int a, int b) {
        int x = find(a);
        int y = find(b);

        parent.put(x, y);
    }

}
