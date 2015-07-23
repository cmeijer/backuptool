package backuptool;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainClass {

	public static void main(String[] args) {
		Path path = Paths.get("/home/chris/development/runrunrun");
		new MainClass().writeFile(path);
	}

	public void writeFile(Path path) {
		try {
			Files.createFile(path);
		} catch (IOException e) {
			throw new BackupToolException(e);
		}
	}

}
