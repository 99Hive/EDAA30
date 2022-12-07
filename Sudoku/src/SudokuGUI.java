
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SudokuGUI {
	public JFrame frame = new JFrame("Sudoku Solver");
	public JTextField textField[][];
	public JButton solveButton;
	public JButton clearButton;
	private final int EMPTY = 0;
	private SudokuSolver sr;

	/*
	 * Konstruktor, innehåller rutorna, en sudokureader. Kalllar på metod för att
	 * sätta upp GUIN
	 */

	public SudokuGUI() {
		textField = new JTextField[9][9];
		sr = new SudokuReader();
		setGUI();

	}

	public void setGUI() {

		Random rand = new Random();

		Color[] col = new Color[9];
		/* Random färger för varje ny ruta */
		for (int i = 0; i < 9; i++) {
			int randColNbr = rand.nextInt(200, 255);
			Color randCol = Color.getHSBColor(randColNbr, randColNbr, randColNbr);
			col[i] = randCol;
		}

		// rutorna
		frame = new JFrame("SUDOKU SOLVER");

		// lägger in rutorna och fixar designen för dem
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				textField[i][j] = new JTextField();
				textField[i][j].setBounds(60 + j * 40, 50 + i * 40, 40, 40); // dimensioner för rutorna, x y width
																				// heoght
				textField[i][j].setBackground(col[(i / 3) * 3 + (j / 3)]); // bakgrundsfärgen
				Font textFont = new Font("SansSerif", Font.BOLD, 20);
				textField[i][j].setFont(textFont);
				textField[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				textField[i][j].setText("");
				frame.add(textField[i][j]);
			}

			/*
			 * Lägger till solveknappen & clearknappen, kallar på solve-metoden via en
			 * actionlistener
			 */
			solveButton = new JButton("SOLVE");
			clearButton = new JButton("CLEAR");

			/* Återställer alla celler till 0 med actionlistener */
			clearButton.setBounds(60, 450, 80, 80);
			clearButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
//					solved = true;
					sr.clear(); // kallar på interface ifall det behövs, vi vill dock ha null istället för 0or
					for (int i = 0; i < 9; i++) {
						for (int k = 0; k < 9; k++) {
							if (sr.getCell(i, k) == 0) {
								textField[i][k].setText(null);
								textField[i][k].setEditable(true);

							} else
								textField[i][k].setText(Integer.toString(sr.getCell(i, k)));
							textField[i][k].setEditable(true);

						}
					}
				}
			});

			/* Knapp som kallar på rekursiva lösningen med actioneventlistener */
			solveButton.setBounds(330, 450, 80, 80);
			solveButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					boolean shouldSolve = true;
					for (int i = 0; i < 9; i++) {
						for (int k = 0; k < 9; k++) {
//							int[][] a = new int[9][9];
							int value = 0;
							if (textField[i][k].getText().isEmpty()) {
								value = 0;
								sr.setCell(i, k, value);
							} else if (allowedValue(textField[i][k].getText())) {
								shouldSolve = true;
								value = Integer.parseInt(textField[i][k].getText());
								sr.setCell(i, k, value);

							} else {
								shouldSolve = false;
								JOptionPane.showMessageDialog(frame, "Illegal character.");
								textField[i][k].setEditable(true);
								System.out.println("nej1");
								break;
							}

						}
					}

					if (sr.solve() && shouldSolve == true) {
						for (int i = 0; i < 9; i++) {
							for (int k = 0; k < 9; k++) {
								textField[i][k].setText(Integer.toString(sr.getCell(i, k)));
								textField[i][k].setEditable(false);
//								System.out.print(a[i][k]);
							}
						}
					} else {
						JOptionPane.showMessageDialog(frame, "Couldn't find a solution.");

					}
				}
			});
			frame.add(solveButton);
			frame.add(clearButton);
		}
		// for-loop stop, s�tter rutans dimensioner
		center();
		frame.setSize(500, 600);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocation(300, 100);
		// frame.setBackground(Color.blue);

	}

	public void center() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dimension.width / 2 - frame.getWidth() / 2, dimension.height / 2 - frame.getHeight() / 2);
		frame.setSize(600, 600);
	}

	/* Privat metod som kollar att det inskrivna värdet är ok */
	private boolean allowedValue(String text) {
		if (text.matches("1") || text.matches("2") || text.matches("3") || text.matches("4") || text.matches("5")
				|| text.matches("6") || text.matches("7") || text.matches("8") || text.matches("9")) {
			return true;
		}

		return false;

	}

}