// TODO: clean up EVERYTHING
// chippy...

package downtheplumerialane;

import java.awt.*;
import java.awt.Font;
import java.lang.Character;
import javax.swing.*;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class CharacterNamingInterface extends PanelWithBackground {

	// Initialize
	int buttonHover = 0;

	private CharacterNamingInterfaceController controller;

	protected JLabel verBox = new JLabel();
	protected JLabel horBox = new JLabel();
	protected JLabel hlBox = new JLabel();
	protected JPanel nameDisplay = new JPanel();
	protected JLayeredPane keySet = new JLayeredPane();

	protected JLabel topText = new JLabel("Your name, please.");
	protected JLabel nameText = new JLabel("_     _     _     _     _     _");
	protected JLabel unsure = new JLabel("I don't know.");
	protected JLabel confirm = new JLabel("Confirm!");
	protected JLabel upButton = new JLabel("Up");

	public CharacterNamingInterface(Window w) {
		super("background/void.png");
		Window.loadFonts(this);

		createAndShowGUI();

		controller = new CharacterNamingInterfaceController(w, this);
	}

	private void createAndShowGUI() {
		UIManager.put("Label.foreground", Color.white);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(Color.black);

		// Greeting Text Set-Up
		topText.setFont(new Font("Gloria Hallelujah", Font.BOLD, 22));
		topText.setPreferredSize(new Dimension(450, 75));
		topText.setForeground(Color.white);

		topText.setHorizontalAlignment(JLabel.CENTER);
		topText.setVerticalAlignment(JLabel.CENTER);

		// Name Display
		Border nDBorder = BorderFactory.createLineBorder(Color.white);
		nameDisplay.setBorder(nDBorder);
		nameDisplay.setBackground(Color.black);
		nameDisplay.setPreferredSize(new Dimension(500, 100));
		nameDisplay.setLayout(new FlowLayout(FlowLayout.CENTER));

		nameText.setFont(new Font("Gloria Hallelujah", Font.BOLD, 25));
		nameText.setPreferredSize(new Dimension(440, 75));
		nameText.setForeground(Color.white);
		nameText.setHorizontalAlignment(JLabel.CENTER);
		nameText.setVerticalAlignment(JLabel.CENTER);

		nameDisplay.add(nameText);

		// Keyboard
		JPanel keyboard = new JPanel();
		keyboard.setBorder(nDBorder);
		keyboard.setPreferredSize(new Dimension(540, 240));
		keyboard.setLayout(new BoxLayout(keyboard, BoxLayout.Y_AXIS));

		// Letter Keys
		keySet.setBackground(Color.black);
		keySet.setPreferredSize(new Dimension(540, 180));

		JPanel keys = new JPanel();
		keys.setBackground(Color.black);
		for (int i = 0; i <= 26; i++) { // FIXME
			if (i == 26) {
				// Replace with Left-Arrow Icon
				JLabel key = new JLabel("B.S.");
				key.setFont(new Font("Gloria Hallelujah", Font.BOLD, 20));
				key.setPreferredSize(new Dimension(30, 30));
				key.setHorizontalAlignment(JLabel.CENTER);
				key.setVerticalAlignment(JLabel.CENTER);
				keys.add(key);
			} else {
				JLabel key = new JLabel(Character.toString((char) (65 + i)));
				key.setFont(new Font("Gloria Hallelujah", Font.BOLD, 20));
				key.setPreferredSize(new Dimension(30, 30));
				key.setHorizontalAlignment(JLabel.CENTER);
				key.setVerticalAlignment(JLabel.CENTER);
				keys.add(key);
			}
		}
		keys.setLayout(new GridLayout(3, 9));
		keys.setSize(new Dimension(540, 180));
		keySet.add(keys, 1);

		JPanel selectorBox = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		selectorBox.setOpaque(false);
		selectorBox.setSize(new Dimension(540, 180));

		renew(hlBox, horBox, verBox);
		verBox.setPreferredSize(new Dimension(540, 0));
		horBox.setPreferredSize(new Dimension(0, 0));

		selectorBox.add(verBox);
		selectorBox.add(horBox);
		selectorBox.add(hlBox);
		keySet.add(selectorBox, 0);

		// Navigation Buttons
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		buttons.setBackground(Color.black);

		unsure.setFont(new Font("Gloria Hallelujah", Font.BOLD, 20));
		unsure.setPreferredSize(new Dimension(239, 50));
		unsure.setHorizontalAlignment(JLabel.CENTER);
		unsure.setVerticalAlignment(JLabel.CENTER);
		unsure.setForeground(Color.white);

		confirm.setFont(new Font("Gloria Hallelujah", Font.BOLD, 20));
		confirm.setPreferredSize(new Dimension(239, 50));
		confirm.setHorizontalAlignment(JLabel.CENTER);
		confirm.setVerticalAlignment(JLabel.CENTER);
		confirm.setForeground(Color.white);

		// Replace with Up-Arrow Icon
		upButton.setFont(new Font("Gloria Hallelujah", Font.BOLD, 20));
		upButton.setPreferredSize(new Dimension(60, 50));
		upButton.setHorizontalAlignment(JLabel.CENTER);
		upButton.setVerticalAlignment(JLabel.CENTER);
		upButton.setForeground(Color.white);

		buttons.setPreferredSize(new Dimension(540, 60));
		buttons.add(unsure);
		buttons.add(confirm);
		buttons.add(upButton);

		keyboard.add(keySet);
		keyboard.add(buttons);

		// Finalization
		contentPanel.add(topText);
		contentPanel.add(nameDisplay);
		contentPanel.add(Box.createRigidArea(new Dimension(900, 45)));
		contentPanel.add(keyboard);

		add(contentPanel);
	}

	public void renew(JComponent b, JComponent v, JComponent h) {
		// Reset Components to Normal
		b.setPreferredSize(new Dimension(60, 60));
		b.setBorder(BorderFactory.createLineBorder(Color.white, 3));
		b.setOpaque(false);
		v.setOpaque(false);
		h.setOpaque(false);
	}
}
