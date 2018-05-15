package chs.wumpus.board;

public class Start extends EmptyElement implements BoardElement {

	@Override
	public boolean isStart() {
		return true;
	}
}
