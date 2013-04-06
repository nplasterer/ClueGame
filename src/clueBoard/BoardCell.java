package clueBoard;

import java.awt.Color;
import java.awt.Graphics;

public abstract class BoardCell {

	private int cellRow, cellColumn;
	private int index;
	
	public BoardCell() {

	}
	
	
	public boolean isWalkway() {
		return false;
	}
	
	public boolean isRoom() {
		return false;
	}
	
	public boolean isDoorway() {
		return false;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getCellRow() {
		return cellRow;
	}

	public void setCellRow(int cellRow) {
		this.cellRow = cellRow;
	}

	public int getCellColumn() {
		return cellColumn;
	}

	public void setCellColumn(int cellColumn) {
		this.cellColumn = cellColumn;
	}
	
	@Override
	public String toString() {
		return ("" + index);
	}
	
	abstract void draw(Graphics g, Board board);

	public void drawTurn(Graphics g, Board board){
		g.setColor(Color.PINK);
		g.fillRect(getCellColumn()*25, getCellRow()*25, 25, 25);
	}

}
