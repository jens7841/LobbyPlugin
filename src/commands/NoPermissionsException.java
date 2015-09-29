package commands;

public class NoPermissionsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6711843478395321344L;

	public NoPermissionsException() {
		super();
	}

	public NoPermissionsException(String msg) {
		super(msg);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
