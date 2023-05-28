package downtheplumerialane;

import java.awt.event.*;

public class ActiveKeyConfigListener extends KeyAdapter {

	Window window;
	SettingsMenu menu;
	ControlMap.Command command;
	Setting.Key setting;

	public ActiveKeyConfigListener(Window w, SettingsMenu m, int p, Setting s) {
		window = w;
		menu = m;
		command = ControlMap.Command.values()[p];
		setting = (Setting.Key) s;
	}

	@Override
	public void keyPressed(KeyEvent evt) {
		boolean invalid = false;
		String keyText = KeyEvent.getKeyText(evt.getKeyCode());

		// TODO: add cases for non-macOS/60%< layout keys (e.g. Windows key, prtsc)
		switch (keyText) {
			case "⏎":
				keyText = "ENTER";
				break;
			case "⌫":
				keyText = "BACK_SPACE";
				break;
			case "␣":
				keyText = "SPACE";
				break;
			case "↑":
				keyText = "UP";
				break;
			case "↓":
				keyText = "DOWN";
				break;
			case "←":
				keyText = "LEFT";
				break;
			case "→":
				keyText = "RIGHT";
				break;
			case "⇧":
				invalid = true;
				break;
			case "⌃":
				invalid = true;
				break;
			case "⌥":
				invalid = true;
				break;
			case "⌘":
				invalid = true;
				break;
			case "Unknown keyCode: 0x0":
				invalid = true;
				break;
		}

		if (
			!keyText.equals(setting.getValue()) &&
			ControlMap.getMappedKeys().contains(keyText)
		) {
			invalid = true;
		}

		if (!invalid) {
			ControlMap.unmapKeybind(command);
			setting.setValue(keyText);
			setting.updateJson();
			(
				(SettingContainer.PressControls) (setting.getContainer().controlField)
			).setValue(setting.getValue());
			Setting.updateJsonFile();
			ControlMap.mapKeybind(command);
			menu.selectedSetting.toggleActive();
			ControlMap.setUpdated(true);
			window.removeKeyListener(this);
		}
	}
}
