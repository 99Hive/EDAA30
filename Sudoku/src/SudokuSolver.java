public interface SudokuSolver {
	/**
	 * Tries to solve the current sudoku.
	 * 
	 * @return true if there is one or more solutions, false if no solution could be
	 *         found.
	 */
	boolean solve();

	/** Clears the entire sudoku board. */
	default void clear() {
		for (int i = 0; i <= 8; i++) {
			for (int k = 0; k <= 8; k++) {
				setCell(i, k, 0);
			}
		}
	}

	boolean validCell(int digit, int row, int col);

	/**
	 * (Optional) Fills the board with random (valid) numbers.
	 *
	 * @throws UnsupportedOperationException if not implemented.
	 */

	boolean solver(int grid[][], int row, int col);

	/**
	 * @param row must be between 1-9 since the sudokuboard is 9x9
	 * @param col must be between 1-9 since the sudokuboard is 9x9
	 * @param val must be between 1-9 (following the rules of sudoku) or 0 if cell
	 *            should be marked as unsolved.
	 * @throws IllegalArgumentException if any of the parameters are out of range or
	 *                                  the if val can't be placed at the current
	 *                                  cell.
	 */
	void setCell(int row, int col, int val) throws IllegalArgumentException;

	/**
	 * @param row must be between 1-9 since the sudokuboard is 9x9
	 * @param col must be between 1-9 since the sudokuboard is 9x9
	 * @throws IllegalArgumentException if any of the parameters are out of range
	 */
	int getCell(int row, int col) throws IllegalAccessError;

	boolean isInputValid() throws IllegalAccessError;

}