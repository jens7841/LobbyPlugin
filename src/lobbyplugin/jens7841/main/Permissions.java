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
	public static final String TELEPORTER_MOVE_IN_INVENTORY = TELEPORTER_PREFIX + "canmoveitemininventory";
	public static final String TELEPORTER_CAN_DROP = TELEPORTER_PREFIX + "";

	/*
	 * Teleporter Commands
	 */

	public static final String COMMAND_TELEPORTER_SETNAME = TELEPORTER_PREFIX + "setname";
	public static final String COMMAND_TELEPORTER_CHANGEITEM = TELEPORTER_PREFIX + "setitem";
	public static final String COMMAND_TELEPORTER_ENABLED = TELEPORTER_PREFIX + "setenabled";

	/*
	 * Hide Players Tool
	 */
	public static final String HIDE_PLAYERS_TOOL_PREFIX = PREFIX + "hideplayerstool.";
	public static final String HIDE_PLAYERS_TOOL_USE = HIDE_PLAYERS_TOOL_PREFIX + "use";
	public static final String HIDE_PLAYERS_TOOL_MOVE_ITEM_IN_INVENTORY = HIDE_PLAYERS_TOOL_PREFIX
			+ "canmoveitemsininventory";
	public static final String HIDE_PLAYERS_TOOL_CAN_DROP = HIDE_PLAYERS_TOOL_PREFIX + "candrop";

	/*
	 * Other Commands
	 */

	public static final String COMMAND_RELOAD = PREFIX + "reload";

	/*
	 * Events
	 */
	public static final String EVENT_CAN_MOVE_ITEMS_IN_INVENTORY = PREFIX + "canmoveitemsininventory";
	public static final String EVENT_CAN_DROP_ITEMS = PREFIX + "candropitems";
	public static final String EVENT_DROP_ITEMS_ON_DEATH = PREFIX + "dropitemsondeath";
	public static final String EVENT_CAN_PICKUP_ITEMS = PREFIX + "canpickupitems";
	public static final String EVENT_CAN_GET_DAMAGE = PREFIX + "cangetdamage";
	public static final String EVENT_CAN_GET_HUNGER = PREFIX + "cangethunger";

}