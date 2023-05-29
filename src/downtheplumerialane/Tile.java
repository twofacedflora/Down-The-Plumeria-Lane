package downtheplumerialane;

import java.io.*;
import java.util.*;

public class Tile {

	public enum Type {
		PASSABLE,
		OBSTRUCTED,
		ENTITY,
		EXIT,
		SAVE_POINT,
	}

	public static final int TILE_WIDTH = 32;

	private static String tileAtlasPath = "resources/TileAtlas.csv";

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

	public static void loadTiles() {
		try {
			BufferedReader reader = new BufferedReader(
				new InputStreamReader(
					new BufferedInputStream(
						Tile.class.getClassLoader().getResourceAsStream(tileAtlasPath)
					)
				)
			);

			int index = 1;
			String line;

			while ((line = reader.readLine()) != null) {
				String[] constraints = line.split(",");
				new Tile(
					constraints[0],
					Type.valueOf(constraints[1]),
					index,
					constraints[2]
				);
				index++;
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
