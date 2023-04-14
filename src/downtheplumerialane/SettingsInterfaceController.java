// TODO: Implement JSON parsing for fetching and writing data to settings.json
// TODO: IMPLEMENT DYNAMIC KEYBINDS.

package downtheplumerialane;

import java.awt.event.*;
import javax.swing.*;

public class SettingsInterfaceController {

	private Window window;
	private SettingsInterface settingsInterface;
	private SettingsMenu activeMenu;

	public SettingsInterfaceController(Window w, SettingsInterface s) {
		window = w;
		settingsInterface = s;
		activeMenu = settingsInterface.getActiveMenu();
	}

	Action upAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			activeMenu.verifyLock();
			activeMenu.moveSelectorUp();
		}
	};

	Action downAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			activeMenu.verifyLock();
			activeMenu.moveSelectorDown();
		}
	};

	Action leftAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			activeMenu.verifyLock();
			activeMenu.moveSelectorLeft();
		}
	};

	Action rightAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			activeMenu.verifyLock();
			activeMenu.moveSelectorRight();
		}
	};

	Action confirmAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			if (
				(
					activeMenu.selectedSetting.getSetting() instanceof Setting.Scale ||
					activeMenu.selectedSetting.getSetting() instanceof Setting.Key
				) &&
				!(activeMenu.selectedSetting.getActive())
			) {
				activeMenu.selectedSetting.toggleActive();
			} else if (
				activeMenu.selectedSetting.getSetting() instanceof Setting.Combo
			) {
				(
					(Setting.Combo) activeMenu.selectedSetting.getSetting()
				).setNextValue();
				((Setting.Combo) activeMenu.selectedSetting.getSetting()).updateJson();
				(
					(SettingContainer.PressControls) (
						activeMenu.selectedSetting.getSetting().getContainer().controlField
					)
				).value.setText(
						((Setting.Combo) activeMenu.selectedSetting.getSetting()).value
					);
			} else if (
				(activeMenu.selectedSetting.getSetting() instanceof Setting.SubMenu) &&
				((Setting.SubMenu) activeMenu.selectedSetting.getSetting()).submenu ==
				"keyconf"
			) {
				JOptionPane.showMessageDialog(
					window,
					"The developer was too lazy to implement dynamic keybinds, so they disabled the key config menu.",
					"Feature Unavailable",
					JOptionPane.INFORMATION_MESSAGE
				);
			}
		}
	};

	Action backAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			if (
				(activeMenu.selectedSetting.getSetting() instanceof Setting.Scale) &&
				activeMenu.selectedSetting.getActive()
			) {
				activeMenu.selectedSetting.toggleActive();
			} else if (
				activeMenu == SettingGroup.getSettingGroups().get(0).getMenu()
			) {
				Setting.updateJsonFile();
				activeMenu.reset();
				window.switchInterface("title");
			} else {
				settingsInterface.switchMenu(SettingGroup.getSettingGroups().get(0));
			}
		}
	};

	public void setKeybinds() {
		settingsInterface
			.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("UP"), "up");
		settingsInterface
			.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("DOWN"), "down");
		settingsInterface
			.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("LEFT"), "left");
		settingsInterface
			.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("RIGHT"), "right");
		settingsInterface
			.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("C"), "confirm");
		settingsInterface
			.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("X"), "back");

		settingsInterface.getActionMap().put("up", upAction);
		settingsInterface.getActionMap().put("down", downAction);
		settingsInterface.getActionMap().put("left", leftAction);
		settingsInterface.getActionMap().put("right", rightAction);
		settingsInterface.getActionMap().put("confirm", confirmAction);
		settingsInterface.getActionMap().put("back", backAction);
	}
}
