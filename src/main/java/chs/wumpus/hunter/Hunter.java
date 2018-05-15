package chs.wumpus.hunter;

public class Hunter {

	private int arrows;
	private Position position;

	public Hunter(int arrows) {
		this.arrows = arrows;
	}

	public int getArrows() {
		return this.arrows;
	}

	public boolean hasArrows() {
		return this.arrows > 0;
	}

	public void throwArrow() {
		this.arrows--;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Position walk() {
		switch (position.getDirection()) {
		case NORTH:
			return new Position(position.getRow() - 1, position.getColumn(), position.getDirection());
		case EAST:
			return new Position(position.getRow(), position.getColumn() + 1, position.getDirection());
		case SOUTH:
			return new Position(position.getRow() + 1, position.getColumn(), position.getDirection());
		default:
			return new Position(position.getRow(), position.getColumn() - 1, position.getDirection());
		}
	}

	public void turnLeft() {
		this.position.turnLeft();
	}

	public void turnRight() {
		this.position.turnRight();
	}
}
