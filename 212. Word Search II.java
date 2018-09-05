/* Given a 2D board and a list of words from the dictionary, find all words in the board.
Each word must be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once in a word.

Example:
Input: 
words = ["oath","pea","eat","rain"] and board =
[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Output: ["eat","oath"]

Note:
You may assume that all inputs are consist of lowercase letters a-z.
*/

// method 1: trie
class Solution {
    private class TrieNode {
        public boolean isWord;
        public TrieNode[] children;

        public TrieNode() {
            children = new TrieNode[26];
        }
    }

    private TrieNode root = new TrieNode();
    private boolean[][] visited;

    public List<String> findWords(char[][] board, String[] words) {
        Set<String> res = new HashSet<>();
        visited = new boolean[board.length][board[0].length];
        buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (root.children[board[i][j] - 'a'] != null) {
                    search(board, i, j, root, "", res);
                }
            }
        }
        return new ArrayList<>(res);
    }

    private void buildTrie(String[] words) {
        for (String word : words) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.isWord = true;
        }
    }

    private void search(char[][] board, int i, int j, TrieNode node, String prefix, Set<String> res) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length ||
            node.children[board[i][j] - 'a'] == null || visited[i][j]) 
            return;
        
        visited[i][j] = true;
        node = node.children[board[i][j] - 'a'];
        if (node.isWord) {
            res.add(prefix + board[i][j]);
        }

        search(board, i - 1, j, node, prefix + board[i][j], res);
        search(board, i + 1, j, node, prefix + board[i][j], res);
        search(board, i, j - 1, node, prefix + board[i][j], res);
        search(board, i, j + 1, node, prefix + board[i][j], res);

        visited[i][j] = false;
    }
}


// method 2: slow
class Solution {
    // check each word in the dictionary, whether they exist on board    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        for (String word: words) {
            if (exist(board, word) && !res.contains(word))
                res.add(word);
        }
        return res;
    }
    
    private boolean[][] visited;
    
    private boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];    
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ((word.charAt(0) == board[i][j]) && dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean dfs(char[][]board, String word, int i, int j, int indexInWord) {
        if (indexInWord == word.length())
            return true; // because nothing needs to match
        if (i >= board.length || i < 0 || j >= board[i].length || j < 0 || 
            board[i][j] != word.charAt(indexInWord) || visited[i][j])
                return false;
        // here board[i][j] == word.charAt(indexInWord), search if next char matches
        visited[i][j] = true;
        if (dfs(board, word, i-1, j, indexInWord+1) || dfs(board, word, i+1, j, indexInWord+1) ||
            dfs(board, word, i, j-1, indexInWord+1) || dfs(board, word, i, j+1, indexInWord+1))
                return true;
        // next char can't find matched path, backtracking
        visited[i][j] = false;
        return false;
    }
}