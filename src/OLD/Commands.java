package OLD;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor,FesteVariablen {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg,	String[] args) {
		if(cmd.getName().equalsIgnoreCase("lobbyplugin")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(p.hasPermission(perm_CommandUse)){
					
					if(args.length >= 1){
						if(args[0].equalsIgnoreCase("reload")){
							Bukkit.getPluginManager().disablePlugin(Main.instance);
							Bukkit.getPluginManager().enablePlugin(Main.instance);
							p.sendMessage(messageList.get(msg_rlComplete));
							return true;
						}
						
					}
					
					p.sendMessage("HELP");
				}else
					p.sendMessage(messageList.get("noPermissions"));
			}else{
				
			}
			return true;
		}
		
		return false;
	}

}
