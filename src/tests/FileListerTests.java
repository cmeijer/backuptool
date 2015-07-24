package tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import backuptool.FileAttributes;
import backuptool.FileLister;

public class FileListerTests {
	private Path currentWorkingDirectory = getCurrentWorkingDirectory();
	private Path testDirectory = currentWorkingDirectory.resolve("resources/test");
	private Path emptyDirectory = testDirectory.resolve("emptyFolder");
	private Path directoryWithOneFile = testDirectory.resolve("folderWithOneFile");
	private FileLister fileLister;

	@Test
	public void getFiles_emptyDirectory_emptyList() {
		List<FileAttributes> files = fileLister.getFiles(emptyDirectory);
		assertEquals(0, files.size());
	}

	@Test
	public void getFiles_directoryWithOneFile_listSizeOne() {
		List<FileAttributes> files = fileLister.getFiles(directoryWithOneFile);
		assertEquals(1, files.size());
	}

	@Test
	public void getFiles_directoryWithOneFile_correctFileName() {
		List<FileAttributes> files = fileLister.getFiles(directoryWithOneFile);
		FileAttributes file = files.get(0);
		assertEquals("file", file.getRelativePath());
	}

	@Before
	public void setUp() {
		fileLister = new FileLister();
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
