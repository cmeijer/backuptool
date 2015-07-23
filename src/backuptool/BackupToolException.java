package backuptool;

import java.io.IOException;

public class BackupToolException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BackupToolException(IOException e) {
		super(e);
	}

}
