/*
 * Authors Jeremiah Jekich, Daniel Jost
 * Failing Tests for Clue Board Game Part II
 */
package test;

import java.util.LinkedList;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clueBoard.Board;
import clueBoard.BoardCell;

public class TestPaths {

	private static Board board;

	@BeforeClass
	public static void setUp() {
		System.out.println("Setting up!");
		board = new Board("ClueBoard.cfg","legend.cfg");
		board.loadConfigFiles();
		board.calcAdjacencies();
	}
	
	//Test surrounded by 4 walkways
	@Test
	public void testAdjacentWalkwaysFour() {
		//System.out.println("testAdjacentWalkwaysFour()");
		LinkedList<Integer> adjacentList = board.getAdjList(board.calcIndex(6, 7));
		Assert.assertTrue(adjacentList.contains(board.calcIndex(6, 6)));
		Assert.assertTrue(adjacentList.contains(board.calcIndex(6, 8)));
		Assert.assertTrue(adjacentList.contains(board.calcIndex(5, 7)));
		Assert.assertTrue(adjacentList.contains(board.calcIndex(7, 7)));
		Assert.assertEquals(4, adjacentList.size());
	}

	//Test Top Edge With 2 Adjacencies
	@Test
	public void testAdjacentWalkwaysTopEdge() {
		//System.out.println("testAdjacentWalkwaysTopEdge()");
		LinkedList<Integer> adjacentList = board.getAdjList(board.calcIndex(0, 8));
		Assert.assertTrue(adjacentList.contains(board.calcIndex(0, 9)));
		Assert.assertTrue(adjacentList.contains(board.calcIndex(1, 8)));
		Assert.assertEquals(adjacentList.size(), 2);
	}

	//Test Left Edge with 1 Adjacency
	@Test
	public void testAdjacentWalkwaysLeftEdge() {
		//System.out.println("testAdjacentWalkwaysLeftEdge()");
		LinkedList<Integer> adjacentList = board.getAdjList(board.calcIndex(14, 0));
		Assert.assertTrue(adjacentList.contains(board.calcIndex(14, 1)));
		Assert.assertEquals(adjacentList.size(), 1);
	}

	//Test Bottom Edge with 3 Adjacencies
	@Test
	public void testAdjacentWalkwaysBottomEdge() {
		//System.out.println("testAdjacentWalkwaysBottomEdge()");
		LinkedList<Integer> adjacentList = board.getAdjList(board.calcIndex(24, 17));
		Assert.assertTrue(adjacentList.contains(board.calcIndex(24, 16)));
		Assert.assertTrue(adjacentList.contains(board.calcIndex(24, 18)));
		Assert.assertTrue(adjacentList.contains(board.calcIndex(23, 17)));
		Assert.assertEquals(adjacentList.size(), 3);
	}

	//Test Right Edge with 3 Adjacencies
	@Test
	public void testAdjacentWalkwaysRightEdge() {
		//System.out.println("testAdjacentWalkwaysRightEdge()");
		LinkedList<Integer> adjacentList = board.getAdjList(board.calcIndex(17, 24));
		Assert.assertTrue(adjacentList.contains(board.calcIndex(17, 23)));
		Assert.assertTrue(adjacentList.contains(board.calcIndex(16, 24)));
		Assert.assertTrue(adjacentList.contains(board.calcIndex(18, 24)));
		Assert.assertEquals(adjacentList.size(), 3);
	}

	//Test Walkway next to RoomCell but not Doorway, 3 Adjacencies
	@Test
	public void testAdjacentWalkwaysRoomCellThree() {
		//System.out.println("testAdjacentWalkwaysRoomCellThree()");
		LinkedList<Integer> adjacentList = board.getAdjList(board.calcIndex(7, 11));
		Assert.assertTrue(adjacentList.contains(board.calcIndex(7, 10)));
		Assert.assertTrue(adjacentList.contains(board.calcIndex(7, 12)));
		Assert.assertTrue(adjacentList.contains(board.calcIndex(8, 11)));
		Assert.assertEquals(adjacentList.size(), 3);
	}

	//Test Walkway next to RoomCell but not Doorway, 1 Adjacency
	@Test
	public void testAdjacentWalkwaysRoomCellOne() {
		//System.out.println("testAdjacentWalkwaysRoomCellOne()");
		LinkedList<Integer> adjacentList = board.getAdjList(board.calcIndex(19, 24));
		Assert.assertTrue(adjacentList.contains(board.calcIndex(18, 24)));
		Assert.assertEquals(adjacentList.size(), 1);
	}

	//Test Walkway next to Doorway, 3 Adjacencies
	@Test
	public void testAdjacentWalkwaysAtDoorwayThree() {
		//System.out.println("testAdjacentWalkwaysAtDoorwayThree()");
		LinkedList<Integer> adjacentList = board.getAdjList(board.calcIndex(19, 14));
		Assert.assertTrue(adjacentList.contains(board.calcIndex(19, 15)));
		Assert.assertTrue(adjacentList.contains(board.calcIndex(18, 14)));
		Assert.assertTrue(adjacentList.contains(board.calcIndex(20, 14)));
		Assert.assertEquals(adjacentList.size(), 3);
	}

