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

		// kolla ifall vi är i slutet, dvs, har löst sudokun
		if (row == 8 && col == 9) {
			return true;
		}
		// om col är nio, ändra till 0 och gå till nästa rad.
		if (col == 9) {
			col = 0;
			row++;
		}
		// om den inte är 0, hoppa över
		if (board[row][col] != 0) {
			return solver(board, row, col + 1);
		}
		// räknar 1-10 för at testa varje värde för cellen
		for (int i = 1; i <= 9; i++) {
			// om nuvarande index går att sätta in, sätt in och kolla nästa plats.
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
			if ((value == board[row][i] && i != col) || (value == board[i][col] && i != row)) { // kollar så den inte
				// finns horisontellt
				// eller
				// vertikalt
				return false;
			}
			/* Börjar i starten av varje "liten" ruta */
			int smallRowStart = (row / 3) * 3;
			int smallColStart = (col / 3) * 3;

			/* iterera genom den lilla rutan, returnera false om value redan finns */

			for (int r = smallRowStart; r < smallRowStart + 3; r++) {
				for (int c = smallColStart; c < smallColStart + 3; c++) {
					if (board[r][c] == value) {
						// System.out.println("Number already exists within the small square");
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
		// TODO Auto-generated method stub
		return board[row][col];

	}

	@Override
	// Checks if the input values are valid
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
//	public String toString() {
//		StringBuilder sb = new StringBuilder();
//		for (int r = 0; r < 9; r++) {
//			sb.append("\n");
//			for (int c = 0; c < 9; c++) {
//				sb.append(String.format(" %s ", board[r][c]));
//			}
//		}
//		return sb.toString();
//	}
//
//	@Override
//	public boolean thereIsD(int row, int col, int value) {
//		// TODO Auto-generated method stub
//		return false;
//	}

//	public static void main(String[] args) {
////		SudokuGUI gui = new SudokuGUI();
//
//		int grid[][] = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 }, { 5, 2, 0, 0, 0, 0, 0, 0, 0 }, { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
//				{ 0, 0, 3, 0, 1, 0, 0, 8, 0 }, { 9, 0, 0, 8, 6, 3, 0, 0, 5 }, { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
//				{ 1, 3, 0, 0, 0, 0, 2, 5, 0 }, { 0, 0, 0, 0, 0, 0, 0, 7, 4 }, { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };
//
//		for (int i = 0; i < 9; i++) {
//			for (int k = 0; i < 9; i++) {
//				grid[i][k] = 0;
//			}
//		}
//		SudokuReader sd = new SudokuReader(grid);
//		sd.solve();
//
//		System.out.print(sd.toString());
//	}

}
