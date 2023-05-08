package downtheplumerialane;

import java.util.*;

public class Tile {

	public enum Type {
		PASSABLE,
		OBSTRUCTED,
		ENTITY,
		EXIT,
		SAVE_POINT,
	}

	public static final int TILE_WIDTH = 60;

	private static HashMap<Integer, Tile> tileMap = new HashMap<>();

	private String name, filename;

	private Type type;

	public Tile(String n, Type t, int id, String f) {
		name = n;
		filename = f;
		type = t;
		tileMap.put(id, this);
	}

	public static HashMap<Integer, Tile> getTiles() {
		return tileMap;
	}

	public String getName() {
		return name;
	}

	public String getFilename() {
		return filename;
	}

	public Type getType() {
		return type;
	}
}
