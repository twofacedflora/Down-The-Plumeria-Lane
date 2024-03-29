package downtheplumerialane;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.imageio.ImageIO;

class MapLoader {

	public static final int PLAYER_X = (int) (Tile.TILE_WIDTH * 9.5f);
	public static final int PLAYER_Y = Tile.TILE_WIDTH * 6;
	public static final int N_PAD = Tile.TILE_WIDTH * 6;
	public static final int S_PAD = Tile.TILE_WIDTH * 8;
	public static final int W_PAD = (int) (Tile.TILE_WIDTH * 9.5f);
	public static final int E_PAD = (int) (Tile.TILE_WIDTH * 9.5f);

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

	public static int[][] getTileIDs(String f, int w, int h) {
		int[][] tileIDs = new int[h][w];
		ArrayList<Integer> entryList = new ArrayList<>();

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
						entryList.add(i);
					}
				}
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				tileIDs[i][j] = entryList.get(w * i + j);
			}
		}

		return tileIDs;
	}

	public static BufferedImage loadMap(String f, int[][] t, int w, int h) {
		BufferedImage map = new BufferedImage(
			w * Tile.TILE_WIDTH + (W_PAD + E_PAD),
			h * Tile.TILE_WIDTH + (N_PAD + S_PAD),
			BufferedImage.TYPE_INT_ARGB
		);
		Graphics mapGraphics = map.createGraphics();

		mapGraphics.setColor(Color.BLACK);

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				try {
					Image tile = ImageIO.read(
						MapLoader.class.getClassLoader()
							.getResourceAsStream(
								"resources/images/sprites32/env/" +
								Tile.getTiles().get(t[i][j]).getFilename()
							)
					);
					mapGraphics.drawImage(
						tile,
						(j * Tile.TILE_WIDTH) + PLAYER_X,
						(i * Tile.TILE_WIDTH) + PLAYER_Y,
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
