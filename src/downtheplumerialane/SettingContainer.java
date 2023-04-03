package downtheplumerialane;

import java.awt.*;
import javax.swing.*;

public class SettingContainer extends JPanel {

	private JPanel container, div, controls;
	private JLabel selector, name;

	public SettingContainer(Window w, String o) {
		container = new JPanel();
		div = new JPanel();
		selector =
			new JLabel(
				new ImageIcon(
					getClass()
						.getClassLoader()
						.getResource("resources/images/interface/selected_option.png")
				)
			);
		controls = new JPanel();
		name = new JLabel(o, SwingConstants.LEFT);

		selector.setAlignmentX(CENTER_ALIGNMENT);

		name.setFont(new Font("Gloria Hallelujah", Font.BOLD, 21));
		name.setForeground(Color.WHITE);

		controls.setLayout(new GridLayout(1, 1));
		controls.setOpaque(false);

		div.setBackground(Color.BLACK);

		container.setLayout(new GridLayout(1, 2));
		container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		container.setOpaque(false);

		setLayout(new OverlayLayout(this));
		setOpaque(false);

		container.add(name);
		container.add(controls);

		add(container);
		add(div);
		add(selector);
	}
}
