package downtheplumerialane;

import java.util.*;

public class Player {

	private static final String defaultName = "FRAN";

	private static Player activePlayer;

	private static ArrayList<Player> playerList = new ArrayList<>();

	private String name;

	private PlayerComponent playerComponent;

	public Player() {
		playerComponent = new PlayerComponent(this);
		setActivePlayer(this);
		playerList.add(this);
	}

	public static Player getActivePlayer() { // FIXME
		return activePlayer;
	}

	public String getName() {
		return name;
	}

	public PlayerComponent getComponent() {
		return playerComponent;
	}

	public static void setActivePlayer(Player p) {
		activePlayer = p;
	}

	public void setName(String n) {
		name = n;
	}
}
