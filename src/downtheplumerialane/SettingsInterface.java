package downtheplumerialane;

import java.awt.*;
import javax.swing.*;

public class SettingsInterface extends PanelWithBackground {

	private SettingsInterfaceController controller;
	private SettingsMenu activeMenu;
	private JPanel menuPanel;

	public SettingsInterface(Window w) {
		super("background/void.png");
		Window.loadFonts(this);
		Setting.setOutputFile();
		Setting.initializeJson();

		setLayout(new GridBagLayout());

		menuPanel = new JPanel();
		menuPanel.setLayout(new CardLayout());
		menuPanel.setOpaque(false);

		SettingGroup main = new SettingGroup("main");
		main.getSettings().add(new Setting.Scale("Master", "MasterVolume", 0, 100));
		main.getSettings().add(new Setting.Scale("FX", "FXVolume", 0, 100));
		main.getSettings().add(new Setting.Scale("Music", "MusicVolume", 0, 100));
		main
			.getSettings()
			.add(new Setting.Scale("Ambience", "AmbientVolume", 0, 100));
		main
			.getSettings()
			.add(
				new Setting.Combo(
					"Fullscreen",
					"Fullscreen",
					new String[] { "NO", "YES" }
				)
			);
		main
			.getSettings()
			.add(
				new Setting.Combo(
					"Text Speed",
					"TextSpeed",
					new String[] { "SLOW", "FAST", "INSTANT" }
				)
			);
		main
			.getSettings()
			.add(
				new Setting.Combo(
					"Debug Info",
					"ShowDebugInfo",
					new String[] { "NO", "YES" }
				)
			);
		main.getSettings().add(new Setting.SubMenu("Key Config", "keyconf"));

		SettingGroup keyConfig = new SettingGroup("keyconf");
		keyConfig.getSettings().add(new Setting.Key("Up", "KBUp"));
		keyConfig.getSettings().add(new Setting.Key("Down", "KBDown"));
		keyConfig.getSettings().add(new Setting.Key("Left", "KBLeft"));
		keyConfig.getSettings().add(new Setting.Key("Right", "KBRight"));
		keyConfig.getSettings().add(new Setting.Key("Confirm", "KBConfirm"));
		keyConfig.getSettings().add(new Setting.Key("Back", "KBBack"));

		for (Setting s : main.getSettings()) {
			main.getMenu().add(s.getContainer());
		}
		for (Setting s : keyConfig.getSettings()) {
			keyConfig.getMenu().add(s.getContainer());
		}

		main.getMenu().selectedSetting = main.getSettings().get(0).getContainer();
		main.getMenu().setSelectedState();

		keyConfig.getMenu().selectedSetting =
			keyConfig.getSettings().get(0).getContainer();
		keyConfig.getMenu().setSelectedState();

		menuPanel.add(main.getMenu(), "main");
		menuPanel.add(keyConfig.getMenu(), "keyconf");

		add(menuPanel);

		activeMenu = main.getMenu();

		controller = new SettingsInterfaceController(w, this);
		controller.setKeybinds();
	}

	public SettingsMenu getActiveMenu() {
		return activeMenu;
	}

	public void switchMenu(SettingGroup g) {
		CardLayout layout = (CardLayout) menuPanel.getLayout();
		layout.show(menuPanel, g.name);
		activeMenu = g.getMenu();
	}
}
