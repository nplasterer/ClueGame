package clueBoard;
//Naomi and Brandon
public class Card {
	private String card;
	public enum cardType {PERSON, WEAPON, ROOM}
	private cardType type;
	
	public Card(String card, cardType type) {
		this.card = card;
		this.type = type;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}
	
	public boolean equals(Object o) {

		if (o instanceof Card) {
			Card c = (Card) o;
			if (this.card.equals(c.card)) return true;
		}
		return false;

	}
	 
	public cardType getType() {
		return type;
	}
}
