package clueBoard;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

//Naomi and Brandon
public class ComputerPlayer extends Player{
	private char lastVistedRoom;
	
	public ComputerPlayer(String name, java.awt.Point location, java.awt.Color color) {
		super(name, location, color);
	}
	
	public ComputerPlayer() {
		super();
	}
	
	public BoardCell pickLocation(Set<BoardCell> target){
		BoardCell targetCell = null;
		Random roller = new Random();
		ArrayList<BoardCell> doors = new ArrayList<BoardCell>();
		ArrayList<BoardCell> nonDoors = new ArrayList<BoardCell>();
		//pick target, doors have first priority
		for(BoardCell b : target) {
			if(b.isDoorway()) {
				doors.add(b);
			}
			else
				nonDoors.add(b);
		}
		
		while(doors.size() > 0) {
			int index = roller.nextInt(doors.size());
			targetCell = doors.get(index);
			RoomCell cell = (RoomCell) targetCell;
			if(cell.getRoomInitial() != lastVistedRoom) {
				return targetCell;
			}
			else 
				doors.remove(targetCell);
		}
		
		if(doors.size() == 0) {
			int index = roller.nextInt(nonDoors.size());
			targetCell = nonDoors.get(index);
		}
		//update current and last visited room
		/*if(currentRoom != 'W') {
			lastVistedRoom = currentRoom;
		}
		
		if(targetCell.isWalkway()) {
			currentRoom = 'W';
		}
		else if(targetCell.isDoorway()){
			RoomCell doorway = (RoomCell) targetCell;
			currentRoom = doorway.getRoomInitial();
		}*/
		return targetCell;
	}
	
	public Solution createSuggestion(){
		//make arrayLists
		ArrayList<Card> notSeen = new ArrayList<Card>();
		ArrayList<Card> notSeenPeople = new ArrayList<Card>();
		ArrayList<Card> notSeenWeapons = new ArrayList<Card>();
		
		//set notSeen
		for(Card c : ClueGame.getFullDeck()) {
			if(!seen.contains(c))
				notSeen.add(c);
		}
		
		//sort not seen
		for(Card c : notSeen) {
			if(c.getType() == Card.cardType.PERSON)
				notSeenPeople.add(c);
			else if(c.getType() == Card.cardType.WEAPON)
				notSeenWeapons.add(c);
		}
		
		//make random number generator and get person and weapon
		Random roller = new Random();
		int index = roller.nextInt(notSeenPeople.size());
		String person = notSeenPeople.get(index).getCard();
		index = roller.nextInt(notSeenWeapons.size());
		String weapon = notSeenWeapons.get(index).getCard();
		
		//set Room
		String room;
		switch(this.currentRoom) {
		case 'C' :
			room = "Conservatory";
			break;
		case 'K' :
			room = "Kitchen";
			break;
		case 'B' :
			room = "Ballroom";
			break;
		case 'R' :
			room = "Billiard Room";
			break;
		case 'L' :
			room = "Library";
			break;
		case 's' :
			room = "Study";
			break;
		case 'D' :
			room = "Dining Room";
			break;
		case 'O' :
			room = "Lounge";
			break;
		case 'H' :
			room = "Hall";
			break;
		default :
			room = null;
		}
		
		Solution guess = new Solution(person, weapon, room);
		return guess;
	}
	
	public void updateSeen(Card seen){
		this.seen.add(seen);
	}

	public char getLastVistedRoom() {
		return lastVistedRoom;
	}

	public void setLastVistedRoom(char lastVistedRoom) {
		this.lastVistedRoom = lastVistedRoom;
	}
}
