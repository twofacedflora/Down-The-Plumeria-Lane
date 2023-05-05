package downtheplumerialane;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CharacterNamingInterfaceController {

	private int buttonHover;

	private Window window;
	private CharacterNamingInterface characterNamingInterface;
	private JLayeredPane keySet;
	private JLabel hlBox, verBox, horBox, topText, nameText, unsure, confirm, upButton;

	public CharacterNamingInterfaceController(
		Window w,
		CharacterNamingInterface c
	) {
		window = w;
		characterNamingInterface = c;
		keySet = c.keySet;
		buttonHover = c.buttonHover;
		hlBox = c.hlBox;
		verBox = c.verBox;
		horBox = c.horBox;
		topText = c.topText;
		nameText = c.nameText;
		unsure = c.unsure;
		confirm = c.confirm;
		upButton = c.upButton;
	}

	Action upAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			if (buttonHover > 8 && buttonHover < 27) {
				buttonHover -= 9;
				if (buttonHover > 8) {
					verBox.setPreferredSize(new Dimension(540, 60));
				} else {
					verBox.setPreferredSize(new Dimension(540, 0));
				}
			}
			endmymisery();
		}
	};

	Action downAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			if (buttonHover < 18) {
				buttonHover += 9;
				if (buttonHover < 18) {
					verBox.setPreferredSize(new Dimension(540, 60));
				} else {
					verBox.setPreferredSize(new Dimension(540, 120));
				}
			} else if (buttonHover < 27) {
				buttonHover = 22 + (buttonHover + 2) / 4;
			}
			endmymisery();
		}
	};

	Action leftAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			if (buttonHover % 9 != 0 && buttonHover < 27) {
				buttonHover -= 1;
				horBox.setPreferredSize(new Dimension(60 * (buttonHover % 9), 0));
			} else if (buttonHover > 27) buttonHover -= 1;
			endmymisery();
		}
	};

	Action rightAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			if (buttonHover % 9 != 8 && buttonHover < 26) {
				buttonHover += 1;
				horBox.setPreferredSize(new Dimension(60 * (buttonHover % 9), 0));
			} else if (buttonHover > 26 && buttonHover != 29) buttonHover += 1;
			endmymisery();
		}
	};

	Action confirmAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			if (buttonHover < 27) Player.alterName(buttonHover); else {
				switch (buttonHover) {
					// Auto-Set
					case 27:
						Player.setName();
						window.switchInterface("ingame");
						break;
					// Confirm
					case 28:
						String name = Player.getName();
						if (!name.equals("")) {
							for (int p = 0; p < name.length(); p++) {
								if (p > 0) name =
									name.substring(0, p) +
									String.valueOf(name.charAt(p)).toLowerCase() +
									name.substring(p + 1, name.length());
							}
							window.switchInterface("ingame");
						} else {
							topText.setText("Please enter a name.");
						}
						break;
					// Return to Keyboard
					case 29:
						buttonHover = 26;
						verBox.setPreferredSize(new Dimension(540, 120));
						horBox.setPreferredSize(new Dimension(480, 0));
						break;
				}
			}
			nameText.setText(Player.getDisplayName());
			endmymisery();
		}
	};

	Action backAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			window.switchInterface("title");
		}
	};

	public void endmymisery() { // FIXME
		// Set Where the Highlight Box Goes on the Keyboard
		characterNamingInterface.renew(hlBox, horBox, verBox);
		keySet.remove(0);
		JPanel selectorBox = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		selectorBox.setOpaque(false);
		selectorBox.setSize(new Dimension(540, 180));

		selectorBox.add(verBox);
		selectorBox.add(horBox);
		if (buttonHover < 27) selectorBox.add(hlBox);
		keySet.add(selectorBox, 0);

		if (buttonHover == 27) unsure.setBorder(
			BorderFactory.createLineBorder(Color.white, 3)
		); else unsure.setBorder(null);
		if (buttonHover == 28) confirm.setBorder(
			BorderFactory.createLineBorder(Color.white, 3)
		); else confirm.setBorder(null);
		if (buttonHover == 29) upButton.setBorder(
			BorderFactory.createLineBorder(Color.white, 3)
		); else upButton.setBorder(null);
	}

	public void setKeybinds() {
		characterNamingInterface
			.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("UP"), "up");
		characterNamingInterface
			.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("DOWN"), "down");
		characterNamingInterface
			.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("LEFT"), "left");
		characterNamingInterface
			.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("RIGHT"), "right");
		characterNamingInterface
			.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("C"), "confirm");
		characterNamingInterface
			.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("X"), "back");

		characterNamingInterface.getActionMap().put("up", upAction);
		characterNamingInterface.getActionMap().put("down", downAction);
		characterNamingInterface.getActionMap().put("left", leftAction);
		characterNamingInterface.getActionMap().put("right", rightAction);
		characterNamingInterface.getActionMap().put("confirm", confirmAction);
		characterNamingInterface.getActionMap().put("back", backAction);
	}
}
