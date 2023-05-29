package downtheplumerialane;

import java.awt.*;
import javax.swing.*;

public class CharacterNamingInterface extends PanelWithBackground {

	private CharacterNamingInterfaceController controller;

	private Keyboard keyboard;
	private TextEntryBox nameBox;

	private JLabel topText;

	public CharacterNamingInterface(Window w) {
		super("background/void.png");
		Window.loadFonts(this);

		setName("characternaming");
		setLayout(new GridBagLayout());

		// HACK: only load interfaces when needed
		Player player = new Player();

		topText = new JLabel("Your name, please.");
		topText.setFont(new Font("Gloria Hallelujah", Font.BOLD, 16));
		topText.setForeground(Color.WHITE);
		topText.setAlignmentX(CENTER_ALIGNMENT);
		topText.setAlignmentY(CENTER_ALIGNMENT);
		topText.setHorizontalAlignment(SwingConstants.CENTER);
		topText.setVerticalAlignment(SwingConstants.CENTER);

		nameBox = new TextEntryBox(6);
		keyboard = new Keyboard();

		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setOpaque(false);

		contentPanel.add(topText);
		contentPanel.add(Box.createVerticalStrut(48));
		contentPanel.add(nameBox);
		contentPanel.add(Box.createVerticalStrut(48));
		contentPanel.add(keyboard);

		add(contentPanel);

		controller = new CharacterNamingInterfaceController(w, this);
	}

	public Keyboard getKeyboard() {
		return keyboard;
	}

	public TextEntryBox getNameBox() {
		return nameBox;
	}
}
