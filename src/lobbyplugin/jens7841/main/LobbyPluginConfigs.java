package lobbyplugin.jens7841.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class LobbyPluginConfigs {

	private final JavaPlugin plugin = LobbyPlugin.instance;

	private File configFile;
	private FileConfiguration conf;

	public LobbyPluginConfigs(String pfad, String fileName) {

		if (pfad.isEmpty()) {
			this.configFile = new File(plugin.getDataFolder(), fileName);
		} else {
			this.configFile = new File(plugin.getDataFolder() + "/" + pfad, fileName);
		}

	}

	public LobbyPluginConfigs(String fileName) {
		this("", fileName);
	}

	public FileConfiguration getConfig() {
		if (conf == null)
			reloadConfig();
		return conf;
	}

	public void reloadConfig() {
		conf = YamlConfiguration.loadConfiguration(configFile);

		InputStream defaultStream = plugin.getResource(configFile.getName());

		if (defaultStream != null) {
			@SuppressWarnings("deprecation")
			YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(defaultStream);
			conf.setDefaults(defaultConfig);
		}
	}

	@SuppressWarnings("deprecation")
	public void saveDefaultConfig() {
		if (!configFile.exists()) {
			InputStream s = plugin.getResource(configFile.getName());

			if (s != null) {
				try {
					YamlConfiguration.loadConfiguration(s).save(configFile);
				} catch (IOException e) {
				}
			}

		}
	}

	public void saveConfig() {
		if (conf == null || configFile == null) {
			return;
		} else {
			try {
				getConfig().save(configFile);
			} catch (IOException ex) {
				plugin.getLogger().log(Level.SEVERE, "Could not save config to " + configFile, ex);
			}
		}
	}

}
