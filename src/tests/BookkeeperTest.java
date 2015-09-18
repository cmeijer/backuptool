package tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import backuptool.Bookkeeper;
import backuptool.FileAttributes;
import backuptool.NoJobsException;

public class BookkeeperTest extends BackUpToolTest {

	private Path persistencePath = testDirectory.resolve("bookkeeperFolder/jobs");
	private Bookkeeper<FileAttributes> bookkeeper;

	@Test(expected = NoJobsException.class)
	public void getUnfinishedJob_nothingAdded_throw() {
		bookkeeper.getUnfinishedJob();
	}

	@Test(expected = NoJobsException.class)
	public void getUnfinishedJob_emptyListAdded_throw() {
		bookkeeper.AddJobs(Arrays.asList(new FileAttributes[] {}));
		bookkeeper.getUnfinishedJob();
	}

	@Test
	public void getUnfinishedJob_jobAdded_returnJob() {
		String relativePath = "3dc13ac6";
		FileAttributes job = new FileAttributes(relativePath);
		bookkeeper.AddJobs(Arrays.asList(new FileAttributes[] { job }));

		FileAttributes unfinishedJob = bookkeeper.getUnfinishedJob();

		assertEquals(relativePath, unfinishedJob.getRelativePath());
	}

	@Test(expected = NoJobsException.class)
	public void getUnfinishedJob_jobAddedAndMarkedAsFinished_throw() {
		String relativePath = "3dc13ac6";
		FileAttributes job = new FileAttributes(relativePath);
		bookkeeper.AddJobs(Arrays.asList(new FileAttributes[] { job }));
		bookkeeper.markJobAsFinished(job);

		FileAttributes unfinishedJob = bookkeeper.getUnfinishedJob();

		assertEquals(relativePath, unfinishedJob.getRelativePath());
	}

	@Test
	public void getUnfinishedJob_jobAddedAndNewBookkeeper_returnJob() {
		String relativePath = "3dc13ac6";
		FileAttributes job = new FileAttributes(relativePath);
		bookkeeper.AddJobs(Arrays.asList(new FileAttributes[] { job }));

		FileAttributes unfinishedJob = getNewBookkeeper().getUnfinishedJob();

		assertEquals(relativePath, unfinishedJob.getRelativePath());
	}

	@Before
	public void setUp() throws IOException {
		bookkeeper = getNewBookkeeper();
	}

	@After
	public void cleanUp() throws IOException {
		Files.deleteIfExists(persistencePath);
	}

	private Bookkeeper<FileAttributes> getNewBookkeeper() {
		return new Bookkeeper<FileAttributes>(persistencePath, new ObjectMapper());
	}

}
