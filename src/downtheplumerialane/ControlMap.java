package downtheplumerialane;

import java.util.*;
import javax.swing.*;
import javax.swing.InputMap;

public class ControlMap {

	enum Command {
		UP,
		DOWN,
		LEFT,
		RIGHT,
		CONFIRM,
		BACK,
	}

	private static boolean updated = false;
	private static ArrayList<String> mappedKeys = new ArrayList<>();
	private static ArrayList<ControlMap> keyMapList = new ArrayList<>();

	private InputMap inputMap;
	private ActionMap actionMap;

	private ArrayList<Setting> keySettingList = SettingGroup
		.getSettingGroups()
		.get(1)
		.getSettings();

	public ControlMap(JPanel itf) {
		inputMap = itf.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		actionMap = itf.getActionMap();
		keyMapList.add(this);
	}

	public static ArrayList<String> getMappedKeys() {
		return mappedKeys;
	}

	public static boolean getUpdated() {
		return updated;
	}

	public static void setUpdated(boolean b) {
		updated = b;
	}

	public void mapAction(Command c, Action a) {
		int i = c.ordinal();
		mappedKeys.add(((Setting.Key) keySettingList.get(i)).getValue());
		inputMap.put(getSavedBinding(i), c);
		actionMap.put(c, a);
	}

	public void unmapAction(Command c) {
		int i = c.ordinal();
		mappedKeys.remove(((Setting.Key) keySettingList.get(i)).getValue());
		inputMap.remove(getSavedBinding(i));
	}

	public static void mapKeybind(Command c) {
		for (ControlMap km : keyMapList) {
			Action a = km.actionMap.get(c);
			if (a != null) {
				km.mapAction(c, a);
			}
		}
	}

	public static void unmapKeybind(Command c) {
		for (ControlMap km : keyMapList) {
			Action a = km.actionMap.get(c);
			if (a != null) {
				km.unmapAction(c);
			}
		}
	}

	private KeyStroke getSavedBinding(int i) {
		return KeyStroke.getKeyStroke(
			((Setting.Key) keySettingList.get(i)).getValue()
		);
	}
}
