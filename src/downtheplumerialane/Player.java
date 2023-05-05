package downtheplumerialane;

import javax.swing.*;

public class Player {

	private static final String defaultName = "FRAN";

	private static String currentAnimationFrame = "fran_front_1.png";

	// The Player's Name
	private static String playerName = "______";

	// Get name without extra spaces
	public static String getName() {
		String name = playerName.replace("_", "");
		return name;
	}

	// Get name with extra spaces
	public static String getDisplayName() {
		return (
			playerName.charAt(0) +
			"     " +
			playerName.charAt(1) +
			"     " +
			playerName.charAt(2) +
			"     " +
			playerName.charAt(3) +
			"     " +
			playerName.charAt(4) +
			"     " +
			playerName.charAt(5)
		);
	}

	// Change a character in the name
	public static void alterName(int x) {
		int p = getPointer();
		if (x == 26 && p > 0) {
			playerName =
				playerName.substring(0, p - 1) + '_' + playerName.substring(p, 6);
		} else if (x < 26 && p < 6) {
			playerName =
				playerName.substring(0, p) +
				Character.toString((char) 65 + x) +
				playerName.substring(p + 1, 6);
		}
	}

	// Auto-Set
	public static void setName() {
		playerName = "FRAN__";
	}

	// Get the first space, if there exists any
	public static int getPointer() {
		for (int i = 0; i < 6; i++) {
			if (playerName.charAt(i) == '_') {
				return i;
			}
		}
		return 6;
	}

	public static String getCurrentAnimationFrame() {
		return "resources/images/sprites/fran/" + currentAnimationFrame;
	}
}
