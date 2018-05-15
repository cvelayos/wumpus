package chs.wumpus.board;

public class Hole extends EmptyElement implements BoardElement {

	@Override
	public boolean isHole() {
		return true;
	}

	@Override
	public String printSensations() {
		return "You've hit a hole. GAME OVER";
	}

}
