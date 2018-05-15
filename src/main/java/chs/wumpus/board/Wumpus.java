package chs.wumpus.board;

public class Wumpus extends EmptyElement implements BoardElement {

	private boolean isAlive = true;

	@Override
	public boolean isWumpus() {
		return isAlive;
	}

	public void killWpumpus() {
		isAlive = false;
	}

	@Override
	public String printSensations() {
		return "Oh no! Wumpus just killed you. GAME OVER";
	}
}
