package lobbyplugin.jens7841.main;

import org.bukkit.ChatColor;

public class Msg {

	public static String s(String str, Object... objects) {
		String nachricht = PluginSettings.getMessages().getString(str);
		if (nachricht == null) {
			return ChatColor.RED + "LobbyPlugin ERROR: MESSAGE NOT FOUND";
		} else {

			nachricht = ChatColor.translateAlternateColorCodes('&', nachricht);
			if (objects.length == 0)
				return nachricht;
			int i = 0;
			for (Object obj : objects) {
				nachricht = nachricht.replace("{" + i + "}", obj.toString());
				i++;
			}
		}

		return nachricht;
	}

}
