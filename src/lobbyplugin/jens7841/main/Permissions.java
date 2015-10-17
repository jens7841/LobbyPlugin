package lobbyplugin.jens7841.main;

public class Permissions {

	private Permissions() {
	}

	public static final String PREFIX = "lobbyplugin.";

	/*
	 * Teleporter
	 */
	public static final String TELEPORTER_PREFIX = PREFIX + "teleporter.";
	public static final String TELEPORTER_USE = TELEPORTER_PREFIX + "use";

	/*
	 * Teleporter Commands
	 */

	public static final String COMMAND_TELEPORTER_SETNAME = TELEPORTER_PREFIX + "setname";
	public static final String COMMAND_TELEPORTER_CHANGEITEM = TELEPORTER_PREFIX + "setitem";
	public static final String COMMAND_RELOAD = PREFIX + "reload";

	/*
	 * Hide Players Tool
	 */
	public static final String HIDE_PLAYERS_TOOL_PREFIX = PREFIX + "hideplayerstool.";
	public static final String HIDE_PLAYERS_TOOL_USE = HIDE_PLAYERS_TOOL_PREFIX + "use";

}