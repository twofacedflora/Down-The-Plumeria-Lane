package downtheplumerialane;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Window extends JFrame {

	public static final int WIDTH = 900;
	public static final int HEIGHT = 600;

	public static final String VERSION_NUMBER = "0.1.0-alpha";

	public static final String[] CUSTOM_FONTS = {
		"GloriaHallelujah-Regular.ttf",
	};

	public Window() {
		super("Down The Plumeria Lane");
		setLayout(new CardLayout());

		add(new TitleScreen(this), "Title Screen");
		add(new SettingsScreen(this), "Settings Screen");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	public static void loadFonts(JPanel s, GraphicsEnvironment ge, String[] fs) {
		try {
			for (String f : fs) ge.registerFont(
				Font.createFont(
					Font.TRUETYPE_FONT,
					new BufferedInputStream(
						s
							.getClass()
							.getClassLoader()
							.getResourceAsStream("resources/fonts/" + f)
					)
				)
			);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
	}

	public void switchToScreen(String s) {
		CardLayout layout = (CardLayout) getContentPane().getLayout();
		layout.show(getContentPane(), s);
	}
}
