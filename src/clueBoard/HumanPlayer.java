package clueBoard;
//Naomi and Brandon
public class HumanPlayer extends Player{
	private boolean madeAccusation = false;
	private boolean makingSuggestion = false;
	private boolean moved = false;

	public HumanPlayer(String name, java.awt.Point location, java.awt.Color color) {
		super(name, location, color);
	}
	
	public HumanPlayer() {
		super();
	}
	
	public void updateSeen(Card seen){
		this.seen.add(seen);
	}

	public void setMadeAccusation(boolean b) {
		this.madeAccusation = b;
	}

	public boolean isMadeAccusation() {
		return madeAccusation;
	}

	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public boolean isMakingSuggestion() {
		return makingSuggestion;
	}

	public void setMakingSuggestion(boolean makingSuggestion) {
		this.makingSuggestion = makingSuggestion;
	}
}
