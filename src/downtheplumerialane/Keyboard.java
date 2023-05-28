package downtheplumerialane;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

public class Keyboard extends JPanel {

	private int selectorPosition = 0;
	private boolean capsActive = true;

	private JLabel box;
	private JPanel alphaShiftGrid, bottomRow, contentPanel;

	public Keyboard() {
		alphaShiftGrid = new JPanel();
		alphaShiftGrid.setLayout(new GridLayout(3, 9));
		alphaShiftGrid.setOpaque(false);

		for (int i = 0; i < 26; i++) {
			alphaShiftGrid.add(new Key((char) (65 + i)));
		}

		alphaShiftGrid.add(
			new Key(
				Window.createScaledImageIcon("interface/shift_arrow_active.png", 24)
			)
		);

		bottomRow = new JPanel();
		bottomRow.setLayout(new GridLayout(1, 2));
		bottomRow.setOpaque(false);

		bottomRow.add(new Key("Confirm"));
		bottomRow.add(new Key("Delete"));

		box =
			new JLabel(
				Window.createScaledImageIcon("interface/keyboard_box.png", 432)
			);
		box.setAlignmentX(CENTER_ALIGNMENT);
		box.setAlignmentY(CENTER_ALIGNMENT);

		contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setOpaque(false);

		setMaximumSize(new Dimension(432, 192));
		setLayout(new OverlayLayout(this));
		setAlignmentX(CENTER_ALIGNMENT);
		setAlignmentY(CENTER_ALIGNMENT);
		setOpaque(false);

		contentPanel.add(alphaShiftGrid);
		contentPanel.add(bottomRow);

		add(contentPanel);
		add(box);

		Key.getKeys().get(selectorPosition).showSelector();
	}

	public int getSelectorPos() {
		return selectorPosition;
	}

	public void moveSelectorUp() {
		if (selectorPosition >= 9) {
			Key.getKeys().get(selectorPosition).hideSelector();

			if (selectorPosition == 28) {
				selectorPosition = 22;
			} else {
				selectorPosition -= 9;
			}

			Key.getKeys().get(selectorPosition).showSelector();
		}
	}

	public void moveSelectorDown() {
		if (selectorPosition <= 26) {
			Key.getKeys().get(selectorPosition).hideSelector();

			if (selectorPosition >= 18 && selectorPosition <= 21) {
				selectorPosition = 27;
			} else if (selectorPosition >= 22 && selectorPosition <= 26) {
				selectorPosition = 28;
			} else {
				selectorPosition += 9;
			}

			Key.getKeys().get(selectorPosition).showSelector();
		}
	}

	public void moveSelectorLeft() {
		if (selectorPosition % 9 != 0) {
			Key.getKeys().get(selectorPosition).hideSelector();
			selectorPosition--;
			Key.getKeys().get(selectorPosition).showSelector();
		}
	}

	public void moveSelectorRight() {
		if (selectorPosition % 9 != 8 && selectorPosition != 28) {
			Key.getKeys().get(selectorPosition).hideSelector();
			selectorPosition++;
			Key.getKeys().get(selectorPosition).showSelector();
		}
	}

	public void toggleCaps() {
		int shift;

		if (capsActive) {
			capsActive = false;
			shift = 32;
			Key
				.getKeys()
				.get(26)
				.setIcon(Window.createScaledImageIcon("interface/shift_arrow.png", 24));
		} else {
			capsActive = true;
			shift = -32;
			Key
				.getKeys()
				.get(26)
				.setIcon(
					Window.createScaledImageIcon("interface/shift_arrow_active.png", 24)
				);
		}

		for (int i = 0; i < 26; i++) {
			char keyChar = Key.getKeys().get(i).getText().charAt(0);
			char newChar = (char) (keyChar + shift);
			Key.getKeys().get(i).setText(Character.toString(newChar));
		}
	}
}
