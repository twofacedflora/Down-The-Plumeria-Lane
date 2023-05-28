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

	private int selectorPosition = 0;

	private TitleInterfaceController controller;
	private ImageIcon emptyCheckbox, tickedCheckbox;
	private JPanel centerFrameContainer, southFrameContainer, mainPanel, optionPanel;
	private JLabel title, version;
	private ArrayList<JLabel> options = new ArrayList<>();

	public TitleInterface(Window w) {
		super("background/title_normal.png");
		Window.loadFonts(this);

		setName("title");
		setLayout(new BorderLayout());

		emptyCheckbox = Window.createScaledImageIcon("interface/checkbox.png", 32);
		tickedCheckbox =
			Window.createScaledImageIcon("interface/ticked_checkbox.png", 32);

		title = new JLabel(Window.createScaledImageIcon("interface/title.png", -1));
		title.setAlignmentX(CENTER_ALIGNMENT);

		version = new JLabel("v" + Window.VERSION_NUMBER);
		version.setFont(new Font("Gloria Hallelujah", Font.BOLD, 12));

		optionPanel = new JPanel();
		optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
		optionPanel.setAlignmentX(CENTER_ALIGNMENT);
		optionPanel.setOpaque(false);

		for (String o : optionNames) {
			JLabel ol = new JLabel(o, SwingConstants.CENTER);
			ol.setHorizontalTextPosition(JLabel.RIGHT);
			ol.setFont(new Font("Gloria Hallelujah", Font.BOLD, 16));
			ol.setIcon(emptyCheckbox);
			options.add(ol);
			optionPanel.add(ol);
		}

		centerFrameContainer = new JPanel();
		centerFrameContainer.setLayout(new GridBagLayout());
		centerFrameContainer.setOpaque(false);

		southFrameContainer = new JPanel();
		southFrameContainer.setLayout(new FlowLayout(FlowLayout.RIGHT));
		southFrameContainer.setOpaque(false);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(96, 96, 96, 96));
		mainPanel.setOpaque(false);

		mainPanel.add(title);
		mainPanel.add(optionPanel);

		centerFrameContainer.add(mainPanel);
		southFrameContainer.add(version);

		add(centerFrameContainer, BorderLayout.CENTER);
		add(southFrameContainer, BorderLayout.SOUTH);

		switchOption(selectorPosition);

		controller = new TitleInterfaceController(w, this);
	}

	public int getSelectorPosition() {
		return selectorPosition;
	}

	public void switchOption(int i) {
		try {
			options.get(selectorPosition).setIcon(emptyCheckbox);
			selectorPosition = i;
			options.get(selectorPosition).setIcon(tickedCheckbox);
		} catch (NullPointerException | IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
}
