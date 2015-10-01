package lobbyplugin.jens7841.main;

import org.bukkit.configuration.file.FileConfiguration;

public class PluginSettings {

	private static LobbyPluginConfigs confFile;
	private static LobbyPluginConfigs msgFile;

	public static void loadSettings() {
		confFile = new LobbyPluginConfigs("config.yml");
		confFile.saveDefaultConfig();

		new LobbyPluginConfigs("messages/", "messages-de.yml").saveDefaultConfig();

		msgFile = new LobbyPluginConfigs("messages/",
				"messages-" + getConfig().getString(ConfigPaths.LANGUAGE) + ".yml");
		msgFile.saveDefaultConfig();

		TeleporterItems.load();
		new TeleporterItem();

	}

	public static void reloadPlugin() {
		// TODO
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
