package chs.wumpus.board;

import java.util.List;

import chs.wumpus.hunter.Sensations;

public interface BoardElement {

	public boolean isHole();

	public boolean hasGold();

	public boolean isWumpus();

	public boolean isStart();

	public boolean isWall();

	public List<Sensations> getSensations();

	public String printSensations();

	public void setSensation(Sensations sensation);

	public int getRow();

	public int getColumn();

	public void setRow(int row);

	public void setColumn(int column);
}
