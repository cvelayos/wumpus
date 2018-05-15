package chs.wumpus.board;

import java.util.ArrayList;
import java.util.List;

import chs.wumpus.hunter.Sensations;

public class EmptyElement implements BoardElement {

	private List<Sensations> hunterSensations = new ArrayList<>();
	private int row;
	private int column;

	@Override
	public boolean isHole() {
		return false;
	}

	@Override
	public boolean hasGold() {
		return false;
	}

	@Override
	public boolean isWumpus() {
		return false;
	}

	@Override
	public boolean isStart() {
		return false;
	}

	@Override
	public List<Sensations> getSensations() {
		return this.hunterSensations;
	}

	@Override
	public void setSensation(Sensations sensation) {
		this.hunterSensations.add(sensation);
	}

	@Override
	public int getRow() {
		return this.row;
	}

	@Override
	public int getColumn() {
		return this.column;
	}

	@Override
	public void setRow(int row) {
		this.row = row;
	}

	@Override
	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public String printSensations() {
		StringBuilder sb = new StringBuilder();
		sb.append("You feel...\n");
		for (Sensations sensation : hunterSensations) {
			sb.append(sensation);
		}
		return sb.toString();
	}

	@Override
	public boolean isWall() {
		return false;
	}

}
