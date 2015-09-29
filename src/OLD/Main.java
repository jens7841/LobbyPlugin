package OLD;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements FesteVariablen{

	public static Main instance;
	
	public static ConfigAccessor confAcc;
	public static FileConfiguration conf;
	
	public static ConfigAccessor messAcc;
	public static FileConfiguration messages;

	
	@Override
	public void onEnable(){
		instance = this;
		
		confAcc = new ConfigAccessor(this, confName);
		confAcc.saveDefaultConfig();
		conf = confAcc.getConfig();
		
		messAcc = new ConfigAccessor(this, msgName);

		messAcc.saveDefaultConfig();
		messages = messAcc.getConfig();
		
		
		Bukkit.getPluginManager().registerEvents(new Eventlistener(), this);
		getCommand("lobbyplugin").setExecutor(new Commands());
		
		messageList.clear();
		for(String str : messages.getConfigurationSection("").getKeys(false)){
			messageList.put(str, ChatColor.translateAlternateColorCodes('&', messages.getString(str)));
		}
		aktiveWelten.clear();
		for(String str : conf.getStringList(pfadEnabledWorlds)){
			if(Bukkit.getWorld(str).getName() != null){
				aktiveWelten.add(Bukkit.getWorld(str));
			}
		}
	}
}
