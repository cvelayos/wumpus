package chs.wumpus.actions;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import chs.wumpus.board.Board;
import chs.wumpus.hunter.Direction;
import chs.wumpus.hunter.Hunter;
import chs.wumpus.hunter.Position;

@RunWith(MockitoJUnitRunner.class)
public class ActionProcessorTest {
	
	@Mock
	private Board board;
	@Mock
	private Hunter hunter;
	@Mock
	private Position hunterPosition;
	
	@Test
	public void turnLeftTest() {
		assertEquals("You've turned left", ActionProcessor.processHunterAction(HunterActions.TURN_LEFT, board, hunter));
		verify(hunter).turnLeft();
	}
	
	@Test
	public void turnRightTest() {
		assertEquals("You've turned right", ActionProcessor.processHunterAction(HunterActions.TURN_RIGHT, board, hunter));
		verify(hunter).turnRight();
	}
		
	@Test
	public void walkingHitsTheWallTest() {
		when(hunter.walk()).thenReturn(hunterPosition);
		when(hunterPosition.getColumn()).thenReturn(0);
		when(hunterPosition.getRow()).thenReturn(0);
		when(board.isWithinBoard(0,0)).thenReturn(false);
		assertEquals("You've hit the wall \n", ActionProcessor.processHunterAction(HunterActions.WALK, board, hunter));
		verify(hunter, never()).setPosition(hunterPosition);
	}
	
	@Test
	public void walkingTest() {
		when(hunter.walk()).thenReturn(hunterPosition);
		when(hunterPosition.getColumn()).thenReturn(0);
		when(hunterPosition.getRow()).thenReturn(0);
		when(board.isWithinBoard(0,0)).thenReturn(true);
		assertEquals("You've walked one step", ActionProcessor.processHunterAction(HunterActions.WALK, board, hunter));
		verify(hunter).setPosition(hunterPosition);
	}
	
	@Test
	public void throwArrowFailsHittingWumpusTest() {
		when(hunter.hasArrows()).thenReturn(true);
		when(hunter.getPosition()).thenReturn(hunterPosition);
		when(hunterPosition.getColumn()).thenReturn(0);
		when(hunterPosition.getRow()).thenReturn(0);
		when(hunterPosition.getDirection()).thenReturn(Direction.EAST);
		when(board.huntersArrowFindsWumpus(0, 0, Direction.EAST)).thenReturn(false);
		assertEquals("Arrow hit the wall", ActionProcessor.processHunterAction(HunterActions.THROW_ARROW, board, hunter));
		verify(hunter).throwArrow();
	}
	
	@Test
	public void throwArrowEmptyArrowsTest() {
		when(hunter.hasArrows()).thenReturn(false);
		when(hunter.getPosition()).thenReturn(hunterPosition);
		when(hunterPosition.getColumn()).thenReturn(0);
		when(hunterPosition.getRow()).thenReturn(0);
		when(hunterPosition.getDirection()).thenReturn(Direction.EAST);
		when(board.huntersArrowFindsWumpus(0, 0, Direction.EAST)).thenReturn(false);
		assertEquals("You've used already all your arrows!", ActionProcessor.processHunterAction(HunterActions.THROW_ARROW, board, hunter));
		verify(hunter, never()).throwArrow();
	}
	
	@Test
	public void throwArrowKillWumpusTest() {
		when(hunter.hasArrows()).thenReturn(true);
		when(hunter.getPosition()).thenReturn(hunterPosition);
		when(hunterPosition.getColumn()).thenReturn(0);
		when(hunterPosition.getRow()).thenReturn(0);
		when(hunterPosition.getDirection()).thenReturn(Direction.EAST);
		when(board.huntersArrowFindsWumpus(0, 0, Direction.EAST)).thenReturn(true);
		assertEquals("SCREAM, you've killed the Wumpus! \n", ActionProcessor.processHunterAction(HunterActions.THROW_ARROW, board, hunter));
		verify(hunter).throwArrow();
	}
	
	@Test
	public void tryToExistInWrongBoardElementTest() {
		assertEquals("Wrong action selected, please choose another", ActionProcessor.processHunterAction(HunterActions.EXIT, board, hunter));
	}
	
	@Test
	public void getCorrectActionTest() {
		assertEquals(HunterActions.TURN_LEFT, ActionProcessor.getHunterAction("0"));
	}

	@Test
	public void nonExistingActionTest() {
		assertEquals(null, ActionProcessor.getHunterAction("10"));
	}

	@Test
	public void castExceptionIsCatchedTest() {
		assertEquals(null, ActionProcessor.getHunterAction("hola"));
	}
}
