package tree;

import java.util.List;

class TreeNodeN {
    public int val;
    public List<TreeNodeN> children;

    public TreeNodeN() {}

    public TreeNodeN(int _val) {
        val = _val;
    }

    public TreeNodeN(int _val, List<TreeNodeN> _children) {
        val = _val;
        children = _children;
    }
}
