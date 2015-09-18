package tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import backuptool.FileAttributes;
import backuptool.FileCopier;
import backuptool.FileSyncer;

public class FileSyncerTest extends BackUpToolTest {

	private Path sourceDirectory = testDirectory.resolve("syncFolder/source");
	private Path targetDirectory = testDirectory.resolve("syncFolder/target");
	private String notYetSyncedFileName = "notyetsynced.jpg";
	private String alreadySyncedFileName = "alreadysynced.jpg";
	private Path expectedTargetFilePath = targetDirectory.resolve(notYetSyncedFileName);
	private Path sourceFilePath = sourceDirectory.resolve(notYetSyncedFileName);
	private FileSyncer fileSyncer;
	private FileCopier fileCopier;

	@Test
	public void sync_noTarget_copyWasCalled() {
		FileAttributes file = new FileAttributes(notYetSyncedFileName);

		// Act
		fileSyncer.sync(file, sourceDirectory, targetDirectory);

		// Assert
		verify(fileCopier).copy(file, sourceDirectory, targetDirectory);
	}

	@Test
	public void sync_alreadySyncedTarget_copyWasNotCalled() {
		FileAttributes file = new FileAttributes(alreadySyncedFileName);

		// Act
		fileSyncer.sync(file, sourceDirectory, targetDirectory);

		// Assert
		verifyZeroInteractions(fileCopier);
	}

	@Before
	public void setUp() throws IOException {
		fileCopier = mock(FileCopier.class);
		fileSyncer = new FileSyncer(fileCopier);
	}

	@After
	public void cleanUp() throws IOException {
	}

}
