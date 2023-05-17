package downtheplumerialane;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class PausedInterface extends PanelWithBackground {

	private static final String[] optionNames = {
		"Resume",
		"How To Play",
		"Settings",
		"Quit To Menu",
	};

	private int selectorPosition = 0;

	private PausedInterfaceController controller;
	private ImageIcon emptyCheckbox, tickedCheckbox;
	private JPanel optionPanel;
	private ArrayList<JLabel> options = new ArrayList<>();

	public PausedInterface(Window w) {
		super("background/void.png");
		Window.loadFonts(this);

		setName("paused");
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		emptyCheckbox =
			Window.createScaledImageIcon("interface/checkbox_alt.png", 40);
		tickedCheckbox =
			Window.createScaledImageIcon("interface/ticked_checkbox_alt.png", 40);

		optionPanel = new JPanel();
		optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
		optionPanel.setAlignmentX(CENTER_ALIGNMENT);
		optionPanel.setOpaque(false);

		for (String o : optionNames) {
			JLabel ol = new JLabel(o, SwingConstants.CENTER);
			ol.setHorizontalTextPosition(JLabel.RIGHT);
			ol.setFont(new Font("Gloria Hallelujah", Font.BOLD, 21));
			ol.setIcon(emptyCheckbox);
			options.add(ol);
			optionPanel.add(ol);
		}

		add(optionPanel, gbc);

		switchOption(selectorPosition);

		controller = new PausedInterfaceController(w, this);
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
