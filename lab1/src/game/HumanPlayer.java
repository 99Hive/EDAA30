package game;
import javax.swing.JOptionPane;

public class HumanPlayer extends Player {
	
	HumanPlayer(String userId){
		super(userId);
	}
	
	//Spelaren drar 1 eller 2 pins 
	public int takePins(Board b) {
		int n = 0;

		if(b.getNoPins() == 1) {
			b.takePins(1);
			JOptionPane.showMessageDialog(null, "Du valde 1 pins");

		} else {

			n = UserInterface.askForInt(JOptionPane.showInputDialog("Din tur! Välj 1 eller 2 pins"));
	
	        while(!(n == 1 || n == 2)) {
	        		JOptionPane.showMessageDialog(null, "Du måste välja 1 eller 2", "Error", JOptionPane.ERROR_MESSAGE);
	        		n = UserInterface.askForInt(JOptionPane.showInputDialog("Välj 1 eller 2"));
	        	}
	        
			b.takePins(n);
			JOptionPane.showMessageDialog(null, "Du valde " + n + " pins");
			JOptionPane.showMessageDialog(null, "Antal pins kvar i spelet: " + b.getNoPins());
	        	
	        }

		
		
		return b.getNoPins(); 
	}
	
}