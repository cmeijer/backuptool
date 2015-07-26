package tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import backuptool.FileAttributes;
import backuptool.FileCopier;

public class FileCopierTests extends BackUpToolTests {

	private Path sourceDirectory = testDirectory.resolve("copyFolder/source");
	private Path targetDirectory = testDirectory.resolve("copyFolder/target");
	private FileCopier fileCopier;

	@Test
	public void copy_emptyFile_fileCreated() {
		FileAttributes file = new FileAttributes("file.jpg");
		fileCopier.copy(file, sourceDirectory, targetDirectory);
		assertTrue(Files.exists(targetDirectory.resolve(file.getRelativePath())));
	}

	@Before
	public void setUp() throws IOException {
		Files.createDirectory(targetDirectory);
		fileCopier = new FileCopier();
	}

	@After
	public void cleanUp() throws IOException {
		Files.delete(targetDirectory);
	}

}
