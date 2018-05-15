package chs.wumpus.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import chs.wumpus.hunter.Direction;
import chs.wumpus.hunter.Sensations;

public class Board {

	private int holes;
	private int size;
	private BoardElement board[][];
	private BoardElement start;

	public Board(int size, int holes) {
		this.holes = holes;
		this.size = size;
		this.board = new BoardElement[size][size];
		this.fillBoard(size);
	}

	public boolean isWithinBoard(int row, int column) {
		return column < this.size && column >= 0 && row < this.size
				&& row >= 0;
	}

	private void fillBoard(int size) {
		List<BoardElement> boardElements = getBoardElements(size, holes);

		int elementIndex = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				BoardElement thisElement = boardElements.get(elementIndex);
				setSensations(i, j, thisElement);
				setPosition(i, j, thisElement);
				if (thisElement.isStart()) {
					this.start = thisElement;
				}
				board[i][j] = thisElement;
				elementIndex++;
			}
		}
	}

	private void setPosition(int i, int j, BoardElement thisElement) {
		thisElement.setRow(i);
		thisElement.setColumn(j);
	}

	private void setSensations(int i, int j, BoardElement thisElement) {
		if (j > 0) {
			BoardElement previousElement = board[i][j - 1];
			setBrisa(thisElement, previousElement);
			setHedor(thisElement, previousElement);
		}
		if (i > 0) {
			BoardElement upperElement = board[i - 1][j];
			setBrisa(thisElement, upperElement);
			setHedor(thisElement, upperElement);
		}
		if (thisElement.hasGold()) {
			thisElement.setSensation(Sensations.GLOW);
		}
	}

	private void setBrisa(BoardElement thisElement, BoardElement upperElement) {
		if (thisElement.isHole()) {
			upperElement.setSensation(Sensations.BREEZE);
		}
		if (upperElement.isHole()) {
			thisElement.setSensation(Sensations.BREEZE);
		}
	}

	private void setHedor(BoardElement thisElement, BoardElement previousElement) {
		if (previousElement.isWumpus()) {
			thisElement.setSensation(Sensations.STINK);
		}
		if (thisElement.isWumpus()) {
			previousElement.setSensation(Sensations.STINK);
		}
	}

	/**
	 * Get all elements that will compose the board. They are shuffled so the
	 * board is random in each game.
	 * 
	 * @param size:
	 *            board is nxn size
	 * @param holes:
	 *            Number of holes in the board.
	 * @return
	 */
	private List<BoardElement> getBoardElements(int size, int holes) {
		int totalSize = size * size;
		List<BoardElement> totalElements = new ArrayList<>(totalSize);
		for (int i = 0; i < holes; i++) {
			totalElements.add(new Hole());
		}

		totalElements.add(new Start());
		totalElements.add(new Wumpus());
		totalElements.add(new Gold());
		// The rest of elements (total size - # holes - 1 start - 1 wumpus - 1
		// gold) are empty
		for (int i = 0; i < (totalSize - holes - 3); i++) {
			totalElements.add(new EmptyElement());
		}
		Collections.shuffle(totalElements);

		return totalElements;
	}

	public BoardElement getStart() {
		return this.start;
	}

	public BoardElement getBoardElementAtPosition(int row, int column) {
		return board[row][column];
	}

	public boolean huntersArrowFindsWumpus(int row, int column, Direction arrowDirection) {
		boolean wumpusFound = false;
		if (Direction.NORTH.equals(arrowDirection)) {
			List<BoardElement> allElements = Arrays.asList(this.board[column]);
			wumpusFound = allElements.stream().filter(e -> e.getRow() < row)
					.anyMatch(e2 -> e2.isWumpus());
		} else if (Direction.SOUTH.equals(arrowDirection)) {
			List<BoardElement> allElements = Arrays.asList(this.board[column]);
			wumpusFound = allElements.stream().filter(e -> e.getRow() > row)
					.anyMatch(e2 -> e2.isWumpus());
		} else if (Direction.EAST.equals(arrowDirection)) {
			wumpusFound = Arrays.stream(board).flatMap(x -> Arrays.stream(x))
					.filter(e -> e.getRow() == row)
					.filter(e2 -> e2.getColumn() > column).anyMatch(e3 -> e3.isWumpus());
		} else if (Direction.WEST.equals(arrowDirection)) {
			wumpusFound = Arrays.stream(board).flatMap(x -> Arrays.stream(x))
					.filter(e -> e.getRow() == row)
					.filter(e2 -> e2.getColumn() < column).anyMatch(e3 -> e3.isWumpus());
		}
		if (wumpusFound) {
			Optional<BoardElement> wumpus = Arrays.stream(board).flatMap(x -> Arrays.stream(x))
					.filter(e3 -> e3.isWumpus()).findFirst();
			wumpus.ifPresent(w -> ((Wumpus) w).killWpumpus());
		}
		return wumpusFound;
	}
}
