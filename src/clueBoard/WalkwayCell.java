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
		g.fillRect(getCellColumn()*25, getCellRow()*25, 25, 25);
		g.setColor(Color.BLACK);
		g.drawRect(getCellColumn()*25, getCellRow()*25, 25, 25);
	}
	

}
