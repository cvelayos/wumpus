package chs.wumpus.actions;

public enum HunterActions {
	TURN_LEFT("Turn left \n"), 
	TURN_RIGHT("Turn right \n"), 
	THROW_ARROW("Throw arrow \n"), 
	WALK("Walk \n"), 
	EXIT("Exit \n");

	private String definition;

	private HunterActions(String definition) {
		this.definition = definition;
	}

	@Override
	public String toString() {
		return this.ordinal() + "- " + definition;
	}
	
	public static String getPossibleActions() {
		StringBuilder sb = new StringBuilder();
		sb.append("What would you like to do?\n");
		for (HunterActions action : HunterActions.values()) {
			sb.append(action);
		}
		return sb.toString();
	}
}
