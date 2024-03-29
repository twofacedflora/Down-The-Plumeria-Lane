package downtheplumerialane;

import java.awt.*;
import javax.swing.*;

public class SettingContainer extends JPanel {

	private boolean active;

	private Setting setting;

	protected JPanel controlField;
	private JPanel container;
	private JLabel selector, nameField;

	public SettingContainer(Setting s) {
		setting = s;
		container = new JPanel();
		selector =
			new JLabel(
				Window.createScaledImageIcon("interface/block_selector.png", 256)
			);
		nameField = new JLabel(s.name, SwingConstants.LEFT);

		selector.setAlignmentX(CENTER_ALIGNMENT);
		selector.setVisible(false);

		nameField.setFont(new Font("Gloria Hallelujah", Font.BOLD, 16));
		nameField.setForeground(Color.WHITE);

		switch (s.getClass().toString()) {
			case "class downtheplumerialane.Setting$Scale":
				controlField = new ArrowControls(((Setting.Scale) s).getValue());
				break;
			case "class downtheplumerialane.Setting$Combo":
				controlField = new PressControls(((Setting.Combo) s).getValue());
				break;
			case "class downtheplumerialane.Setting$Key":
				controlField = new PressControls(((Setting.Key) s).getValue());
				break;
			default:
				controlField = new JPanel();
				controlField.setOpaque(false);
				break;
		}

		container.setLayout(new GridLayout(1, 2));
		container.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 16));
		container.setOpaque(false);

		setLayout(new OverlayLayout(this));
		setOpaque(false);

		container.add(nameField);
		container.add(controlField);

		add(container);
		add(selector);
	}

	public boolean getActive() {
		return active;
	}

	public void toggleActive() {
		if (setting instanceof Setting.Scale) {
			if (active) {
				active = false;
				((ArrowControls) controlField).removeActiveArrows();
			} else {
				active = true;
				((ArrowControls) controlField).setActiveArrows();
			}
		}

		if (setting instanceof Setting.Key) {
			if (!active) {
				active = true;
				((PressControls) controlField).promptValue();
			} else {
				active = false;
			}
		}
	}

	public Setting getSetting() {
		return setting;
	}

	public void showSelector() {
		selector.setVisible(true);
	}

	public void hideSelector() {
		selector.setVisible(false);
	}

	public void invertColor() {
		nameField.setForeground(Color.BLACK);
		switch (controlField.getClass().toString()) {
			case "class downtheplumerialane.SettingContainer$ArrowControls":
				((ArrowControls) controlField).invertColor();
				break;
			case "class downtheplumerialane.SettingContainer$PressControls":
				((PressControls) controlField).invertColor();
				break;
			default:
				break;
		}
	}

	public void resetColor() {
		nameField.setForeground(Color.WHITE);
		switch (controlField.getClass().toString()) {
			case "class downtheplumerialane.SettingContainer$ArrowControls":
				((ArrowControls) controlField).resetColor();
				break;
			case "class downtheplumerialane.SettingContainer$PressControls":
				((PressControls) controlField).resetColor();
				break;
			default:
				break;
		}
	}

	public class ArrowControls extends JPanel {

		protected JLabel value;
		private JLabel leftArrow, rightArrow;

		public ArrowControls(int v) {
			setLayout(new GridLayout(1, 3));
			setOpaque(false);

			value = new JLabel(Integer.toString(v));
			leftArrow =
				new JLabel(
					Window.createScaledImageIcon("interface/arrow_left.png", 32)
				);
			rightArrow =
				new JLabel(
					Window.createScaledImageIcon("interface/arrow_right.png", 32)
				);

			value.setFont(new Font("Gloria Hallelujah", Font.BOLD, 16));
			value.setForeground(Color.WHITE);
			value.setHorizontalAlignment(SwingConstants.CENTER);

			add(leftArrow);
			add(value);
			add(rightArrow);
		}

		public void invertColor() {
			value.setForeground(Color.BLACK);
			leftArrow.setIcon(
				Window.createScaledImageIcon("interface/arrow_left_selected.png", 32)
			);
			rightArrow.setIcon(
				Window.createScaledImageIcon("interface/arrow_right_selected.png", 32)
			);
		}

		public void resetColor() {
			value.setForeground(Color.WHITE);
			leftArrow.setIcon(
				Window.createScaledImageIcon("interface/arrow_left.png", 32)
			);
			rightArrow.setIcon(
				Window.createScaledImageIcon("interface/arrow_right.png", 32)
			);
		}

		public void setActiveArrows() {
			leftArrow.setIcon(
				Window.createScaledImageIcon(
					"interface/arrow_left_selected_active.png",
					40
				)
			);
			rightArrow.setIcon(
				Window.createScaledImageIcon(
					"interface/arrow_right_selected_active.png",
					40
				)
			);
		}

		public void removeActiveArrows() {
			leftArrow.setIcon(
				Window.createScaledImageIcon("interface/arrow_left_selected.png", 32)
			);
			rightArrow.setIcon(
				Window.createScaledImageIcon("interface/arrow_right_selected.png", 32)
			);
		}
	}

	public class PressControls extends JPanel {

		private JLabel value;

		public PressControls(String v) {
			setLayout(new GridLayout(1, 1));
			setOpaque(false);

			value = new JLabel(v, SwingConstants.RIGHT);

			value.setFont(new Font("Gloria Hallelujah", Font.BOLD, 16));
			value.setForeground(Color.WHITE);

			add(value);
		}

		public void setValue(String s) {
			value.setText(s);
		}

		public void promptValue() {
			value.setText("press any key");
		}

		public void invertColor() {
			value.setForeground(Color.BLACK);
		}

		public void resetColor() {
			value.setForeground(Color.WHITE);
		}
	}
}
