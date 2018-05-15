package chs.wumpus.board;

import java.util.Arrays;
import java.util.List;

import chs.wumpus.hunter.Sensations;

public class Wall extends EmptyElement implements BoardElement {

	@Override
	public boolean isWall() {
		return true;
	}

	@Override
	public List<Sensations> getSensations() {
		return Arrays.asList(Sensations.CRASH);
	}
}