	//Test Walkway next to Doorway, 3 Adjacencies not including Doorway (Not correct direction)
	@Test
	public void testAdjacentWalkwaysNearNonAdjacentDoorwayThree() {
		//System.out.println("testAdjacentWalkwaysNearAdjacentDoorwayThree()");
		LinkedList<Integer> adjacentList = board.getAdjList(board.calcIndex(18, 18));
		Assert.assertTrue(adjacentList.contains(board.calcIndex(18, 17)));
		Assert.assertTrue(adjacentList.contains(board.calcIndex(18, 19)));
		Assert.assertTrue(adjacentList.contains(board.calcIndex(17, 18)));
		Assert.assertEquals(adjacentList.size(), 3);
	}

	//Test Doorway, 1 Adjacency
	@Test
	public void testAdjacentWalkwaysAtDoorwayOnePartOne() {
		//System.out.println("testAdjacentWalkwaysAtDoorwayOnePartOne()");
		LinkedList<Integer> adjacentList = board.getAdjList(board.calcIndex(4, 6));
		Assert.assertTrue(adjacentList.contains(board.calcIndex(5, 6)));
		Assert.assertEquals(adjacentList.size(), 1);
	}

	//Test Doorway, 1 Adjacency
	@Test
	public void testAdjacentWalkwaysAtDoorwayOnePartTwo() {
		//System.out.println("testAdjacentWalkwaysAtDoorwayOnePartTwo()");
		LinkedList<Integer> adjacentList = board.getAdjList(board.calcIndex(17, 5));
		Assert.assertTrue(adjacentList.contains(board.calcIndex(18, 5)));
		Assert.assertEquals(adjacentList.size(), 1);
	}
	
	//Test Inside Room Adjacency, 1 Door
	@Test
	public void testAdjacentInsideRoomOneDoor() {
		//System.out.println("testAdjacentInsideRoomOneDoor()");
		LinkedList<Integer> adjacentList = board.getAdjList(board.calcIndex(10, 2));
		Assert.assertEquals(adjacentList.size(), 0);
	}

	//Test Inside Room Adjacencies, 2 Doors
	@Test
	public void testAdjacentInsideRoomTwoDoors() {
		//System.out.println("testAdjacentInsideRoomTwoDoors()");
		LinkedList<Integer> adjacentList = board.getAdjList(board.calcIndex(4, 13));
		Assert.assertEquals(adjacentList.size(), 0);
	}
	
	@Test
	public void testTargetsAlongWalkwaysOneStep() {
		//System.out.println("testTargetsAlongWalkwaysOneStep()");
		board.calcTargets(7, 15, 1);
		Set<BoardCell> targets = board.getTargets();
		System.out.println(targets);
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 14))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 16))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 15))));
	}

	@Test
	public void testTargetsAlongWalkwaysTwoSteps() {
		//System.out.println("testTargetsAlongWalkwaysTwoSteps()");
		board.calcTargets(19, 14, 2);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(17, 14))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(18, 15))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(19, 16))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(20, 14))));
	}

	@Test
	public void testTargetsAlongWalkwaysThreeSteps() {
		//System.out.println("testTargetsAlongWalkwaysThreeSteps()");
		board.calcTargets(7, 5, 3);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(7, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 2))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 5))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(5, 6))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 6))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 7))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 7))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 8))));
	}

	@Test
	public void testTargetsAlongWalkwaysFourSteps() {
		//System.out.println("testTargetsAlongWalkwaysFourSteps()");
		board.calcTargets(8, 17, 4);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(19, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(4, 17))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(5, 18))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 17))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 19))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 18))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 19))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 21))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(9, 20))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(9, 18))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(9, 16))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(9, 14))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 15))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 13))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 16))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 14))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(10, 17))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(11, 16))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(11, 18))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(12, 17))));
	}

	@Test
	public void testTargetsIntoRoomOneStep() {
		//System.out.println("testTargetsIntoRoomOneStep()");
		board.calcTargets(17, 19, 1);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 19))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(17, 18))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(17, 20))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(18, 19))));
	}

	@Test
	public void testTargetsIntoRoomTwoStep() {
		//System.out.println("testTargetsIntoRoomTwoStep()");
		board.calcTargets(8, 17, 2);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(8, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 17))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 16))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(9, 18))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 18))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(9, 16))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 15))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 19))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(10, 17))));
	}

	@Test
	public void testTargetsLeavingRoomOneStep() {
		//System.out.println("testTargetsLeavingRoomOneStep()");
		board.calcTargets(19, 18, 1);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(1, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(19, 17))));
	}

	@Test
	public void testTargetsLeavingRoomTwoSteps() {
		//System.out.println("testTargetsLeavingRoomTwoSteps()");
		board.calcTargets(21, 5, 2);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(20, 6))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(21, 7))));
	}
	
	@Test
	public void testGetRow() {
		Assert.assertEquals(board.getRow(514), 20);
		Assert.assertEquals(board.getRow(323), 12);
		
	}
	
	@Test
	public void testGetColumns() {
		Assert.assertEquals(board.getColumn(514), 14);
		Assert.assertEquals(board.getColumn(323), 23);
	}
}
