package chs.wumpus.hunter;

public enum Sensations {
	STINK("HEDOR, wumpus is close \n"), 
	BREEZE("BREEZE, there is a hole near you \n"), 
	GLOW("GLOW, you've found the gold! \n"), 
	SCREAM("SCREAM, you've killed the Wumpus! \n"), 
	CRASH("You've hit the wall \n");

	String definition;

	private Sensations(String definition) {
		this.definition = definition;
	}

	@Override
	public String toString() {
		return definition;
	}
}
