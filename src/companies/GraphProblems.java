package companies;

import graph.Node;

import java.util.*;

public class GraphProblems {

    public static int ladderLength(String beginWord, String endWord, List<String> words) {
        HashSet<String> set = new HashSet<>(words);

        if (!set.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        int level = 1;

        while(!queue.isEmpty()) {

            int size = queue.size();

            while(size-- > 0) {

                String currWord = queue.poll();

                if (currWord.equals(endWord))
                    return level;

                char[] currWordArray = currWord.toCharArray();

                for(int j = 0; j < currWord.length(); j++) {
                    char originalCh = currWordArray[j];
                    for(char ch = 'a'; ch <= 'z'; ch++) {
                        if (ch == originalCh)
                            continue;
                        currWordArray[j] = ch;
                        String newWord = String.valueOf(currWordArray);

                        if (set.contains(newWord)) {
                            queue.add(newWord);
                        }
                    }

                    currWordArray[j] = originalCh;
                }

            }


            level++;
        }

        return 0;
    }


    public String alienOrder(String[] words) {
        int[] indegree = new int[26]; //
        Map<Character, HashSet<Character>> g = new HashMap<>();
        buildGraph(g, words, indegree);
        return bfsWords(g, indegree);
    }

    public String bfsWords(Map<Character, HashSet<Character>> g, int[] indegree) {
        StringBuilder sb = new StringBuilder();
        int totalChars = g.size();

        if (totalChars == 0) {
            return "";
        }

        Queue<Character> queue =  new LinkedList<>();

        for(char c : g.keySet()) {
            if (indegree[c - 'a'] == 0) {
                sb.append(c);
                queue.offer(c);
            }
        }

        if (queue.size() == totalChars && queue.size() > 1) {
            StringBuilder builder = new StringBuilder();

            while(!queue.isEmpty()) {
                builder.append(queue.poll());
            }

            return builder.toString();
        }


        while(!queue.isEmpty()) {
            char cur = queue.poll();

            if (g.get(cur) == null || g.get(cur).size() == 0) {
                continue;
            }

            for(char neig : g.get(cur)) {
                indegree[neig - 'a']--;
                //System.out.println(neig + " : " + indegree[neig - 'a']);
                if (indegree[neig - 'a'] == 0) {
                    queue.offer(neig);
                    sb.append(neig);
                }
            }
        }

        return sb.length() == totalChars ? sb.toString() : "";
    }

    public static Node cloneGraph(Node node) {
        return closeGraphInternal(node, new HashMap<>());
    }

    private static Node closeGraphInternal(Node node, Map<Node, Node> map) {
        if (node == null)
            return null;

        if (map.containsKey(node))
            return map.get(node);

        Node clone = new Node(node.val);

        for(Node neigh : node.neighbors) {
            clone.neighbors.add(closeGraphInternal(neigh, map));
        }

        map.put(node, clone);

        return clone;
    }

    public void buildGraph(Map<Character, HashSet<Character>> g, String[] words, int[] indegree) {
        for(String word : words) {
            for (char c : word.toCharArray()) {
                g.putIfAbsent(c, new HashSet<>());
            }
        }

        for(int i = 1; i < words.length; i++) {
            String first = words[i-1];
            String second = words[i];

            int len = Math.min(first.length(), second.length());

            boolean isFirst = false;
            int j = 0;
            for (j = 0; j < len; j++) {
                if (isFirst == false && first.charAt(j) != second.charAt(j)) {
                    char out = first.charAt(j);
                    char in = second.charAt(j);

                    if (!g.get(out).contains(in)) {
                        g.get(out).add(in);
                        indegree[in-'a']++;
                    }

                    isFirst = true;
                }
            }

            if (second.length() == j && j < first.length()) {
                g.clear();
                return;
            }
        }

        System.out.println(g);
    }

}
