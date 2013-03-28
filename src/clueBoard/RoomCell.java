package clueBoard;

import java.awt.Graphics;

public class RoomCell extends BoardCell {

	public enum DoorDirection {RIGHT, LEFT, UP, DOWN, NONE}

	DoorDirection doorDirection;
	private char roomInitial, secondInitial;
	
	
	/******************************************************************************************************************
	 * RoomCell() - default constructor
	 *****************************************************************************************************************/
	public RoomCell() {
		
	}

	/******************************************************************************************************************
	 * RoomCell(String id) - constructor that appropriately sets the doorDirection, roomInitial, and secondInitial
	 * 							based upon the id. Also throws an exception if the id is not 1 or 2 characters in
	 * 							length.
	 *****************************************************************************************************************/
	public RoomCell(String id) throws BadConfigFormatException {
		
		//store the first character as the room initial
		roomInitial = id.charAt(0);
		
		//if the room is a door, assign the appropriate value to the enum based on the second character of the id
		if (id.length() == 2) {
			secondInitial = (id.charAt(1));
			switch(secondInitial) {
			case 'R':
				doorDirection = DoorDirection.RIGHT;
				break;
			case 'L':
				doorDirection = DoorDirection.LEFT;
				break;
			case 'U':
				doorDirection = DoorDirection.UP;
				break;
			case 'D':
				doorDirection = DoorDirection.DOWN;
				break;
			default :
				doorDirection = DoorDirection.NONE;
				break;
			}
		} else if (id.length() != 1) {
			
			//if the id has more than 2 characters, throw an error
			throw new BadConfigFormatException("Room cell initial contains invalid number of characters.");
			
			//if the id is one character, set door direction to none
		} else {
			doorDirection = DoorDirection.NONE;
		}
		
	}
	

	/******************************************************************************************************************
	 *  isRoom() - returns true
	 *****************************************************************************************************************/
	@Override
	public boolean isRoom() {
		return true;
	}
	
	/******************************************************************************************************************
	 *  getDoorDirection() - returns doorDirection
	 *****************************************************************************************************************/
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
	
	/******************************************************************************************************************
	 * getRoomInitial() - returns room initial
	 *****************************************************************************************************************/
	public char getRoomInitial() {
		return roomInitial;
	}
	
	/******************************************************************************************************************
	 * isDoorway() - returns true if doorDirection is not NONE (i.e. it is UP, DOWN, LEFT, or RIGHT), otherwise false
	 *****************************************************************************************************************/
	@Override
	public boolean isDoorway() {
		if (doorDirection != DoorDirection.NONE) {
			return true;
		}
		return false;
	}
	
	/******************************************************************************************************************
	 * getSecondInitial() - returns the secondInitial of the initial id with which the cell was created
	 *****************************************************************************************************************/
	public char getSecondInitial() {
		return secondInitial;
	}
	
	
	@Override
	public void draw(Graphics g, Board board) {
		
	}
}
