package chs.wumpus.hunter;

public enum Direction {
	NORTH, EAST, SOUTH, WEST;

	public Direction getLeft() {
		int leftPosition = this.ordinal() - 1;
		if (leftPosition < 0) {
			leftPosition = Direction.values().length - 1;
		}
		return Direction.values()[leftPosition];
	}

	public Direction getRight() {
		int rightPosition = this.ordinal() + 1;
		if (rightPosition >= Direction.values().length) {
			rightPosition = 0;
		}
		return Direction.values()[rightPosition];
	}

}
