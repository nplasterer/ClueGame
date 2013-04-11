package clueBoard;
//Naomi and Brandon
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class Player {
	protected String name;
	protected ArrayList<Card> seen = new ArrayList<Card>();
	protected ArrayList<Card> cards = new ArrayList<Card>();
	protected ArrayList<Card> deck = new ArrayList<Card>();
	protected java.awt.Point location;
	protected java.awt.Color color;
	protected char lastVistedRoom;
	protected String currentRoom;
	
	public Player() {
		name = null;
		location = null;
		color = null;
	}

	public Player(String name, java.awt.Point location, java.awt.Color color) {
		this.name = name;
		this.location = location;
		this.color = color;
	}
	
	public void createDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}
	
	public Card disproveSuggestion(Solution suggestion){

		Random rand = new Random();
		ArrayList<Card> hand = new ArrayList<Card>();
		for(int i = 0; i < cards.size(); i++)
			hand.add(cards.get(i));
		
		while (!hand.isEmpty())
		{
			int j = rand.nextInt(hand.size());
			if(suggestion.getWeapon().equals(hand.get(j).getCard()))
				return hand.get(j);
			if(suggestion.getPerson().equals(hand.get(j).getCard()))
				return hand.get(j);
			if(suggestion.getRoom().equals(hand.get(j).getCard()))
				return hand.get(j);
			hand.remove(j);
		}
		return null;

	}
	
	public void acceptCard(Card card) {
		cards.add(card);
		seen.add(card);
	}
	
	public void draw(Graphics g){
		g.setColor(color);
		g.fillOval(location.x*25, location.y*25, 25, 25);
	}

	public void drawOutline(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setStroke(new BasicStroke(3));
		g2D.setColor(color);
		g2D.drawOval(location.x*25, location.y*25, 25, 25);
		g2D.setStroke(new BasicStroke(1));
	}
	
	//Setters and getters for tests
	public String getName() {
		return name;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public void setLocation(java.awt.Point location) {
		this.location = location;
	}

	public java.awt.Point getLocation() {
		return location;
	}
	
	public java.awt.Color getColor() {
		return color;
	}
	
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}


	public char getLastVistedRoom() {
		return lastVistedRoom;
	}


	public void setLastVistedRoom(char lastVistedRoom) {
		this.lastVistedRoom = lastVistedRoom;
	}

	public void setCurrentRoom(String currentRoom) {
		this.currentRoom = currentRoom;
	}
	
	public String getCurrentRoom() {
		return currentRoom;
	}

	@Override
	public String toString() {
		return this.name;
	}
	
}
