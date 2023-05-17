// TODO: use GSON instead of org.json

package downtheplumerialane;

import com.google.gson.*;
import java.io.*;
import java.net.URISyntaxException;
import java.util.*;

public class Setting {

	protected static final String outputPath = ".data/settings.json";
	protected static File outputFile;

	private static FileWriter fileWriter;
	private static JsonObject jsonObject;
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
			Gson gson = new Gson();
			jsonObject =
				gson
					.fromJson(
						new InputStreamReader(
							new BufferedInputStream(new FileInputStream(outputFile))
						),
						JsonElement.class
					)
					.getAsJsonObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void updateJsonFile() {
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String prettyOutput = gson.toJson(jsonObject);
			fileWriter = new FileWriter(outputFile, false);
			fileWriter.write(prettyOutput);
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

		private int value;
		private int lowerLimit, upperLimit;
		private String jsonKey;

		public Scale(String n, String k, int l, int u) {
			super(n);
			jsonKey = k;
			value = jsonObject.get(k).getAsInt();
			lowerLimit = l;
			upperLimit = u;
			super.container = new SettingContainer(this);
		}

		public int getValue() {
			return value;
		}

		public void incrementValue() {
			if (value < upperLimit) {
				value++;
			}
		}

		public void decrementValue() {
			if (value > lowerLimit) {
				value--;
			}
		}

		public void updateJson() {
			jsonObject.remove(jsonKey);
			jsonObject.addProperty(jsonKey, value);
		}
	}

	public static class Combo extends Setting {

		private String value;
		private int index;
		private String jsonKey;
		private String[] choiceList;

		public Combo(String n, String k, String[] c) {
			super(n);
			jsonKey = k;
			index = jsonObject.get(k).getAsInt();
			value = c[index];
			choiceList = c;
			super.container = new SettingContainer(this);
		}

		public String getValue() {
			return value;
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
			jsonObject.addProperty(jsonKey, index);
		}
	}

	public static class Key extends Setting {

		private String value;
		private String jsonKey;

		public Key(String n, String k) {
			super(n);
			jsonKey = k;
			value = jsonObject.get(k).getAsString();
			super.container = new SettingContainer(this);
		}

		public String getValue() {
			return value;
		}

		public void setValue(String s) {
			value = s;
		}

		public void updateJson() {
			jsonObject.remove(jsonKey);
			jsonObject.addProperty(jsonKey, value);
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
