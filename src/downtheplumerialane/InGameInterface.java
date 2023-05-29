package downtheplumerialane;

import javax.swing.*;

public class InGameInterface extends JPanel {

	private InGameInterfaceController controller;
	private MapContainer activeMap;

	private JLabel player;

	public InGameInterface(Window w) {
		Window.loadFonts(this);

		// HACK: there has to be a format better than CSV, right?
		Tile.loadTiles();

		Map testMap = new Map("testmap_3.txt");
		testMap.addExit(6, 5);

		setActiveMap(testMap.getContainer());
		activeMap.goToExit(0);

		// TODO: make player sprite graphic instead of component
		player =
			new JLabel(
				new ImageIcon(
					this.getClass()
						.getClassLoader()
						.getResource(Player.getCurrentAnimationFrame())
				)
			);
		player.setAlignmentX(0.5f);
		player.setAlignmentY(5.5f / 15);

		setName("ingame");
		setLayout(new OverlayLayout(this));

		add(player);
		add(activeMap);

		controller = new InGameInterfaceController(w, this);
	}

	public MapContainer getActiveMap() {
		return activeMap;
	}

	public void setActiveMap(MapContainer mc) {
		activeMap = mc;
	}
}
