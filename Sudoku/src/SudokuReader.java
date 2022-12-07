public class SudokuReader implements SudokuSolver {
	private int[][] board;
//	private final int EMPTY = 0;

	public SudokuReader() {
		board = new int[9][9];
	}

	public SudokuReader(int board[][]) {
		this.board = board;
	}

	@Override
	public boolean solve() {
		return solver(board, 0, 0);
	}

	@Override
	public boolean solver(int grid[][], int row, int col) {

		if (row == 8 && col == 9) {
			return true;
		}
		if (col == 9) {
			col = 0;
			row++;
		}
		if (board[row][col] != 0) {
			return solver(board, row, col + 1);
		}
		for (int i = 1; i <= 9; i++) {
			if (validCell(row, col, i)) {
				board[row][col] = i;
				if (solver(board, row, col + 1)) {
					return true;
				} else {
					// går det inte, ställ till 0 igen
					board[row][col] = 0;
				}
			}
		}
		return false;
	}

	@Override
	public boolean validCell(int row, int col, int value) {
		for (int i = 0; i < 9; i++) {
			if ((value == board[row][i] && i != col) || (value == board[i][col] && i != row)) { 
				
				return false;
			}
			int smallRowStart = (row / 3) * 3;
			int smallColStart = (col / 3) * 3;


			for (int r = smallRowStart; r < smallRowStart + 3; r++) {
				for (int c = smallColStart; c < smallColStart + 3; c++) {
					if (board[r][c] == value) {
						return false;
					}
				}
			}
		}

		return true;

	}

	@Override
	public void setCell(int row, int col, int val) throws IllegalArgumentException {
//		val = board[row][col];
		board[row][col] = val;
	}

	@Override
	public int getCell(int row, int col) throws IllegalArgumentException {
		return board[row][col];

	}

	@Override
	public boolean isInputValid() {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				int val = board[row][col];
				if (val != 0 && !validCell(row, col, val)) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		SudokuGUI gui = new SudokuGUI();
	}

//	@Override
//	public boolean thereIsD(int row, int col, int value) {
//		// TODO Auto-generated method stub
//		return false;
//	}



}
