public class CountDistinctSubseq {
    public static void main(String[] args) {
        System.out.println(countDistinctSubsequences("aaaa"));
    }

    static int countDistinctSubsequences(String word) {
        Trie4 trie = new Trie4();
        for (int i = 0; i < word.length(); i++) {
            for (int j = i; j < word.length(); j++) {
                trie.insert(word.substring(j));
            }
        }
        return trie.getDistinctCount();
    }
}

class TrieNode {
    private static final int ALPHABET_SIZE = 26;
    private TrieNode[] links = new TrieNode[ALPHABET_SIZE];

    public boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    public void put(char ch, TrieNode node) {
        links[ch - 'a'] = node;
    }

    public TrieNode get(char ch) {
        return links[ch - 'a'];
    }
}

class Trie4 {
    private int distinctCount = 0;
    private TrieNode root;

    public Trie4() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {
                node.put(currentChar, new TrieNode());
                distinctCount++;
            }
            node = node.get(currentChar);
        }
    }

    public int getDistinctCount() {
        return distinctCount;
    }
}
