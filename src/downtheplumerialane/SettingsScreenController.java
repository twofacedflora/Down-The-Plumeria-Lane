// TODO: Implement JSON parsing for fetching and writing data to settings.json
// TODO: Implement key bindings

package downtheplumerialane;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.sound.midi.ControllerEventListener;
import javax.swing.*;
import org.json.JSONObject;

public class SettingsScreenController {

	private String settingsJSONString;
	private JSONObject settingsJSONObject; // = new JSONObject(settingsJSONString);

	private static int selectedSetting = 0;
	private static String[][] settingRestraints;
	private static boolean scaleControlsActive = false;

	private Window window;
	private SettingsScreen settingsScreen;
	private ArrayList<JPanel> options;

	public SettingsScreenController(
		Window w,
		SettingsScreen s,
		ArrayList<JPanel> o,
		String[][] r
	) {
		window = w;
		settingsScreen = s;
		options = o;
		settingRestraints = r;
	}

	public ImageIcon createLabelIcon(String f) {
		return new ImageIcon(
			new ImageIcon(
				getClass().getClassLoader().getResource("resources/images/" + f)
			)
				.getImage()
				.getScaledInstance(40, -1, Image.SCALE_SMOOTH)
		);
	}

	public void setControls(int t, int i) {
		try {
			JPanel controls = (JPanel) (
				(JPanel) options.get(i).getComponent(0)
			).getComponent(1);
			JLabel value;

			switch (t) {
				case 0:
					break;
				case 1:
					value = new JLabel(settingRestraints[i][0]);
					JPanel scaleLayout = new JPanel(new GridLayout(1, 3));

					scaleLayout.setOpaque(false);

					scaleLayout.add(
						new JLabel(createLabelIcon("interface/arrow_left_inactive.png"))
					);

					value.setFont(new Font("Gloria Hallelujah", Font.BOLD, 21));
					value.setForeground(Color.WHITE);
					value.setHorizontalAlignment(SwingConstants.CENTER);
					scaleLayout.add(value);

					scaleLayout.add(
						new JLabel(createLabelIcon("interface/arrow_right_inactive.png"))
					);

					controls.add(scaleLayout);

					break;
				default:
					value = new JLabel(settingRestraints[i][0]);

					value.setFont(new Font("Gloria Hallelujah", Font.BOLD, 21));
					value.setForeground(Color.WHITE);
					value.setHorizontalAlignment(SwingConstants.CENTER);

					controls.add(value);

					break;
			}
		} catch (NullPointerException | IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	public void setInactiveState(int t) {
		try {
			((JPanel) options.get(selectedSetting).getComponent(1)).setVisible(true);

			JLabel name = (JLabel) (
				(JPanel) options.get(selectedSetting).getComponent(0)
			).getComponent(0);
			JPanel controls = (JPanel) (
				(JPanel) options.get(selectedSetting).getComponent(0)
			).getComponent(1);

			name.setForeground(Color.WHITE);

			switch (t) {
				case 0:
					break;
				case 1:
					JPanel scaleDiv = (JPanel) controls.getComponent(0);

					((JLabel) scaleDiv.getComponents()[0]).setIcon(
							createLabelIcon("interface/arrow_left_inactive.png")
						);
					scaleDiv.getComponents()[1].setForeground(Color.WHITE);
					((JLabel) scaleDiv.getComponents()[2]).setIcon(
							createLabelIcon("interface/arrow_right_inactive.png")
						);
					break;
				default:
					controls.getComponent(0).setForeground(Color.WHITE);
					break;
			}
		} catch (NullPointerException | IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	public void setActiveState(int t) {
		try {
			((JPanel) options.get(selectedSetting).getComponent(1)).setVisible(false);

			JLabel name = (JLabel) (
				(JPanel) options.get(selectedSetting).getComponent(0)
			).getComponent(0);
			JPanel controls = (JPanel) (
				(JPanel) options.get(selectedSetting).getComponent(0)
			).getComponent(1);

			name.setForeground(Color.BLACK);

			switch (t) {
				case 0:
					break;
				case 1:
					JPanel scaleDiv = (JPanel) controls.getComponent(0);

					((JLabel) scaleDiv.getComponents()[0]).setIcon(
							createLabelIcon("interface/arrow_left_active.png")
						);
					scaleDiv.getComponents()[1].setForeground(Color.BLACK);
					((JLabel) scaleDiv.getComponents()[2]).setIcon(
							createLabelIcon("interface/arrow_right_active.png")
						);
					break;
				default:
					controls.getComponent(0).setForeground(Color.BLACK);
					break;
			}
		} catch (NullPointerException | IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	public void enableScaleControls() {
		scaleControlsActive = true;

		JPanel scaleDiv = (JPanel) (
			(JPanel) (
				(JPanel) options.get(selectedSetting).getComponent(0)
			).getComponent(1)
		).getComponent(0);

		((JLabel) scaleDiv.getComponents()[0]).setIcon(
				createLabelIcon("interface/arrow_left_active_enabled.png")
			);
		((JLabel) scaleDiv.getComponents()[2]).setIcon(
				createLabelIcon("interface/arrow_right_active_enabled.png")
			);
	}

	public void disableScaleControls() {
		scaleControlsActive = false;

		JPanel scaleDiv = (JPanel) (
			(JPanel) (
				(JPanel) options.get(selectedSetting).getComponent(0)
			).getComponent(1)
		).getComponent(0);

		((JLabel) scaleDiv.getComponents()[0]).setIcon(
				createLabelIcon("interface/arrow_left_active.png")
			);
		((JLabel) scaleDiv.getComponents()[2]).setIcon(
				createLabelIcon("interface/arrow_right_active.png")
			);
	}

	Action upAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			System.out.println("up");
			if (
				!scaleControlsActive && selectedSetting != 0 && selectedSetting != 1
			) {
				setInactiveState(settingRestraints[selectedSetting].length);
				selectedSetting -= 2;
				setActiveState(settingRestraints[selectedSetting].length);
			}
		}
	};

	Action downAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			System.out.println("down");
			if (
				!scaleControlsActive &&
				selectedSetting != settingRestraints.length - 1 &&
				selectedSetting != settingRestraints.length - 2
			) {
				setInactiveState(settingRestraints[selectedSetting].length);
				selectedSetting += 2;
				setActiveState(settingRestraints[selectedSetting].length);
			}
		}
	};

	Action leftAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			System.out.println("left");
			if (!scaleControlsActive && selectedSetting % 2 == 1) {
				setInactiveState(settingRestraints[selectedSetting].length);
				selectedSetting--;
				setActiveState(settingRestraints[selectedSetting].length);
			}
		}
	};

