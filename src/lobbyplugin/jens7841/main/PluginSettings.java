package lobbyplugin.jens7841.main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class PluginSettings {

	private static LobbyPluginConfigs confFile;
	private static LobbyPluginConfigs msgFile;

	public static ArrayList<String> availableLanguages = new ArrayList<>();

	static {
		availableLanguages.add("en");
		availableLanguages.add("de");
	}

	public static void loadSettings() {
		confFile = new LobbyPluginConfigs("config.yml");
		confFile.saveDefaultConfig();

		new LobbyPluginConfigs("messages/", "messages-de.yml").saveDefaultConfig();

		String lang = getConfig().getString(ConfigPaths.LANGUAGE).toLowerCase();

		if (availableLanguages.contains(lang)) {
			msgFile = new LobbyPluginConfigs("messages/", "messages-" + lang + ".yml");
		} else {
			msgFile = new LobbyPluginConfigs("messages/", "messages-en.yml");
		}

		msgFile.saveDefaultConfig();

		try {
			TeleporterItems.load();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		try {
			TeleporterItem.load();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		try {
			HideItem.load();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	public static void reloadPlugin() {
		Bukkit.getPluginManager().disablePlugin(LobbyPlugin.instance);
		Bukkit.getPluginManager().enablePlugin(LobbyPlugin.instance);
	}

	public static FileConfiguration getConfig() {
		return confFile.getConfig();
	}

	public static void saveConfig() {
		confFile.saveConfig();
	}

	public static FileConfiguration getMessages() {
		return msgFile.getConfig();
	}

	public static void saveMessages() {
		msgFile.saveConfig();
	}

}
