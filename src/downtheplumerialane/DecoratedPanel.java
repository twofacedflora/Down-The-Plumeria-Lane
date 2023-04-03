package downtheplumerialane;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

public class DecoratedPanel extends JPanel {

	private BufferedImage bg;

	public DecoratedPanel(String f) {
		try {
			bg =
				ImageIO.read(
					getClass().getClassLoader().getResource("resources/images/" + f)
				);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, this);
	}
}