	Action rightAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			System.out.println("right");
			if (!scaleControlsActive && selectedSetting % 2 == 0) {
				setInactiveState(settingRestraints[selectedSetting].length);
				selectedSetting++;
				setActiveState(settingRestraints[selectedSetting].length);
			}
		}
	};

	Action confirmAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			System.out.println("c");
			if (!scaleControlsActive) {
				switch (settingRestraints[selectedSetting].length) {
					case 0:
						break;
					case 1:
						enableScaleControls();
						break;
					default:
						break;
				}
			}
		}
	};

	Action backAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			System.out.println("x");
			if (scaleControlsActive) {
				disableScaleControls();
			} else {
				window.switchToScreen("Title Screen");
			}
		}
	};

	public void setKeybinds() {
		settingsScreen
			.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("UP"), "up");
		settingsScreen
			.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("DOWN"), "down");
		settingsScreen
			.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("LEFT"), "left");
		settingsScreen
			.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("RIGHT"), "right");
		settingsScreen
			.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("C"), "confirm");
		settingsScreen
			.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("X"), "back");

		settingsScreen.getActionMap().put("up", upAction);
		settingsScreen.getActionMap().put("down", downAction);
		settingsScreen.getActionMap().put("left", leftAction);
		settingsScreen.getActionMap().put("right", rightAction);
		settingsScreen.getActionMap().put("confirm", confirmAction);
		settingsScreen.getActionMap().put("back", backAction);
	}
}
