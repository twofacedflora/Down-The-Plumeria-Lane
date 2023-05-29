package downtheplumerialane;

import javax.swing.*;

public class PlayerComponent extends JLabel {

	// HACK: dump in CSV? read filenames from directory?
	// HACK: don't use array element order to determine animation cycle
	// TODO: add sprite classifications (idle, walking, special? idk)
	private static final String[][] frames = {
		{ "FranBack1.png", "FranBack2.png", "FranBack1.png", "FranBack3.png" },
		{ "FranFront1.png", "FranFront2.png", "FranFront1.png", "FranFront3.png" },
		{ "FranLeft1.png", "FranLeft2.png", "FranLeft1.png", "FranLeft3.png" },
		{ "FranRight1.png", "FranRight2.png", "FranRight1.png", "FranRight3.png" },
	};

	private int currentCyclePos = 0;
	private String currentFrame = "FranFront1.png";

	private Player player;

	public PlayerComponent(Player p) {
		player = p;
		// HACK: make player property of player class
		// TODO: make player sprite graphic instead of component
		setIcon(Window.createScaledImageIcon(getCurrentFrame(), -1));
		setAlignmentX(0.5f);
		setAlignmentY(5.5f / 15);
	}

	public void setCurrentFrame(String s) {
		currentFrame = s;
	}

	public String getCurrentFrame() {
		return "sprites32/fran/" + currentFrame;
	}

	public void updateCurrentFrame(String d) {
		// HACK: don't hardcode cycle length
		if (currentCyclePos == 3) {
			currentCyclePos = 0;
		} else {
			currentCyclePos++;
		}

		switch (d) {
			case "UP":
				setCurrentFrame(frames[0][currentCyclePos]);
				break;
			case "DOWN":
				setCurrentFrame(frames[1][currentCyclePos]);
				break;
			case "LEFT":
				setCurrentFrame(frames[2][currentCyclePos]);
				break;
			case "RIGHT":
				setCurrentFrame(frames[3][currentCyclePos]);
				break;
			default:
				break;
		}

		setIcon(Window.createScaledImageIcon(getCurrentFrame(), -1));
	}
}
