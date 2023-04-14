// TODO: use GSON instead of org.json

package downtheplumerialane;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;
import org.json.*;

public class Setting {

	protected static final String outputPath = ".data/settings.json";
	protected static File outputFile;

	protected static FileWriter fileWriter;
	private static JSONTokener jsonTokener;
	protected static JSONObject jsonObject;
	private static ArrayList<Setting> settingList = new ArrayList<>();

	protected String name;

	private SettingContainer container;

	public Setting(String n) {
		name = n;
		settingList.add(this);
	}

	public static void setOutputFile() {
		try {
			outputFile =
				new File(
					new File(
						Main.class.getProtectionDomain()
							.getCodeSource()
							.getLocation()
							.toURI()
					)
						.getParent() +
					File.separator +
					outputPath
				);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public static void initializeJson() {
		try {
			jsonTokener =
				new JSONTokener(
					new BufferedInputStream(new FileInputStream(outputFile))
				);
			jsonObject = new JSONObject(jsonTokener);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void updateJsonFile() {
		try {
			fileWriter = new FileWriter(outputFile, false);
			fileWriter.write(jsonObject.toString());
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Setting> getSettings() {
		return settingList;
	}

	public SettingContainer getContainer() {
		return container;
	}

	public static class Scale extends Setting {

		protected int value;
		private int lowerLimit, upperLimit;
		private String jsonKey;

		public Scale(String n, String k, int l, int u) {
			super(n);
			jsonKey = k;
			value = jsonObject.getInt(k);
			lowerLimit = l;
			upperLimit = u;
			super.container = new SettingContainer(this);
		}

		public void incrementValue() {
			if (value < upperLimit) value++;
		}

		public void decrementValue() {
			if (value > lowerLimit) value--;
		}

		public void updateJson() {
			jsonObject.remove(jsonKey);
			jsonObject.put(jsonKey, value);
		}
	}

	public static class Combo extends Setting {

		protected String value;
		private int index;
		private String jsonKey;
		private String[] choiceList;

		public Combo(String n, String k, String[] c) {
			super(n);
			jsonKey = k;
			index = jsonObject.getInt(k);
			value = c[index];
			choiceList = c;
			super.container = new SettingContainer(this);
		}

		public void setNextValue() {
			if (choiceList.length - index == 1) {
				index = 0;
			} else {
				index++;
			}
			value = choiceList[index];
		}

		public void updateJson() {
			jsonObject.remove(jsonKey);
			jsonObject.put(jsonKey, index);
		}
	}

	public static class Key extends Setting {

		protected String value;
		private String jsonKey;

		public Key(String n, String k) {
			super(n);
			jsonKey = k;
			value = jsonObject.getString(k);
			super.container = new SettingContainer(this);
		}

		public void updateJson() {
			jsonObject.remove(jsonKey);
			jsonObject.put(jsonKey, value);
		}

		public void promptValue() {
			value = "Press any key...";
		}
	}

	public static class SubMenu extends Setting {

		protected String submenu;

		public SubMenu(String n, String s) {
			super(n);
			submenu = s;
			super.container = new SettingContainer(this);
		}
	}
}
