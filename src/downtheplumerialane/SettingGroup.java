package downtheplumerialane;

import java.util.*;

public class SettingGroup {

	private static ArrayList<SettingGroup> settingGroupList = new ArrayList<>();

	protected String name;

	private SettingsMenu menu;
	private ArrayList<Setting> settingList;

	public SettingGroup(String n) {
		name = n;
		settingList = new ArrayList<Setting>();
		settingGroupList.add(this);
		menu = new SettingsMenu(this);
	}

	public ArrayList<Setting> getSettings() {
		return settingList;
	}

	public static ArrayList<SettingGroup> getSettingGroups() {
		return settingGroupList;
	}

	public SettingsMenu getMenu() {
		return menu;
	}
}
