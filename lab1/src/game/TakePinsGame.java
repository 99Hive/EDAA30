package game;

import javax.swing.JOptionPane;

public class TakePinsGame {

	public static void main(String[] args) {
		
		Board board = new Board();
		board.setUp(15);
		
		Player p1 = new HumanPlayer("Simon");
		Player p2 = new ComputerPlayer("Dator");
		
		
		
		while (board.getNoPins() > 0 ){
			
			if(board.getNoPins()>0) {

				p1.takePins(board);
				if(board.getNoPins() == 0) {
					JOptionPane.showMessageDialog(null, p2.getUserId() + " har vunnit!");
					break;
					
				}
			}
		
			if(board.getNoPins()>0) {
				p2.takePins(board);
				JOptionPane.showMessageDialog(null, "Antal pins kvar i spelet: " + board.getNoPins());
				if(board.getNoPins() == 0) {
					JOptionPane.showMessageDialog(null, p1.getUserId() + " har vunnit!");

					break;
					
				}
			}

		}

	}

}