package game;
import javax.swing.JOptionPane;

public class UserInterface {

	
	UserInterface(){
		
	}
	
	
	public static void printMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);

	}
	
	public static int askForInt(String msg) { // Tar hand om icke-korrekt data
		
		while (!(msg == null) && !(msg.matches("[0-9]+"))) { // Kollar om man klickar i nått annat än siffror. ( "" eller Cancel )
			JOptionPane.showMessageDialog(null, "Du måste välja 1 eller 2", "ErRor", JOptionPane.ERROR_MESSAGE );
			msg = JOptionPane.showInputDialog("Välj 1 eller 3");
			
		}
		
		if(msg == null) { //Ifall man klickar avbryt
			JOptionPane.showMessageDialog(null, "Avslutas", "Cancel", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		

		return Integer.parseInt(msg);
		
		
		
	}

}