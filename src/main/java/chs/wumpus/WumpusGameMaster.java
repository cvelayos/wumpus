package chs.wumpus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

import chs.wumpus.actions.ActionProcessor;
import chs.wumpus.actions.HunterActions;
import chs.wumpus.board.Board;
import chs.wumpus.board.BoardElement;
import chs.wumpus.hunter.Direction;
import chs.wumpus.hunter.Hunter;
import chs.wumpus.hunter.Position;

public class WumpusGameMaster {

	private Board board;
	private Hunter hunter;

	public WumpusGameMaster(Board board, Hunter hunter) {
		this.board = board;
		this.hunter = hunter;
	}

	public void play() {
		BoardElement start = board.getStart();
		setHunterInInitialPosition(hunter, start);
		System.out.println(start.printSensations());

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BoardElement boardElementWhereTheHunterIs = start;
		boolean exit = false;
		while (!exit) {
			System.out.print(HunterActions.getPossibleActions());

			try {
				String actionSelected = reader.readLine();
				HunterActions action = ActionProcessor.getHunterAction(actionSelected);
				if (HunterActions.EXIT.equals(action) && boardElementWhereTheHunterIs.isStart()) {
					exit = true;
					break;
				}
				String reaction = ActionProcessor.processHunterAction(action, board, hunter);
				System.out.println(reaction);

				boardElementWhereTheHunterIs = board.getBoardElementAtPosition(hunter.getPosition().getRow(),
						hunter.getPosition().getColumn());
				System.out.println(boardElementWhereTheHunterIs.printSensations());
				if (boardElementWhereTheHunterIs.isHole() || boardElementWhereTheHunterIs.isWumpus()) {
					exit = true;
				}
			} catch (IOException e) {
				System.out.println("uuups there was an error, please start the game again.");
				exit = true;
			}
		}
	}

	private void setHunterInInitialPosition(Hunter hunter, BoardElement start) {
		Direction[] possibleDirections = Direction.values();
		Collections.shuffle(Arrays.asList(possibleDirections));
		Position initialPosition = new Position(start.getRow(), start.getColumn(), possibleDirections[0]);
		hunter.setPosition(initialPosition);
	}
}
