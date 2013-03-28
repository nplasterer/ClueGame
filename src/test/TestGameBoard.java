package test;

import java.io.FileNotFoundException;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;
import clueBoard.BadConfigFormatException;
import clueBoard.Board;
import clueBoard.RoomCell;

public class TestGameBoard {

	private static Board board;
	int NUM_ROWS = 25, NUM_COLUMNS = 25, NUM_ROOMS = 11, NUM_DOORS = 12;
	

	@BeforeClass
	public static void setUp() {
		System.out.println("Setting up!");
		board = new Board("ClueBoard.cfg", "legend.cfg");
		board.loadConfigFiles();
	}
	
	@Test
	public void testNumRooms() {
		System.out.println("testRoomNumbers()");
		Map<Character, String> testMap = board.getRooms();
		Assert.assertEquals(NUM_ROOMS, testMap.size());
	}
	
	@Test
	public void testMapping() {
		Assert.assertEquals(board.getRooms().get('C'), "Conservatory");
		Assert.assertEquals(board.getRooms().get('K'), "Kitchen");
		Assert.assertEquals(board.getRooms().get('B'), "Ballroom");
		Assert.assertEquals(board.getRooms().get('R'), "Billiard room");
		Assert.assertEquals(board.getRooms().get('L'), "Library");
		Assert.assertEquals(board.getRooms().get('S'), "Study");
		Assert.assertEquals(board.getRooms().get('D'), "Dining room");
		Assert.assertEquals(board.getRooms().get('O'), "Lounge");
		Assert.assertEquals(board.getRooms().get('H'), "Hall");
		Assert.assertEquals(board.getRooms().get('X'), "Closet");
		Assert.assertEquals(board.getRooms().get('W'), "Walkway");
	}
	
	@Test
	public void testBoardDimensions(){
		Assert.assertEquals(NUM_COLUMNS, board.getNumColumns());
		Assert.assertEquals(NUM_ROWS, board.getNumRows());
	}
	
	@Test
	public void testNumDoors() {
		int count = 0;
		for (int i = 0; i < board.getCells().size(); i++) {
			if (board.getCells().get(i).isDoorway()) {
				count++;
			}
		}
		Assert.assertEquals(NUM_DOORS, count);
	}

	@Test
	public void testDoorDirection() {
		Assert.assertEquals(board.getRoomCellAt(19, 18).getDoorDirection(), RoomCell.DoorDirection.LEFT);
		Assert.assertEquals(board.getRoomCellAt(11, 6).getDoorDirection(), RoomCell.DoorDirection.RIGHT);
		Assert.assertEquals(board.getRoomCellAt(20, 14).getDoorDirection(), RoomCell.DoorDirection.UP);
		Assert.assertEquals(board.getRoomCellAt(4, 6).getDoorDirection(), RoomCell.DoorDirection.DOWN);
		Assert.assertEquals(board.getRoomCellAt(2, 3).getDoorDirection(), RoomCell.DoorDirection.NONE);
	}
	
	@Test
	public void testIsDoorway() {
		Assert.assertEquals(board.getRoomCellAt(2, 12).isDoorway(), false);
		Assert.assertEquals(board.getRoomCellAt(19, 18).isDoorway(), true);
		Assert.assertEquals(board.getRoomCellAt(0, 0).isDoorway(), false);
	}
	
	@Test
	public void testRoomInitial() {
		System.out.println("testRoomInitial()");
		Assert.assertEquals(board.getRoomCellAt(1, 2).getRoomInitial(), 'B');
		Assert.assertEquals(board.getRoomCellAt(2, 14).getRoomInitial(), 'L');
		Assert.assertEquals(board.getRoomCellAt(3,22).getRoomInitial(), 'K');
		Assert.assertEquals(board.getRoomCellAt(10, 2).getRoomInitial(), 'S');
		Assert.assertEquals(board.getRoomCellAt(10, 11).getRoomInitial(), 'X');
		Assert.assertEquals(board.getRoomCellAt(11, 22).getRoomInitial(), 'H');
		Assert.assertEquals(board.getRoomCellAt(16, 2).getRoomInitial(), 'C');
		Assert.assertEquals(board.getRoomCellAt(22, 2).getRoomInitial(), 'R');
		Assert.assertEquals(board.getRoomCellAt(24, 13).getRoomInitial(), 'D');
		Assert.assertEquals(board.getRoomCellAt(24, 24).getRoomInitial(), 'O');
	}
	
	@Test
	public void testCalcIndex() {
		Assert.assertEquals(board.calcIndex(1, 1), 26);
		Assert.assertEquals(board.calcIndex(24, 0), 600);
		Assert.assertEquals(board.calcIndex(0, 24), 24);
		Assert.assertEquals(board.calcIndex(13, 11), 336);
	}
	
	//Tests that the Clue Board file has an incorrect number of columns
	@Test (expected = BadConfigFormatException.class)
	public void testBadColumn() throws BadConfigFormatException, FileNotFoundException{
		System.out.println("testBadColumn()");
		Board boardBadColumn = new Board("ClueBoardBadColumns.cfg", "legend.cfg");
		boardBadColumn.loadBoardConfigFile();
		boardBadColumn.loadLegendConfigFile();
	}
	
	@Test (expected = BadConfigFormatException.class)
	public void testBadRoom() throws BadConfigFormatException, FileNotFoundException{
		System.out.println("testBadRoom()");
		Board boardBadRoom = new Board("ClueBoardBadRoom.cfg", "legend.cfg");
		boardBadRoom.loadBoardConfigFile();
		boardBadRoom.loadLegendConfigFile();
	}
	
	@Test (expected = BadConfigFormatException.class)
	public void testBadLegend() throws BadConfigFormatException, FileNotFoundException{
		System.out.println("testBadLegend()");
		Board boardBadLegend = new Board("ClueBoard.cfg", "legendBadFormat.cfg");
		boardBadLegend.loadBoardConfigFile();
		boardBadLegend.loadLegendConfigFile();
	}
}
