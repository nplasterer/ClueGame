package test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import junit.framework.Assert;
import experiment.IntBoard;
import org.junit.*;


public class TestIntBoard {

	private IntBoard board;
	
	@Before
	public void setUp() {
		System.out.println("Setting up");
		board = new IntBoard();
	}
	
	@Test
	public void testCalcIndex() {
		Assert.assertEquals(board.calcIndex(1, 1), 26);
		Assert.assertEquals(board.calcIndex(0, 24), 600);
		Assert.assertEquals(board.calcIndex(24, 0), 24);
		Assert.assertEquals(board.calcIndex(11, 13), 336);
	}
	
	@Test
	public void testCalcAdjacencyTopLeft() {
		//Test for Top Left Corner
		board.calcAdjacencies(0);
		LinkedList<Integer> answers = board.getAdjList().get(0);
		Assert.assertTrue(answers.contains(1));
		Assert.assertTrue(answers.contains(25));
		Assert.assertEquals(2, answers.size());
	}

	@Test
	public void testCalcAdjacencyBottRight() {	
		//Test for Bottom Right Corner
		board.calcAdjacencies(624);
		LinkedList<Integer> answers = board.getAdjList().get(624);
		Assert.assertTrue(answers.contains(599));
		Assert.assertTrue(answers.contains(623));
		Assert.assertEquals(2, answers.size());
	}

	@Test
	public void testCalcAdjacencyBottLeft() {
		//Test for Bottom Left Corner
		board.calcAdjacencies(600);
		LinkedList<Integer> answers = board.getAdjList().get(600);		
		Assert.assertTrue(answers.contains(575));
		Assert.assertTrue(answers.contains(601));
		Assert.assertEquals(2, answers.size());
	}

	@Test
	public void testCalcAdjacencyTopRight() {
		//Test for Top Right Corner
		board.calcAdjacencies(24);
		LinkedList<Integer> answers = board.getAdjList().get(24);
		Assert.assertTrue(answers.contains(23));
		Assert.assertTrue(answers.contains(49));
		Assert.assertEquals(2, answers.size());
	}

	@Test
	public void testCalcAdjacencyRightEdge() {
		//Test for Right Edge
		board.calcAdjacencies(249);
		LinkedList<Integer> answers = board.getAdjList().get(249);
		Assert.assertTrue(answers.contains(224));
		Assert.assertTrue(answers.contains(248));
		Assert.assertTrue(answers.contains(274));
		Assert.assertEquals(3, answers.size());
	}

	@Test
	public void testCalcAdjacencyLeftEdge() {
		//Test for Left Edge
		board.calcAdjacencies(400);
		LinkedList<Integer> answers = board.getAdjList().get(400);
		Assert.assertTrue(answers.contains(375));
		Assert.assertTrue(answers.contains(401));
		Assert.assertTrue(answers.contains(425));
		Assert.assertEquals(3, answers.size());
	}

	@Test
	public void testCalcAdjacencyColTwoRowTwo() {
		//Test for Second Column Second Row
		board.calcAdjacencies(26);
		LinkedList<Integer> answers = board.getAdjList().get(26);
		Assert.assertTrue(answers.contains(1));
		Assert.assertTrue(answers.contains(25));
		Assert.assertTrue(answers.contains(51));
		Assert.assertTrue(answers.contains(27));
		Assert.assertEquals(4, answers.size());
	}

	@Test
	public void testCalcAdjacencySecToLastColAndRow() {
		//Test for Second To Last Column Second To Last Row
		board.calcAdjacencies(598);
		LinkedList<Integer> answers = board.getAdjList().get(598);
		Assert.assertTrue(answers.contains(573));
		Assert.assertTrue(answers.contains(597));
		Assert.assertTrue(answers.contains(623));
		Assert.assertTrue(answers.contains(599));
		Assert.assertEquals(4, answers.size());
	}

