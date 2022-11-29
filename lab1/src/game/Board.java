package game;

public class Board {
	
	private int noPins;
	
	
	Board(){
		
	}
	
	public int getNoPins() {
		return noPins;
	}
	
	public void setUp(int a) {
		noPins = a;
	}
	
	public int takePins(int a) {
		
		noPins -= a;
		return noPins;
	}

}