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

	private static HashMap<Integer, String> tileMap = new HashMap<>();

	private String name;

	private Type type;

	public Tile(String n, Type t, int id, String f) {
		name = n;
		type = t;
		tileMap.put(id, f);
	}

	public static HashMap<Integer, String> getTiles() {
		return tileMap;
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}
}
