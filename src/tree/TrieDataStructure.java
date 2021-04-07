package tree;

public class TrieDataStructure {

    TrieNode root = new TrieNode();

    public void insert(String str) {

        TrieNode p = root;

        for(char ch : str.toCharArray()) {

            if (!p.map.containsKey(ch)) {
                p.map.put(ch, new TrieNode());
            }

            p = p.map.get(ch);
        }

        p.isWord = true;
    }

    public boolean checkWordExist(String str) {

        TrieNode p = root;

        for(char ch : str.toCharArray()) {
            TrieNode child = p.map.get(ch);
            if (child == null)
                return false;
            p = child;
        }

        return p.isWord;
    }

    public boolean checkPrefix(String str) {

        TrieNode p = root;

        for(char ch : str.toCharArray()) {
            TrieNode child = p.map.get(ch);
            if (child == null)
                return false;
            p = child;
        }

        return true;
    }

    public static void main(String[] args) {
        TrieDataStructure s = new TrieDataStructure();
        s.insert("ajay");
        System.out.println(s.checkWordExist("ajay"));
    }

}
