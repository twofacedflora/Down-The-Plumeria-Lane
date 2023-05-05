package downtheplumerialane;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;

class MapLoader {

	public static int getMapWidth(String f) {
		int width = 0;

		try {
			Scanner scanner = new Scanner(new File("resources/mapdata/" + f));

			if (scanner.hasNextLine()) {
				String firstLine = scanner.nextLine();
				width = firstLine.split(" ").length;
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return width;
	}

	public static int getMapHeight(String f) {
		int height = 0;

		try {
			Scanner scanner = new Scanner(new File("resources/mapdata/" + f));

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (
					!line.isEmpty() &&
					!line.trim().equals(" ") &&
					!line.trim().equals("\n")
				) {
					height++;
				}
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return height;
	}

	public static BufferedImage loadMap(String f) {
		int width = getMapWidth(f);
		int height = getMapHeight(f);

		ArrayList<Integer> tileIDs = new ArrayList<Integer>();

		try {
			Scanner scanner = new Scanner(new File("resources/mapdata/" + f));

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (
					!line.isEmpty() &&
					!line.trim().equals(" ") &&
					!line.trim().equals("\n")
				) {
					int[] entries = Arrays
						.stream(line.split("\\s+"))
						.mapToInt(Integer::parseInt)
						.toArray();
					for (int i : entries) {
						tileIDs.add(i);
					}
				}
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		BufferedImage map = new BufferedImage(
			width * Tile.TILE_WIDTH + 840,
			height * Tile.TILE_WIDTH + 540,
			BufferedImage.TYPE_INT_ARGB
		);
		Graphics mapGraphics = map.createGraphics();

		mapGraphics.setColor(Color.BLACK);

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				try {
					Image tile = ImageIO.read(
						MapLoader.class.getClassLoader()
							.getResourceAsStream(
								"resources/images/sprites/env/" +
								Tile.getTiles().get(tileIDs.get(width * i + j))
							)
					);
					mapGraphics.drawImage(
						tile,
						(j * Tile.TILE_WIDTH) + (Tile.TILE_WIDTH * 7),
						(i * Tile.TILE_WIDTH) + (Tile.TILE_WIDTH * 4),
						null
					);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return map;
	}
}
