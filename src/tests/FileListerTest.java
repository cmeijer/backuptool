package tests;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import backuptool.FileAttributes;
import backuptool.FileLister;

public class FileListerTest extends BackUpToolTest {
	private Path emptyDirectory = testDirectory.resolve("emptyFolder");
	private Path directoryWithOneFile = testDirectory.resolve("folderWithOneFile");
	private Path directoryWithSubs = testDirectory.resolve("folderWithSubs");
	private Path directoryWithSub = testDirectory.resolve("folderWithSub");
	private FileLister fileLister;

	@Test
	public void getFiles_emptyDirectory_emptyList() {
		Stream<FileAttributes> files = fileLister.getFiles(emptyDirectory);
		assertEquals(0, files.count());
	}

	@Test
	public void getFiles_directoryWithOneFile_listSizeOne() {
		Stream<FileAttributes> files = fileLister.getFiles(directoryWithOneFile);
		assertEquals(1, files.count());
	}

	@Test
	public void getFiles_directoryWithOneFile_correctPath() {
		Stream<FileAttributes> files = fileLister.getFiles(directoryWithOneFile);
		FileAttributes file = files.findFirst().get();
		assertEquals("file", file.getRelativePath());
	}

	@Test
	public void getFiles_directoryWithSubsAnd4Files_4files() {
		Stream<FileAttributes> files = fileLister.getFiles(directoryWithSubs);
		assertEquals(4, files.count());
	}

	@Test
	public void getFiles_fileInSub_correctPath() {
		Stream<FileAttributes> files = fileLister.getFiles(directoryWithSub);
		FileAttributes file = files.findFirst().get();
		assertEquals("sub/file", file.getRelativePath());
	}

	@Before
	public void setUp() {
		fileLister = new FileLister();
	}
}
