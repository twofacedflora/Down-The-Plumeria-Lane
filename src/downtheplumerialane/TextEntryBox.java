package downtheplumerialane;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class TextEntryBox extends JPanel {

	private int cursorPosition = 0;
	private String text = "";

	private JLabel box;
	private JPanel slotPanel;

	private ArrayList<JLabel> slotList = new ArrayList<>();

	public TextEntryBox(int l) {
		setMaximumSize(new Dimension(48 * l, 48));
		setLayout(new OverlayLayout(this));
		setAlignmentX(CENTER_ALIGNMENT);
		setAlignmentY(CENTER_ALIGNMENT);
		setOpaque(false);

		// FIXME
		box =
			new JLabel(
				Window.createScaledImageIcon("interface/name_box.png", 48 * l)
			);
		box.setAlignmentX(CENTER_ALIGNMENT);
		box.setAlignmentY(CENTER_ALIGNMENT);

		slotPanel = new JPanel();
		slotPanel.setLayout(new GridLayout(1, l));
		slotPanel.setOpaque(false);

		for (int i = 0; i < l; i++) {
			JLabel slot = new JLabel("_");
			slot.setFont(new Font("Gloria Hallelujah", Font.BOLD, 16));
			slot.setForeground(Color.WHITE);
			slot.setHorizontalAlignment(SwingConstants.CENTER);
			slot.setVerticalAlignment(SwingConstants.CENTER);
			text += slot.getText();
			slotList.add(slot);
			slotPanel.add(slot);
		}

		add(slotPanel);
		add(box);
	}

	public String getText() {
		return text;
	}

	public void enterCharacter(Character c) {
		slotList.get(cursorPosition).setText(Character.toString(c));
		cursorPosition++;
	}

	public void deleteCharacter() {
		cursorPosition--;
		slotList.get(cursorPosition).setText("_");
	}
}
