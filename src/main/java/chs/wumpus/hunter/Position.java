package chs.wumpus.hunter;

public class Position {
	private int row;
	private int column;
	private Direction direction;

	public Position(int row, int column, Direction direction) {
		this.row = row;
		this.column = column;
		this.direction = direction;
	}

	public void setNewDirection(Direction newDirection) {
		this.direction = newDirection;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public Direction getDirection() {
		return direction;
	}

	public void turnLeft() {
		this.direction = this.direction.getLeft();
	}

	public void turnRight() {
		this.direction = this.direction.getRight();
	}
}
