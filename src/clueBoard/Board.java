package clueBoard;

import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JPanel;

import clueBoard.RoomCell.DoorDirection;

public class Board extends JPanel {

	
	private ArrayList<BoardCell> cells;
	private Map<Character, String> rooms;
	private int numRows;
	private int numColumns;
	Scanner reader;
	FileReader inputFileReader;
	String boardFile, legendFile; 
	private Map<Integer, LinkedList<Integer>> adjMatrix;
	private HashSet<BoardCell> targets;
	private ArrayList<Integer> grid;
	private ArrayList<Boolean> visited;

	
	/******************************************************************************************************************
	 * Board() 	- default constructor. Initializes:
	 * 				cells, rooms, boardFile, legendFile, grid, targets, adjMatrix, visited, and doorIndeces
	 * 			- boardFile and legendFile are hard-coded for inilization
	 *****************************************************************************************************************/
	public Board() {
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character, String>();
		boardFile = "ClueLayout.csv";
		legendFile = "ClueLegend.txt";
		grid = new ArrayList<Integer>();
		targets = new HashSet<BoardCell>();
		adjMatrix = new HashMap<Integer, LinkedList<Integer>>();
		visited = new ArrayList<Boolean>();
	}

	/******************************************************************************************************************
	 * Board(String boardFile, String legendFile) 	- parameterized constructor. Initializes:
	 * 													cells, rooms, boardFile, legendFile, grid, targets, adjMatrix,
	 * 													visited, and doorIndeces
	 * 												- boardFile and legendFile are initialized with the parameters
	 *****************************************************************************************************************/
	public Board(String boardFile, String legendFile) {
		this.boardFile = boardFile;
		this.legendFile = legendFile;
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character, String>();
		grid = new ArrayList<Integer>();
		targets = new HashSet<BoardCell>();
		adjMatrix = new HashMap<Integer, LinkedList<Integer>>();
		visited = new ArrayList<Boolean>();
	}

	
	/******************************************************************************************************************
	 * loadConfigFiles() 	- calls loadBoardConfigFile() and loadLegendConfigFile()
	 * 						- catches FileNotFoundException, BadConfigFormatException
	 *****************************************************************************************************************/
	public void loadConfigFiles() {
		try {
			loadBoardConfigFile();
			loadLegendConfigFile();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (BadConfigFormatException e) {
			System.out.println(e.getMessage());
		}
	}

	
	/******************************************************************************************************************
	 * loadBoardConfigFile() 	- counts the number of rows and columns, storing the values into numRows and numColumns\
	 * 							- populates cells ArrayList with Rooms or Walkways depending upon file contents
	 * 							- checks the cells ArrayList for bad format, throwing exceptions is it does
	 *****************************************************************************************************************/
	public void loadBoardConfigFile() throws FileNotFoundException, BadConfigFormatException {
		inputFileReader = new FileReader(this.boardFile);
		reader = new Scanner(inputFileReader);
		String fileLine;
		int numCommas = 0, prevNumCommas = 0, rows = 1;

		//while the file has another line, count the number of lines and make sure there is a consistent amount of rows
		while (reader.hasNextLine()) {
			numCommas = 0;
			fileLine = reader.nextLine();
			for (int i = 0; i < fileLine.length(); i++) {
				if (fileLine.charAt(i) == ',') {
					if (rows == 1) {
						prevNumCommas = prevNumCommas + 1;
					}
					numCommas = numCommas + 1;
				}
			}
			if (numCommas != prevNumCommas) {
				throw new BadConfigFormatException("There is at least one row containing an inconsistent number of columns.");
			}
			rows++;
			prevNumCommas = numCommas;
		}

		//Set the number of rows and columns based on the number of commas in each row of the config file and the total number of rows, respectively
		numColumns = numCommas + 1;
		numRows = rows - 1;

		//Declare a String array to store the tokens of the split row into
		String[] fileLineSplit = new String[numCommas + 1];


		//Pass in new FileReader to the scanner for second pass through the file
		FileReader inputFileReader2 = new FileReader(boardFile);
		reader = new Scanner(inputFileReader2);

		BoardCell tempCell;
		int indexCount = 0;
		
		//While the file has another line, read in the line and split the line via commas, then add the appropriate type of
		//BoardCell depending on it's first character
		while (reader.hasNextLine()) {
			fileLine = reader.nextLine();
			fileLineSplit = fileLine.split(",");
			for (int i = 0; i < fileLineSplit.length; i++) {
				if (fileLineSplit[i].charAt(0) == 'W') {
					tempCell = new WalkwayCell();
					tempCell.setIndex(indexCount);
					cells.add(tempCell);
				} else if (fileLineSplit[i].charAt(0) == 'C' || fileLineSplit[i].charAt(0) == 'K' || 
						fileLineSplit[i].charAt(0) == 'B' || fileLineSplit[i].charAt(0) == 'R' ||
						fileLineSplit[i].charAt(0) == 'L' || fileLineSplit[i].charAt(0) == 'S' || 
						fileLineSplit[i].charAt(0) == 'D' || fileLineSplit[i].charAt(0) == 'O' ||
						fileLineSplit[i].charAt(0) =='H' || fileLineSplit[i].charAt(0) == 'X') {
					tempCell = new RoomCell(fileLineSplit[i]);
					tempCell.setIndex(indexCount);
					cells.add(tempCell);
				} else {
					throw new BadConfigFormatException("Board contains cell with invalid initial.");
				}
				indexCount++;
			}
			
		}
		
		//Checks each door on the board and makes sure it is not in a roow, otherwise it throws an exception
		for (int i = 0; i < cells.size(); i++) {
			if (cells.get(i).isDoorway() == true) {
				if (!(cells.get(i-1).isWalkway() || cells.get(i+1).isWalkway() ||
						cells.get(i-numColumns).isWalkway() || cells.get(i+numColumns).isWalkway())) {
					throw new BadConfigFormatException("Doorway found inside room.");
				}
			}
		}
		
		//set cell rows and columns
		int indexTracker = 0;
		for(int row = 0;row < numRows;row++){
            for(int col = 0; col < numColumns;col++){
                cells.get(indexTracker).setCellRow(row);
                cells.get(indexTracker).setCellColumn(col);
            }
            indexTracker++;
		}
		
		//Populates the the visited list to be false for all cells
		for (int i = 0; i < numRows * numColumns; i++) {
			visited.add(false);
		}
	}

	/******************************************************************************************************************
	 * loadLegendConfigFile() 	- counts the number of entries in the legend from the file
	 * 							- reads in the room initials and names and stores them into the rooms map
	 *****************************************************************************************************************/
	public void loadLegendConfigFile() throws FileNotFoundException, BadConfigFormatException {
		
		//Declare FileReader, Scanner, and local variables
		inputFileReader = new FileReader(legendFile);
		reader = new Scanner(inputFileReader);
		String entry;
		int numEntries = 0, currentRow = 0;
		
		//count the number of lines
		while (reader.hasNextLine()) {			
			numEntries++;
			reader.nextLine();
		}
		
		//Declare new FileReader with same file in order to reaccess the file
		FileReader inputFileReader2 = new FileReader(legendFile);
		reader = new Scanner(inputFileReader2);
		
		String[] entries = new String[numEntries];
	
		//While the file has another line, read in the line and split it via commas. If there are 2 tokens in the line,
		//the two tokens are put into the room HashMap with the first token as its key and the second token as its value
		while (reader.hasNextLine()) {
			currentRow = currentRow + 1;
			entry = reader.nextLine();
			entries = entry.split(", ");
			if (entries.length == 2) {
				rooms.put(entries[0].charAt(0), entries[1]);
			} else {
				throw new BadConfigFormatException("Row " + currentRow + " of legend file does not contain exactly two items.");
			}
		}
		
		reader.close();
		
	}

	/******************************************************************************************************************
	 * calcIndex(int row, int column)	- calculates the index of a cell in the cells ArrayList based on row and column
	 *****************************************************************************************************************/
	public int calcIndex(int row, int column) {
		return ((row * numColumns) + column);
	}

	/******************************************************************************************************************
	 * getRoomCellAt(int row, int column)	- returns the RoomCell located at the given row and column
	 * 										- if the given cell location is a walkway, returns null
	 *****************************************************************************************************************/
	public RoomCell getRoomCellAt(int row, int column) {
		int i = calcIndex(row, column);
		RoomCell cell;
		
		//if the cell at (row, column) is not a walkway, return the cell
		if (!cells.get(i).isWalkway()) {
			cell = (RoomCell) cells.get(i);
			return cell;
		} 
		
		//else return null
		return null;
	}

	
	/******************************************************************************************************************
	 * getRoomCellAt(int index)	- returns the RoomCell located at the given index
	 * 							- if the given cell location is a walkway, returns null
	 *****************************************************************************************************************/
	public RoomCell getRoomCellAt(int index) {
		RoomCell cell;
		
		//if the cell at index is not a walkway, return the cell
		if (!cells.get(index).isWalkway()) {
			cell = (RoomCell) cells.get(index);
			return cell;
		} 
		
		//else return null
		return null;
	}
	
	/******************************************************************************************************************
	 * getCellAt(int index) - returns the BoardCell located at the given index
	 *****************************************************************************************************************/
	public BoardCell getCellAt(int index) {
		return cells.get(index);
	}
	
	/******************************************************************************************************************
	 * getCells() - returns the cells ArrayList of BoardCells
	 *****************************************************************************************************************/
	public ArrayList<BoardCell> getCells() {
		return cells;
	}

	/******************************************************************************************************************
	 * getRooms() - returns the rooms Map of Characters and associated Strings
	 *****************************************************************************************************************/
	public Map<Character, String> getRooms() {
		return rooms;
	}

	/******************************************************************************************************************
	 * getNumRows() - returns numRows
	 *****************************************************************************************************************/
	public int getNumRows() {
		return numRows;
	}

	/******************************************************************************************************************
	 * getNumColumns() - returns numColumns
	 *****************************************************************************************************************/
	public int getNumColumns() {
		return numColumns;
	}
	
	/******************************************************************************************************************
	 * calcAdjacencies()	- populates the adjMatrix Map of Integers and associated LinkedList<Integer>s
	 * 						- calculates the adjacent cells of each cell on the board
	 *****************************************************************************************************************/
	public void calcAdjacencies() {
		LinkedList<Integer> adjIndexList = new LinkedList<Integer>();

		//for each index on the board
		for (int index = 0; index < numColumns * numRows; index++) {
			adjIndexList = new LinkedList<Integer>();
			
			//Left Adjacent Check
			//if the cell is not on the left edge
			if (!(index % numColumns == 0)) {
				
				//if the cell is a Doorway
				if (cells.get(index).isDoorway()) {
					
					//and the doorway faces LEFT
					if (getRoomCellAt(index).getDoorDirection() == DoorDirection.LEFT) {
						
						//and the cell to the left is not a Room, add it to the adjacency list
						if (cells.get(index-1).isRoom() == false) {
							adjIndexList.add(index - 1);
						}
					} 
				}
				
				//else if the cell is not a doorway, then if the left adjacent cell is a walkway or not a room, or the left
				//adjacent cell is a doorway and it's direction is right, add the left adjacent cell to the adjacency list
				else if((cells.get(index - 1).isWalkway() && !cells.get(index).isRoom()) || 
							(cells.get(index - 1).isDoorway() && 
									getRoomCellAt(index - 1).getDoorDirection() == DoorDirection.RIGHT)) {
						adjIndexList.add(index - 1);
					}
			} 
			
			//Above Adjacent Check
			//if the cell is not on the top edge
			if (!(index >= 0 && index <= numColumns - 1)) {
				
				//if the cell is a doorway
				if (cells.get(index).isDoorway()) {
					
					//then if the door's direction is up and the bottom adjacent cell is not a room, add the above adjacent cell
					//to the adjacency list
					if (getRoomCellAt(index).getDoorDirection() == DoorDirection.UP) {
						if (cells.get(index - numColumns).isRoom() == false) {
							adjIndexList.add(index - numColumns);
						}
					}
				}
				
				//else if the cell is not a doorway, then if the above adjacent cell is a walkway or not a room, or the
				//above adjacent cell is a doorway and it's direction is down, add the above adjacent cell to the adjacency list
				else if((cells.get(index - numColumns).isWalkway() && !cells.get(index).isRoom()) || 
							(cells.get(index - numColumns).isDoorway() &&
								getRoomCellAt(index - numColumns).getDoorDirection() == DoorDirection.DOWN)) {
						adjIndexList.add(index - numColumns);
					}
			}
			
			//Below Adjacent
			//if the cell is not on the bottom edge
			if (!(index >= numColumns * (numRows - 1) && index <= (numColumns * numRows) - 1)) {
				
				//if the cell is a doorway
				if (cells.get(index).isDoorway()) {
					
					//and the door's direction is down
					if (getRoomCellAt(index).getDoorDirection() == DoorDirection.DOWN) {
						
						//and the below adjacent cell is not a room, add the below adacent cell to the adjacency list
						if (cells.get(index + numColumns).isRoom() == false) {
							adjIndexList.add(index + numColumns);
						}
					}
				}
				
				//else if the cell is not a doorway, then if the below adjacent cell is a walkway or not a room, or the
				//below adjacent cell is a doorway and it's direction up, add the below adjacent cell to the adjacency list
				else if((cells.get(index + numColumns).isWalkway() && !cells.get(index).isRoom()) || 
							(cells.get(index + numColumns).isDoorway() && 
									getRoomCellAt(index + numColumns).getDoorDirection() == DoorDirection.UP)) {
						adjIndexList.add(index + numColumns);
					}
			}
			
			//Right Adjacent
			//if the cell is not on the right edge
			if (!(index % numColumns == numColumns - 1)) {
				
				//if the cell is a doorway
				if (cells.get(index).isDoorway()) {
					
					//and the door's direction is right
					if (getRoomCellAt(index).getDoorDirection() == DoorDirection.RIGHT) {
						
						//and the right adjacent cell is not a room, add the right adjacent cell to the adjacency list
						if (cells.get(index + 1).isRoom() == false) {
							adjIndexList.add(index + 1);
						}
					}
				}
				//else if the cell is not a doorway, then if the right adjacent cell is a walkway or not a room, or the
				//right adjacent cell is a doorway and it's direction left, add the right adjacent cell to the adjacency list
				else if((cells.get(index + 1).isWalkway() && !cells.get(index).isRoom()) || 
							(cells.get(index + 1).isDoorway() && 
									getRoomCellAt(index + 1).getDoorDirection() == DoorDirection.LEFT)) {
						adjIndexList.add(index + 1);
					}
			}
			
			//Store the local adjacency list into the adjacencies Map at the correct index
			adjMatrix.put(index, adjIndexList);
		
		}
	}
	
	/******************************************************************************************************************
	 * calcTargets() 	- sets up variables and data for the recursive call to calcTargetsRec()
	 *****************************************************************************************************************/
	public void calcTargets(int row, int column, int steps) {
		targets = new HashSet<BoardCell>();
		int index = calcIndex(row, column);
		visited.set(index, true);
		calcAdjacencies();
		calcTargetsRec(row, column, steps);
	}
		
	/******************************************************************************************************************
	 * calcTargetsRec() 	- Through recursion, calculates the target cells available based on the number of steps
	 * 							 and the row and column of the starting cell on the board.
	 *****************************************************************************************************************/
	public void calcTargetsRec(int row, int column, int step) {
		LinkedList<Integer> adjCells = adjMatrix.get(calcIndex(row,column));
		
		//sets the current cell to visited
		visited.set(calcIndex(row,column), true);
		
		//for each adjacent cell
		for (Integer i : adjCells) {
			
			//if the cell has not been visited
			if (visited.get(i) == false) {
				
				//set the cell to visited
				visited.set(i, true);
				
				//if max number of steps have been taken, add the cell to list of targets
				if (step == 1) {
					targets.add(cells.get(i));
					
				//else if the cell is a doorway, add the doorway to the list of targets	
				} else if (getCellAt(i).isDoorway()) {
					targets.add(cells.get(i));
					
				//else if there are still steps to be taken, calculate targets of the current adjacent cell with one less step	
				} else {
					calcTargetsRec(getRow(i), getColumn(i), step - 1);
				}
				
				//sets the current adjacent cell back to false after targets have been calculated
				visited.set(i, false);
			}
		}
	}
	
	public void paintComponents(Graphics g){
		
	}

	/******************************************************************************************************************
	 * getTargets() - returns the targets HashSet
	 *****************************************************************************************************************/
	public HashSet getTargets() {
		return targets;
	}
	
	/******************************************************************************************************************
	 * getRow() - calculates the row of the cell given the index
	 *****************************************************************************************************************/
	public int getRow(int index) {
		return index / numColumns;
	}
	
	/******************************************************************************************************************
	 * getColumn() - calculates the column of the cell given the index
	 *****************************************************************************************************************/
	public int getColumn(int index) {
		return index % numColumns;
	}
	
	/******************************************************************************************************************
	 * getAdjList(int index) - returns a list of cells adjacent to the cell located at the given index 
	 *****************************************************************************************************************/
	public LinkedList<Integer> getAdjList(int index) {
		return adjMatrix.get(index);
	}

}
