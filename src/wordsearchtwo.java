class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        int n = board.length;
        int m = board[0].length;
        boolean[][] visited = new boolean[n][m];
        int[] dirArray = {-1, 0, 1, 0, -1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs(board, visited, i, j, "", trie, result, dirArray);
            }
        }

        return result;
    }

    private void dfs(char[][] board, boolean[][] visited, int i, int j, String currentWord, Trie trie, List<String> result, int[] dirArray) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || visited[i][j]) {
            return;
        }

        currentWord += board[i][j];
        if (!trie.startsWith(currentWord)) {
            return;
        }

        if (trie.search(currentWord)) {
            result.add(currentWord);
            trie.erase(currentWord); 
        }

        visited[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int newRow = i + dirArray[k];
            int newCol = j + dirArray[k + 1];
            dfs(board, visited, newRow, newCol, currentWord, trie, result, dirArray);
        }
        visited[i][j] = false;
    }
}

class Node {
    Node[] links = new Node[26];
    boolean isEnd = false;
    int prefixCount = 0;

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

    void resetEnd() {
        isEnd = false;
    }

    boolean isEnd() {
        return isEnd;
    }

    void increasePrefix() {
        prefixCount++;
    }

    void reducePrefix() {
        prefixCount--;
    }

    int getPrefixCount() {
        return prefixCount;
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
            node.increasePrefix();
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

    void erase(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            if (node.containsKey(word.charAt(i))) {
                node = node.get(word.charAt(i));
                node.reducePrefix();
            } else {
                return;
            }
        }
        node.resetEnd();
    }
}
