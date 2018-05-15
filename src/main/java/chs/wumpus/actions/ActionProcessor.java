package chs.wumpus.actions;

import chs.wumpus.board.Board;
import chs.wumpus.hunter.Hunter;
import chs.wumpus.hunter.Position;
import chs.wumpus.hunter.Sensations;

public class ActionProcessor {

	private static final String WRONG_ACTION = "Wrong action selected, please choose another";

	public static String processHunterAction(HunterActions selectedAction, Board board, Hunter hunter) {
		switch (selectedAction) {
		case TURN_LEFT:
			hunter.turnLeft();
			return "You've turned left";
		case TURN_RIGHT:
			hunter.turnRight();
			return "You've turned right";
		case WALK:
			Position newPosition = hunter.walk();
			if (!board.isWithinBoard(newPosition.getRow(), newPosition.getColumn())) {
				return Sensations.CRASH.toString();
			}
			hunter.setPosition(newPosition);
			return "You've walked one step";
		case THROW_ARROW:
			if (!hunter.hasArrows()) {
				return "You've used already all your arrows!";
			}
			hunter.throwArrow();
			if (board.huntersArrowFindsWumpus(hunter.getPosition().getRow(), hunter.getPosition().getColumn(), hunter.getPosition().getDirection())) {
				return Sensations.SCREAM.toString();
			}
			return "Arrow hit the wall";
		default:
			return WRONG_ACTION;
		}
	}

	public static HunterActions getHunterAction(String action) {
		try {
			int actionValue = Integer.valueOf(action);
			if (actionValue < 0 || actionValue >= HunterActions.values().length) {
				return null;
			}

			return HunterActions.values()[actionValue];
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
