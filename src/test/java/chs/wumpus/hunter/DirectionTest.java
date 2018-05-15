package chs.wumpus.hunter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import chs.wumpus.hunter.Direction;

public class DirectionTest {

	@Test
	public void turnRightTest() {
		assertEquals(Direction.EAST, Direction.NORTH.getRight());
		assertEquals(Direction.SOUTH, Direction.EAST.getRight());
		assertEquals(Direction.WEST, Direction.SOUTH.getRight());
		assertEquals(Direction.NORTH, Direction.WEST.getRight());
	}

	@Test
	public void turnLeftTest() {
		assertEquals(Direction.WEST, Direction.NORTH.getLeft());
		assertEquals(Direction.NORTH, Direction.EAST.getLeft());
		assertEquals(Direction.EAST, Direction.SOUTH.getLeft());
		assertEquals(Direction.SOUTH, Direction.WEST.getLeft());
	}
}
