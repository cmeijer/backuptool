package backuptool;

public class BackupToolException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BackupToolException(Throwable cause) {
		super(cause);
	}

	public BackupToolException(String message) {
		super(message);
	}

	public BackupToolException(String message, Throwable cause) {
		super(message, cause);
	}

}
