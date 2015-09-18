package backuptool;

public class NoJobsException extends BackupToolException {

	private static final long serialVersionUID = 1L;

	public NoJobsException(Throwable cause) {
		super(cause);
	}

	public NoJobsException(String message) {
		super(message);
	}

	public NoJobsException(String message, Throwable cause) {
		super(message, cause);
	}

}
