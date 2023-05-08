package downtheplumerialane;

import java.awt.image.*;
import java.util.*;

public class Map {

	private static ArrayList<Map> mapList = new ArrayList<>();

	private int width, height;

	private BufferedImage mapImage;

	private MapContainer container;

	private int[][] tileIDs;

	private ArrayList<int[]> exits = new ArrayList<>();

	public Map(String f) {
		width = MapLoader.getMapWidth(f);
		height = MapLoader.getMapHeight(f);
		tileIDs = MapLoader.getTileIDs(f, width, height);
		mapImage = MapLoader.loadMap(f, tileIDs, width, height);
		container = new MapContainer(this);
		mapList.add(this);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public BufferedImage getMap() {
		return mapImage;
	}

	public MapContainer getContainer() {
		return container;
	}

	public ArrayList<int[]> getExits() {
		return exits;
	}

	public static ArrayList<Map> getMaps() {
		return mapList;
	}

	public void addExit(int x, int y) {
		exits.add(new int[] { x, y });
	}
}