	@Test
	public void testCalcAdjacencyMiddleOfGrid() {
		//Test for Middle of Grid
		board.calcAdjacencies(312);
		LinkedList<Integer> answers = board.getAdjList().get(312);
		Assert.assertTrue(answers.contains(287));
		Assert.assertTrue(answers.contains(311));
		Assert.assertTrue(answers.contains(337));
		Assert.assertTrue(answers.contains(313));
		Assert.assertEquals(4, answers.size());
	}
	
	@Test
	public void testTargets0_3() {
		board.startTargets(0, 3);
		Set targets = board.getTargets();
		System.out.println(targets);
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(3));
		Assert.assertTrue(targets.contains(27));
		Assert.assertTrue(targets.contains(51));
		Assert.assertTrue(targets.contains(25));
		Assert.assertTrue(targets.contains(75));
		Assert.assertTrue(targets.contains(1));
	}
	
	@Test
	public void testTargets600_3() {
		board.startTargets(600, 3);
		Set targets = board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(525));
		Assert.assertTrue(targets.contains(575));
		Assert.assertTrue(targets.contains(601));
		Assert.assertTrue(targets.contains(551));
		Assert.assertTrue(targets.contains(577));
		Assert.assertTrue(targets.contains(603));
	}
	
	@Test
	public void testTargets624_3() {
		board.startTargets(624, 3);
		Set targets = board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(549));
		Assert.assertTrue(targets.contains(573));
		Assert.assertTrue(targets.contains(599));
		Assert.assertTrue(targets.contains(623));
		Assert.assertTrue(targets.contains(597));
		Assert.assertTrue(targets.contains(621));
	}
	
	@Test
	public void testTargets11_3() {
		board.startTargets(11, 3);
		Set targets = board.getTargets();
		Assert.assertEquals(10, targets.size());
		Assert.assertTrue(targets.contains(8));
		Assert.assertTrue(targets.contains(34));
		Assert.assertTrue(targets.contains(10));
		Assert.assertTrue(targets.contains(12));
		Assert.assertTrue(targets.contains(38));
		Assert.assertTrue(targets.contains(14));
		Assert.assertTrue(targets.contains(86));
		Assert.assertTrue(targets.contains(60));
		Assert.assertTrue(targets.contains(62));
		Assert.assertTrue(targets.contains(36));
	}
	
	@Test
	public void testTargets499_3() {
		board.startTargets(499, 3);
		Set targets = board.getTargets();
		Assert.assertEquals(10, targets.size());
		Assert.assertTrue(targets.contains(424));
		Assert.assertTrue(targets.contains(448));
		Assert.assertTrue(targets.contains(474));
		Assert.assertTrue(targets.contains(498));
		Assert.assertTrue(targets.contains(524));
		Assert.assertTrue(targets.contains(548));
		Assert.assertTrue(targets.contains(574));
		Assert.assertTrue(targets.contains(496));
		Assert.assertTrue(targets.contains(472));
		Assert.assertTrue(targets.contains(522));
	}
	
	@Test
	public void testTargets238_3() {
		board.startTargets(283, 3);
		Set targets = board.getTargets();
		Assert.assertEquals(16, targets.size());
		Assert.assertTrue(targets.contains(208));
		Assert.assertTrue(targets.contains(232));
		Assert.assertTrue(targets.contains(234));
		Assert.assertTrue(targets.contains(256));
		Assert.assertTrue(targets.contains(258));
		Assert.assertTrue(targets.contains(260));
		Assert.assertTrue(targets.contains(280));
		Assert.assertTrue(targets.contains(282));
		Assert.assertTrue(targets.contains(284));
		Assert.assertTrue(targets.contains(286));
		Assert.assertTrue(targets.contains(306));
		Assert.assertTrue(targets.contains(308));
		Assert.assertTrue(targets.contains(310));
		Assert.assertTrue(targets.contains(332));
		Assert.assertTrue(targets.contains(334));
		Assert.assertTrue(targets.contains(358));
	}
	
}
