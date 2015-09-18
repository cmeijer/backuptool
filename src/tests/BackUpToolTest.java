package tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BackUpToolTest {

	private Path currentWorkingDirectory = getCurrentWorkingDirectory();
	protected Path testDirectory = currentWorkingDirectory.resolve("resources/test");

	public BackUpToolTest() {
		super();
	}

	private Path getCurrentWorkingDirectory() {
		String classPath;
		try {
			classPath = new File(".").getCanonicalPath();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return Paths.get(classPath);
	}

}