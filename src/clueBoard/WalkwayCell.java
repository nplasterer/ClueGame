package clueBoard;

import java.awt.Color;
import java.awt.Graphics;

public class WalkwayCell extends BoardCell {

	public WalkwayCell() {
	
	}
	
	@Override
	public boolean isWalkway() {
		return true;
	}
	
	@Override
	public void draw(Graphics g, Board board) {
		g.setColor(Color.YELLOW);
		g.fillRect(getCellColumn()*20, getCellRow()*20, 20, 20);
	}
}
