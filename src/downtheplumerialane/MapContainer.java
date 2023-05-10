package downtheplumerialane;

import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;

public class MapContainer extends JPanel {

	private int px, py;
	private int dx1, dy1, dx2, dy2;
	private int srcx1, srcy1, srcx2, srcy2;

	private int[][] tileIDs;

	private ArrayList<int[]> exits;

	private BufferedImage mapImage;

	public MapContainer(Map m) {
		tileIDs = m.getTileIDs();
		exits = m.getExits();
		mapImage = m.getMap();
		px = 0;
		py = 0;
		dx1 = 0;
		dy1 = 0;
		dx2 = Window.WIDTH;
		dy2 = Window.HEIGHT;
		srcx1 = 0;
		srcy1 = 0;
		srcx2 = Window.WIDTH;
		srcy2 = Window.HEIGHT;
	}

	public void moveSourceUp() {
		if (srcy1 > 0) {
			if (
				Tile.getTiles().get(tileIDs[py - 1][px]).getType() == Tile.Type.PASSABLE
			) {
				py--;
				srcy1 -= Tile.TILE_WIDTH;
				srcy2 -= Tile.TILE_WIDTH;
			}
		}
	}

	public void moveSourceDown() {
		if (srcy2 < mapImage.getHeight()) {
			if (
				Tile.getTiles().get(tileIDs[py + 1][px]).getType() == Tile.Type.PASSABLE
			) {
				py++;
				srcy1 += Tile.TILE_WIDTH;
				srcy2 += Tile.TILE_WIDTH;
			}
		}
	}

	public void moveSourceLeft() {
		if (srcx1 > 0) {
			if (
				Tile.getTiles().get(tileIDs[py][px - 1]).getType() == Tile.Type.PASSABLE
			) {
				px--;
				srcx1 -= Tile.TILE_WIDTH;
				srcx2 -= Tile.TILE_WIDTH;
			}
		}
	}

	public void moveSourceRight() {
		if (srcx2 < mapImage.getWidth()) {
			if (
				Tile.getTiles().get(tileIDs[py][px + 1]).getType() == Tile.Type.PASSABLE
			) {
				px++;
				srcx1 += Tile.TILE_WIDTH;
				srcx2 += Tile.TILE_WIDTH;
			}
		}
	}

	public void goToExit(int i) {
		int[] exit = exits.get(i);
		px = exits.get(0)[0];
		py = exits.get(0)[1];
		srcx1 = Tile.TILE_WIDTH * exit[0];
		srcy1 = Tile.TILE_WIDTH * exit[1];
		srcx2 = Tile.TILE_WIDTH * exit[0] + Window.WIDTH;
		srcy2 = Tile.TILE_WIDTH * exit[1] + Window.HEIGHT;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
		g.drawImage(mapImage, dx1, dy1, dx2, dy2, srcx1, srcy1, srcx2, srcy2, this);
	}
}
