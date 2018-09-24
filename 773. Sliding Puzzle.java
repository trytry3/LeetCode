/* On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, 
and an empty square represented by 0.
A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
Given a puzzle board, return the least number of moves required so that the state of the board is solved. 
If it is impossible for the state of the board to be solved, return -1.

Examples:
Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.

Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.

Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]

Input: board = [[3,2,4],[1,5,0]]
Output: 14

Note:
board will be a 2 x 3 array as described above.
board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].
*/

// bfs, neighbors are board states
class Solution {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    
	public int slidingPuzzle(int[][] board) {
		BoardState boardState = new BoardState(board);
		Set<BoardState> seen = new HashSet<>();

		Queue<BoardState> boardStates = new LinkedList<>();
		boardStates.offer(boardState);
		seen.add(boardState);
		while (!boardStates.isEmpty()) {
			boardState = boardStates.poll();
			if (boardState.isWin()) {
				return boardState.level;
			}
			List<BoardState> nextStates = boardState.getNextStates();
			for (BoardState nextState : nextStates) {
				if (!seen.contains(nextState)) {
                    boardStates.offer(nextState);
					seen.add(nextState);
				}
			}
		}
		return -1;
	}

	private class BoardState {
		int[][] board;
        // position of zero
		int zx;
		int zy;
	    int level;

		public BoardState(int[][] board) {
			this.board = board;
			this.level = 0;
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					if (board[i][j] == 0) {
						this.zx = i;
						this.zy = j;
						break;
					}
				}
			}
		}
        
        public BoardState(BoardState parent, int newZx, int newZy) {
			this.board = deepCopy(parent.board);
			this.level = parent.level + 1;
            // swap zero
            board[parent.zx][parent.zy] = board[newZx][newZy];
			board[newZx][newZy] = 0;
            this.zx = newZx;
			this.zy = newZy;
		}

		public boolean isWin() {
			return board[0][0] == 1 && board[0][1] == 2 && board[0][2] == 3 
                    && board[1][0] == 4 && board[1][1] == 5;
		}

		public List<BoardState> getNextStates() {
			List<BoardState> list = new ArrayList<>();
			int rows = board.length;
			int cols = board[0].length;
            for (int[] dir : dirs) {
                int newZx = zx + dir[0];
                int newZy = zy + dir[1];
                if (newZx >= 0 && newZx < rows && newZy >= 0 && newZy < cols) 
                    list.add(new BoardState(this, newZx, newZy));
            }
			return list;
		}
        
        @Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			BoardState that = (BoardState) o;
			return Arrays.deepEquals(board, that.board);
		}

        // needed for board array equal
		@Override
		public int hashCode() {
			return Arrays.deepHashCode(board);
		}

        private int[][] deepCopy(int[][] array) {
		    int[][] copiedArray = new int[array.length][];
		    for (int i = 0; i < array.length; i++) {
			    copiedArray[i] = array[i].clone();
		    }
		    return copiedArray;
	    }
	}
	
}