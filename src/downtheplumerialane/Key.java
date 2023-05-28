package downtheplumerialane;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

public class Key extends JPanel {

	private static ArrayList<Key> keyList = new ArrayList<>();

	private JLabel content, selector;

	public Key(char c) {
		content = new JLabel(Character.toString(c));
		content.setFont(new Font("Gloria Hallelujah", Font.BOLD, 16));
		content.setForeground(Color.WHITE);

		selector =
			new JLabel(
				Window.createScaledImageIcon("interface/key_selector.png", 48)
			);

		createLayout();

		keyList.add(this);
	}

	public Key(ImageIcon i) {
		content = new JLabel(i);

		selector =
			new JLabel(
				Window.createScaledImageIcon("interface/key_selector.png", 48)
			);

		createLayout();

		keyList.add(this);
	}

	public Key(String s) {
		content = new JLabel(s);
		content.setFont(new Font("Gloria Hallelujah", Font.BOLD, 16));
		content.setForeground(Color.WHITE);

		selector =
			new JLabel(
				Window.createScaledImageIcon("interface/key_selector_2.png", 216)
			);

		createLayout();

		keyList.add(this);
	}

	public String getText() {
		return content.getText();
	}

	public JLabel getContent() {
		return content;
	}

	public static ArrayList<Key> getKeys() {
		return keyList;
	}

	public void setText(String s) {
		content.setText(s);
	}

	public void setIcon(ImageIcon i) {
		content.setIcon(i);
	}

	public void showSelector() {
		selector.setVisible(true);
	}

	public void hideSelector() {
		selector.setVisible(false);
	}

	private void createLayout() {
		content.setAlignmentX(CENTER_ALIGNMENT);
		content.setAlignmentY(CENTER_ALIGNMENT);

		selector.setAlignmentX(CENTER_ALIGNMENT);
		selector.setAlignmentY(CENTER_ALIGNMENT);
		selector.setVisible(false);

		setLayout(new OverlayLayout(this));
		setOpaque(false);

		add(content);
		add(selector);
	}
}
