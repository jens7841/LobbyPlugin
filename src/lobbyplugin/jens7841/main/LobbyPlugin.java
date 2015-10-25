package lobbyplugin.jens7841.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import commands.LobbyPluginCommand;

public class LobbyPlugin extends JavaPlugin {

	public static JavaPlugin instance;

	@Override
	public void onEnable() {
		instance = this;
		new PluginSettings();
		getCommand("lobbyplugin").setExecutor(new LobbyPluginCommand());
		Bukkit.getPluginManager().registerEvents(new EventListener(), this);

		PluginSettings.loadSettings();

		for (Player pe : Bukkit.getOnlinePlayers()) {
			TeleporterItem.giveItem(pe);
			HideItem.giveItem(pe);
		}

	}

}
