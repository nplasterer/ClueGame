package clueBoard;
//Naomi and Brandon
public class HumanPlayer extends Player{

	public HumanPlayer(String name, java.awt.Point location, java.awt.Color color) {
		super(name, location, color);
	}
	
	public HumanPlayer() {
		super();
	}
	
	public void updateSeen(Card seen){
		this.seen.add(seen);
	}
}
