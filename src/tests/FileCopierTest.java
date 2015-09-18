package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import backuptool.FileAttributes;
import backuptool.IoFileCopier;

public class FileCopierTest extends BackUpToolTest {

	private Path sourceDirectory = testDirectory.resolve("copyFolder/source");
	private Path targetDirectory = testDirectory.resolve("copyFolder/target");
	private String fileName = "file.jpg";
	private Path expectedTargetFilePath = targetDirectory.resolve(fileName);
	private Path sourceFilePath = sourceDirectory.resolve(fileName);
	private IoFileCopier fileCopier;

	@Test
	public void copy_file_fileCreated() {
		FileAttributes file = new FileAttributes(fileName);

		// Act
		fileCopier.copy(file, sourceDirectory, targetDirectory);

		// Assert
		assertTrue(Files.exists(expectedTargetFilePath));
	}

	@Test
	public void copy_file_sameSize() throws IOException {
		FileAttributes file = new FileAttributes(fileName);

		// Act
		fileCopier.copy(file, sourceDirectory, targetDirectory);

		// Assert
		assertEquals(Files.size(sourceFilePath), Files.size(expectedTargetFilePath));
	}

	@Test
	public void copy_file_sameContent() throws IOException {
		FileAttributes file = new FileAttributes(fileName);

		// Act
		fileCopier.copy(file, sourceDirectory, targetDirectory);

		// Assert
		byte[] sourceBytes = Files.readAllBytes(sourceFilePath);
		byte[] targetBytes = Files.readAllBytes(expectedTargetFilePath);
		for (int i = 0; i < sourceBytes.length; i++) {
			assertEquals(sourceBytes[i], targetBytes[i]);
		}
	}

	@Before
	public void setUp() throws IOException {
		Files.createDirectory(targetDirectory);
		fileCopier = new IoFileCopier();
	}

	@After
	public void cleanUp() throws IOException {
		Files.delete(expectedTargetFilePath);
		Files.delete(targetDirectory);
	}

}
