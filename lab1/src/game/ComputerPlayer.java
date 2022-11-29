package game;
import javax.swing.JOptionPane;
import java.util.*;

public class ComputerPlayer extends Player {
	
	ComputerPlayer(String userId){
		super(userId);
		
	}
	
	public int takePins(Board b) {
		
		Random rand = new Random();
		
		if(b.getNoPins() == 1) {
			b.takePins(1);
			JOptionPane.showMessageDialog(null, "Datorn valde 1 pins");
		} else {
			
			int n = rand.nextInt(2)+1;
			b.takePins(n);
			JOptionPane.showMessageDialog(null, "Datorn valde " + n + " pins");
		}
		
		return b.getNoPins();
	}

}