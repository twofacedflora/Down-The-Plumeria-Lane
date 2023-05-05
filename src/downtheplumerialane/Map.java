package downtheplumerialane;

import java.awt.image.*;
import java.util.*;

public class Map {

	private int width, height;

	private BufferedImage map;

	private MapContainer container;

	private static ArrayList<Map> mapList = new ArrayList<>();

	public Map(String f) {
		width = MapLoader.getMapWidth(f);
		height = MapLoader.getMapHeight(f);
		map = MapLoader.loadMap(f);
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
		return map;
	}

	public MapContainer getContainer() {
		return container;
	}

	public static ArrayList<Map> getMaps() {
		return mapList;
	}
}
