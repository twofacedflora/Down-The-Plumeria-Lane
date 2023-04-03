package downtheplumerialane;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

public class SettingsScreen extends DecoratedPanel {

	private static final String[] optionNames = {
		"Master",
		"Fullscreen",
		"FX",
		"Text Speed",
		"Music",
		"Debug Info",
		"Ambience",
		"Key Config",
	};

	private static final String[][] settingRestraints = {
		{ "100" },
		{ "YES", "NO" },
		{ "100" },
		{ "SLOW", "FAST", "INSTANT" },
		{ "100" },
		{ "YES", "NO" },
		{ "100" },
		{},
	};

	private SettingsScreenController controller;
	private JPanel optionPanel;
	private ArrayList<JPanel> options;

	public SettingsScreen(Window w) {
		super("background/void.png");
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Window.loadFonts(this, ge, Window.CUSTOM_FONTS);

		setLayout(new GridBagLayout());

		optionPanel = new JPanel();
		optionPanel.setLayout(new GridLayout(4, 2, 50, 0));
		optionPanel.setPreferredSize(new Dimension(700, 400));
		optionPanel.setAlignmentX(CENTER_ALIGNMENT);
		optionPanel.setOpaque(false);
		options = new ArrayList<JPanel>();

		for (String o : optionNames) {
			JPanel settingContainer = new SettingContainer(w, o);
			options.add(settingContainer);
			optionPanel.add(settingContainer);
		}

		add(optionPanel);

		controller =
			new SettingsScreenController(w, this, options, settingRestraints);
		controller.setKeybinds();
		for (int i = 0; i < settingRestraints.length; i++) {
			controller.setControls(settingRestraints[i].length, i);
		}
		controller.setActiveState(settingRestraints[0].length);
	}
}
