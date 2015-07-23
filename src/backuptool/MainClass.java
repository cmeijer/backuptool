package backuptool;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MainClass {

	public static void main(String[] args) {

	}

	public void writeFile(Path path) {
		try {
			Files.createFile(path);
		} catch (IOException e) {
			throw new BackupToolException(e);
		}
	}

}
