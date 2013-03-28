package experiment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	private Map<Integer, LinkedList<Integer>> adjMatrix;
	private HashSet<Integer> targets;
	private ArrayList<Integer> grid;
	private ArrayList<Boolean> visited;
	private int columns, rows;
	
	//Default Constructor: Initiates all instance variables and assumes a 25 x 25 board
	// and sets all entries in the ArrayList visited to false
	public IntBoard() {
		grid = new ArrayList<Integer>(625);
		targets = new HashSet<Integer>();
		adjMatrix = new HashMap<Integer, LinkedList<Integer>>();
		columns = 25;
		rows = 25;
		visited = new ArrayList<Boolean>();
		for (int i = 0; i < 625; i++) {
			visited.add(false);
		}
	}
	
	//Parameterized Constructor: Initialized all instance variables and creates a board of specified height and width
	// and sets all entries in the ArrayList visited to false
	public IntBoard(int width, int height) {
		grid = new ArrayList<Integer>(width * height);
		rows = height;
		columns = width;
		targets = new HashSet<Integer>();
		adjMatrix = new HashMap<Integer, LinkedList<Integer>>();
		visited = new ArrayList<Boolean>();
		for (int i = 0; i < width * height; i++) {
			visited.add(false);
		}
	}
	
	public void calcAdjacencies(int index) {
		//adjMatrix = new HashMap<Integer, LinkedList<Integer>>();
		LinkedList<Integer> adjIndexList = new LinkedList<Integer>();
		
		//Left Adjacent
		if (!(index % columns == 0)) {
			if (visited.get(index - 1) == false)
				adjIndexList.add(index - 1);
		} 
		
		//Above Adjacent
		if (!(index >= 0 && index <= columns - 1)) {
			if (visited.get(index - columns) == false)
				adjIndexList.add(index - columns);
		}
		
		//Below Adjacent
		if (!(index >= columns * (rows - 1) && index <= (columns * rows) - 1)) {
			if (visited.get(index + columns) == false)
				adjIndexList.add(index + columns);
		}
		
		//Right Adjacent
		if (!(index % columns == columns - 1)) {
			if (visited.get(index + 1) == false)
				adjIndexList.add(index + 1);
		}
		
		adjMatrix.put(index, adjIndexList);
	}
	
	//Sets up the recursive function
	public void startTargets(int index, int steps) {
		visited.set(index, true);
		calcAdjacencies(index);
		calcTargets(index, steps);
	}
	
	//Through recursion, calculates the target cells available based off of the number of steps and the location of board.
	public void calcTargets(int index, int step) {
		int numOfSteps = step;
		calcAdjacencies(index);
		
		visited.set(index, true);
		for (int i = 0; i < adjMatrix.get(index).size(); i++) {
			visited.set(adjMatrix.get(index).get(i), true);
			if (numOfSteps == 1) {
				targets.add(adjMatrix.get(index).get(i));
			} else {
				calcTargets(adjMatrix.get(index).get(i), numOfSteps - 1);
			}
			visited.set(adjMatrix.get(index).get(i), false);
		}
	}
	
	public HashSet getTargets() {
		return targets;
	}
	
	public Map<Integer, LinkedList<Integer>> getAdjList() {
		return adjMatrix;
	}
	
	//Calculates the index of the cell based off of column and row.
	public int calcIndex(int column, int row) {
		return ((row * columns) + column);
	}
	
}
