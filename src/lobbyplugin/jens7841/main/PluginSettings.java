package lobbyplugin.jens7841.main;

import org.bukkit.configuration.file.FileConfiguration;

public class PluginSettings {

	private static PluginSettings instance;

	private LobbyPluginConfigs confFile;
	private LobbyPluginConfigs msgFile;

	public PluginSettings() {
		instance = this;
		loadSettings();
	}

	public void loadSettings() {
		this.confFile = new LobbyPluginConfigs("config.yml");
		confFile.saveDefaultConfig();

		new LobbyPluginConfigs("messages/", "messages-de.yml").saveDefaultConfig();

		this.msgFile = new LobbyPluginConfigs("messages/",
				"messages-" + getConfig().getString(ConfigPaths.LANGUAGE) + ".yml");
		msgFile.saveDefaultConfig();

		TeleporterItems.load();
		new TeleporterItem();

	}

	public FileConfiguration getConfig() {
		return confFile.getConfig();
	}

	public FileConfiguration getMessages() {
		return msgFile.getConfig();
	}

	public static PluginSettings getInstance() {
		if (instance == null) {
			instance = new PluginSettings();
		}
		return instance;
	}
}
