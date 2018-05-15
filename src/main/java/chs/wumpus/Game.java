package chs.wumpus;

import chs.wumpus.board.Board;
import chs.wumpus.hunter.Hunter;

public class Game {

	public static void main(String[] args) {
		if (args.length < 3) {
			System.out
					.println("Wrong parameters, please introduce size of board; number of holes and number of arrows");
			return;
		}
		int size = Integer.valueOf(args[0]);
		int holes = Integer.valueOf(args[1]);
		int arrows = Integer.valueOf(args[2]);

		Board board = new Board(size, holes);
		Hunter hunter = new Hunter(arrows);

		WumpusGameMaster game = new WumpusGameMaster(board, hunter);
		System.out.println("Welcome to Wumpus! Good luck finding the gold.");
		game.play();
	}

}
