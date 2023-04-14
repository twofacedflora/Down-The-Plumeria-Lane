package downtheplumerialane;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class TitleInterface extends PanelWithBackground {

	private static final String[] optionNames = {
		"New Game",
		"Settings",
		"Quit",
	};

	private TitleInterfaceController controller;
	private JPanel centerContainer, southContainer, mainPanel, optionPanel;
	private JLabel title, version;
	private ArrayList<JLabel> options;

	public TitleInterface(Window w) {
		super("background/title_normal.png");
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Window.loadFonts(this, Window.CUSTOM_FONTS);

		setLayout(new BorderLayout());

		title = new JLabel("Down The Plumeria Lane");
		title.setFont(new Font("Gloria Hallelujah", Font.BOLD, 54));
		title.setAlignmentX(CENTER_ALIGNMENT);

		version = new JLabel("v" + Window.VERSION_NUMBER);
		version.setFont(new Font("Gloria Hallelujah", Font.BOLD, 16));

		optionPanel = new JPanel();
		optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
		optionPanel.setAlignmentX(CENTER_ALIGNMENT);
		optionPanel.setOpaque(false);
		options = new ArrayList<JLabel>();

		for (String o : optionNames) {
			JLabel ol = new JLabel(o, SwingConstants.CENTER);
			ol.setHorizontalTextPosition(JLabel.RIGHT);
			ol.setFont(new Font("Gloria Hallelujah", Font.BOLD, 21));
			options.add(ol);
			optionPanel.add(ol);
		}

		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setOpaque(false);

		centerContainer = new JPanel();
		centerContainer.setLayout(new GridBagLayout());
		centerContainer.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();

		southContainer = new JPanel();
		southContainer.setLayout(new FlowLayout(FlowLayout.RIGHT));
		southContainer.setOpaque(false);

		mainPanel.add(Box.createVerticalStrut(30));
		mainPanel.add(title);
		mainPanel.add(Box.createVerticalStrut(70));
		mainPanel.add(optionPanel);

		centerContainer.add(mainPanel, gbc);
		southContainer.add(version);

		add(centerContainer, BorderLayout.CENTER);
		add(southContainer, BorderLayout.SOUTH);

		controller = new TitleInterfaceController(w, this, options);
		controller.setKeybinds();
		for (int i = 0; i < options.size(); i++) controller.resetOptionState(i);
		controller.setSelector(0);
	}
}
