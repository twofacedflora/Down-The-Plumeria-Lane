package downtheplumerialane;

import java.awt.event.*;
import javax.swing.*;

public class SettingsInterfaceController {

	private Window window;
	private ControlMap keyMap;
	private SettingsInterface settingsInterface;
	private SettingsMenu activeMenu;

	public SettingsInterfaceController(Window w, SettingsInterface itf) {
		window = w;
		settingsInterface = itf;
		activeMenu = itf.getActiveMenu();
		keyMap = new ControlMap(itf);
		keyMap.mapAction(ControlMap.Command.UP, upAction);
		keyMap.mapAction(ControlMap.Command.DOWN, downAction);
		keyMap.mapAction(ControlMap.Command.LEFT, leftAction);
		keyMap.mapAction(ControlMap.Command.RIGHT, rightAction);
		keyMap.mapAction(ControlMap.Command.CONFIRM, confirmAction);
		keyMap.mapAction(ControlMap.Command.BACK, backAction);
	}

	Action upAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			if (ControlMap.getUpdated()) {
				ControlMap.setUpdated(false);
			} else {
				activeMenu.verifyLock();
				activeMenu.moveSelectorUp();
			}
		}
	};

	Action downAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			if (ControlMap.getUpdated()) {
				ControlMap.setUpdated(false);
			} else {
				activeMenu.verifyLock();
				activeMenu.moveSelectorDown();
			}
		}
	};

	Action leftAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			if (ControlMap.getUpdated()) {
				ControlMap.setUpdated(false);
			} else {
				activeMenu.verifyLock();
				activeMenu.moveSelectorLeft();
			}
		}
	};

	Action rightAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			if (ControlMap.getUpdated()) {
				ControlMap.setUpdated(false);
			} else {
				activeMenu.verifyLock();
				activeMenu.moveSelectorRight();
			}
		}
	};

	Action confirmAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			Setting setting = settingsInterface
				.getActiveMenu()
				.selectedSetting.getSetting();
			SettingContainer container = activeMenu.selectedSetting;

			if (ControlMap.getUpdated()) {
				ControlMap.setUpdated(false);
			} else if (setting instanceof Setting.Scale && !(container.getActive())) {
				container.toggleActive();
			} else if (setting instanceof Setting.Key && !(container.getActive())) {
				container.toggleActive();
				window.addKeyListener(
					new ActiveKeyConfigListener(
						window,
						activeMenu,
						activeMenu.selectorPosition,
						setting
					)
				);
			} else if (setting instanceof Setting.Combo) {
				((Setting.Combo) setting).setNextValue();
				((Setting.Combo) setting).updateJson();
				(
					(SettingContainer.PressControls) (setting.getContainer().controlField)
				).setValue(((Setting.Combo) setting).getValue());
			} else if (
				(setting instanceof Setting.SubMenu) &&
				((Setting.SubMenu) setting).submenu == "keyconf"
			) {
				settingsInterface.setActiveMenu(SettingGroup.getSettingGroups().get(1));
				activeMenu = SettingGroup.getSettingGroups().get(1).getMenu();
			}
		}
	};

	Action backAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			Setting setting = settingsInterface
				.getActiveMenu()
				.selectedSetting.getSetting();
			SettingContainer container = activeMenu.selectedSetting;

			if (ControlMap.getUpdated()) {
				ControlMap.setUpdated(false);
			} else if ((setting instanceof Setting.Scale) && container.getActive()) {
				container.toggleActive();
			} else if ((setting instanceof Setting.Key) && container.getActive()) {
				// disable key binding
			} else if (
				activeMenu == SettingGroup.getSettingGroups().get(0).getMenu()
			) {
				Setting.updateJsonFile();
				activeMenu.reset();
				window.goToPrevInterface();
			} else {
				settingsInterface.setActiveMenu(SettingGroup.getSettingGroups().get(0));
				activeMenu = SettingGroup.getSettingGroups().get(0).getMenu();
			}
		}
	};
}
