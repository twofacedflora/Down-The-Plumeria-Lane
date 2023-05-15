package downtheplumerialane;

import javax.swing.*;

public class InGameInterface extends JPanel {

	private InGameInterfaceController controller;
	private MapContainer activeMap;

	private JLabel player;

	public InGameInterface(Window w) {
		Window.loadFonts(this);

		Tile blackboard1N = new Tile(
			"Blackboard1N",
			Tile.Type.OBSTRUCTED,
			1,
			"blackboard_1_n.png"
		);
		Tile blackboard1NE = new Tile(
			"Blackboard1NE",
			Tile.Type.OBSTRUCTED,
			2,
			"blackboard_1_ne.png"
		);
		Tile blackboard1NW = new Tile(
			"Blackboard1NW",
			Tile.Type.OBSTRUCTED,
			3,
			"blackboard_1_nw.png"
		);
		Tile blackboard1S = new Tile(
			"Blackboard1S",
			Tile.Type.OBSTRUCTED,
			4,
			"blackboard_1_s.png"
		);
		Tile blackboard1SE = new Tile(
			"Blackboard1SE",
			Tile.Type.OBSTRUCTED,
			5,
			"blackboard_1_se.png"
		);
		Tile blackboard1SW = new Tile(
			"Blackboard1SW",
			Tile.Type.OBSTRUCTED,
			6,
			"blackboard_1_sw.png"
		);
		Tile grass1 = new Tile("Grass1", Tile.Type.PASSABLE, 7, "grass_1.png");
		Tile road1 = new Tile("Road1", Tile.Type.PASSABLE, 8, "road_1.png");
		Tile wall1 = new Tile("Wall1", Tile.Type.OBSTRUCTED, 9, "wall_1.png");
		Tile window1 = new Tile(
			"Window1",
			Tile.Type.OBSTRUCTED,
			10,
			"window_1.png"
		);
		Tile window2 = new Tile(
			"Window2",
			Tile.Type.OBSTRUCTED,
			11,
			"window_2.png"
		);
		Tile tile1 = new Tile("Tile1", Tile.Type.PASSABLE, 12, "tile_1.png");

		Map testMap2 = new Map("testmap_2.txt");
		testMap2.addExit(8, 7);

		setActiveMap(testMap2.getContainer());
		activeMap.goToExit(0);

		player =
			new JLabel(
				new ImageIcon(
					this.getClass()
						.getClassLoader()
						.getResource(Player.getCurrentAnimationFrame())
				)
			);
		player.setAlignmentX(0.5f);
		player.setAlignmentY(0.45f);

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
