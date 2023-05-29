package downtheplumerialane;

import javax.swing.*;

public class InGameInterface extends JPanel {

	private InGameInterfaceController controller;
	private MapContainer activeMap;

	public InGameInterface(Window w) {
		Window.loadFonts(this);

		// HACK: there has to be a format better than CSV, right?
		Tile.loadTiles();

		Map testMap = new Map("testmap_3.txt");
		testMap.addExit(6, 5);

		setActiveMap(testMap.getContainer());
		activeMap.goToExit(0);

		setName("ingame");
		setLayout(new OverlayLayout(this));

		add(Player.getActivePlayer().getComponent());
		add(activeMap);

		controller =
			new InGameInterfaceController(w, this, Player.getActivePlayer());
	}

	public MapContainer getActiveMap() {
		return activeMap;
	}

	public void setActiveMap(MapContainer mc) {
		activeMap = mc;
	}
}
