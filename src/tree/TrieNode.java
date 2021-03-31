package tree;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    Map<Character, TrieNode> map;
    boolean isWord;

    public TrieNode() {
        map = new HashMap<>();
    }
}
