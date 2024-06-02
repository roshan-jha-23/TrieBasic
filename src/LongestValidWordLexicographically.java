public class LongestValidWordLexicographically {
    public static void main(String[] args) {
        // Test case
        String[] words = {"a", "banana", "app", "apply", "ap", "appl", "apple"};
        String result = completeString(words);
        System.out.println("Longest valid word lexicographically: " + result);
    }

    public static String completeString(String[] words) {
        Trie3 trie = new Trie3();
        for (String word : words) {
            trie.insert(word);
        }

        String longest = "";
        for (String word : words) {
            if (trie.checkIfPrefixExist(word)) {
                if (word.length() > longest.length() ||
                        (word.length() == longest.length() && word.compareTo(longest) < 0)) {
                    longest = word;
                }
            }
        }
        return longest;
    }
}

class Node3 {
    private Node3[] links = new Node3[26];
    private boolean isEnd = false;

    public boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    public void put(char ch, Node3 node) {
        links[ch - 'a'] = node;
    }

    public Node3 get(char ch) {
        return links[ch - 'a'];
    }

    public void setEnd() {
        isEnd = true;
    }

    public boolean isEnd() {
        return isEnd;
    }
}

class Trie3 {
    private Node3 root;

    public Trie3() {
        root = new Node3();
    }

    public void insert(String word) {
        Node3 node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containsKey(ch)) {
                node.put(ch, new Node3());
            }
            node = node.get(ch);
        }
        node.setEnd();
    }

    public boolean checkIfPrefixExist(String word) {
        Node3 node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (node.containsKey(ch)) {
                node = node.get(ch);
                if (!node.isEnd()) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }
}

