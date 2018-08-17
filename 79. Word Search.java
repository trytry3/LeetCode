/* Given a 2D board and a word, find if the word exists in the grid.
The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once.

Example:
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
*/

// dfs, backtracking
class Solution {
    private boolean[][] visited;
    
    public boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];    
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if((word.charAt(0) == board[i][j]) && dfs(board, word, i, j, 0)){
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean dfs(char[][]board, String word, int i, int j, int indexInWord){
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