package downtheplumerialane;

import java.awt.*;
import java.io.*;
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

		add(new TitleInterface(this), "title");
		add(new SettingsInterface(this), "settings");
		add(new CharacterNamingInterface(this), "namechar");
		add(new InGameInterface(this), "ingame");

		getContentPane().setPreferredSize(new Dimension(900, 600));
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	public static void loadFonts(JPanel s) {
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			for (String f : CUSTOM_FONTS) ge.registerFont(
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

	public static ImageIcon createScaledImageIcon(String f, int r) {
		return new ImageIcon(
			new ImageIcon(
				Window.class.getClassLoader().getResource("resources/images/" + f)
			)
				.getImage()
				.getScaledInstance(r, -1, Image.SCALE_SMOOTH)
		);
	}

	public void switchInterface(String s) {
		CardLayout layout = (CardLayout) getContentPane().getLayout();
		layout.show(getContentPane(), s);
	}
}
