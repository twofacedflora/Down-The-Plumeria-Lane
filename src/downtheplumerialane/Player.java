package downtheplumerialane;

public class Player {

	private static final String defaultName = "FRAN";

	private static String currentAnimationFrame = "FranFront1.png";
	private static Player activePlayer; // FIXME

	private String name;

	public Player() {}

	public static Player getActivePlayer() { // FIXME
		return activePlayer;
	}

	public static void setActivePlayer(Player p) {
		activePlayer = p;
	}

	public String getName() {
		return name;
	}

	public void setName(String n) {
		name = n;
	}

	public static String getCurrentAnimationFrame() {
		return "resources/images/sprites32/fran/" + currentAnimationFrame;
	}
}
