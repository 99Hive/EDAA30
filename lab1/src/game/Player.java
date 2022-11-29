package game;


public class Player {
	
	protected String userId;
	
	
	
	Player(String userId){
		this.userId = userId;
		
	}
	
	public String getUserId() {
		return userId;
	}
	
	public int takePins(Board a) {
		//a.takePins(b);
		return a.getNoPins();
	}

}