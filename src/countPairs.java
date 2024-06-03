class Solution {
    public int countPairs(int[] nums, int low, int high) {
        Trie trie = new Trie();
        int count = 0;
        for (int val : nums) {
            count += trie.query(val, high) - trie.query(val, low - 1);
            trie.insert(val);
        }
        return count;
    }
}

class Node {
    private static final int BITS = 31;
    Node[] links = new Node[2];
    int prefix = 0;

    boolean containsKey(int bit) {
        return links[bit] != null;
    }

    Node get(int bit) {
        return links[bit];
    }

    void put(int bit, Node node) {
        links[bit] = node;
    }

    void increasePrefix() {
        prefix++;
    }

    int getPrefix() {
        return prefix;
    }
}

class Trie {
    private Node root;

    Trie() {
        root = new Node();
    }

    void insert(int num) {
        Node node = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (!node.containsKey(bit)) {
                node.put(bit, new Node());
            }
            node = node.get(bit);
            node.increasePrefix();
        }
    }

    int query(int val, int high) {
        Node node = root;
        int count = 0;
        for (int i = 31; i >= 0; i--) {
            if (node == null) break;
            int valBit = (val >> i) & 1;
            int highBit = (high >> i) & 1;
            if (highBit == 1) {
                if (node.containsKey(valBit)) {
                    count += node.get(valBit).getPrefix();
                }
                node = node.get(1 - valBit);
            } else {
                node = node.get(valBit);
            }
        }
        if (node != null) {
            count += node.getPrefix();
        }
        return count;
    }
}
