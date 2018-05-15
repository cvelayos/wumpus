package chs.wumpus.board;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BoardTest {

	@Test
	public void createBoardTest() {
		Board board = new Board(2,1);
		BoardElement start = board.getStart();
		assertNotNull(start);
		// at least 1 start
		assertTrue(start.isStart());

		// since it's random, we don't know where it is, but there should be at least 1 gold, 1 wumpus, 1 start and 1 hole
		BoardElement e00 = board.getBoardElementAtPosition(0, 0);
		assertTrue(e00.isHole() || e00.isStart() || e00.hasGold() || e00.isWumpus());
		BoardElement e01 = board.getBoardElementAtPosition(0, 1);
		assertTrue(e01.isHole() || e01.isStart() || e01.hasGold() || e01.isWumpus());
		BoardElement e10 = board.getBoardElementAtPosition(1, 0);
		assertTrue(e10.isHole() || e10.isStart() || e10.hasGold() || e10.isWumpus());
		BoardElement e11 = board.getBoardElementAtPosition(1, 1);
		assertTrue(e11.isHole() || e11.isStart() || e11.hasGold() || e11.isWumpus());	
	}

}
