
public class Main {
    public static void main(String[] args) {


    }
}
class Node {
    private Node[] links = new Node[26];
    private boolean isEnd = false;

    boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    void put(char ch, Node node) {
        links[ch - 'a'] = node;
    }

    Node get(char ch) {
        return links[ch - 'a'];
    }

    void setEnd() {
        isEnd = true;
    }

    boolean isEnd() {
        return isEnd;
    }
}

class Trie {
    private Node root;

    Trie() {
        root = new Node();
    }

    void insert(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (!node.containsKey(ch)) {
                node.put(ch, new Node());
            }
            node = node.get(ch);
        }
        node.setEnd();
    }

    boolean search(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (!node.containsKey(ch)) {
                return false;
            }
            node = node.get(ch);
        }
        return node.isEnd();
    }

    boolean startsWith(String prefix) {
        Node node = root;
        for (char ch : prefix.toCharArray()) {
            if (!node.containsKey(ch)) {
                return false;
            }
            node = node.get(ch);
        }
        return true;
    }
}
