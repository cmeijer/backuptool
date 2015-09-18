package backuptool;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.google.inject.Inject;

public class Bookkeeper<T> {
	private List<FileAttributes> unFinishedJobs = new LinkedList<FileAttributes>();
	private Path persistencePath;
	private ObjectMapper objectMapper;

	@Inject
	public Bookkeeper(Path path, ObjectMapper objectMapper) {
		this.persistencePath = path;
		this.objectMapper = objectMapper;
		addPersistedJobs();
	}

	public void AddJobs(Collection<FileAttributes> newJobs) {
		unFinishedJobs.addAll(newJobs);
		persistJobs();
	}

	public FileAttributes getUnfinishedJob() {
		if (unFinishedJobs.isEmpty()) {
			throw new NoJobsException("No jobs left.");
		}

		return unFinishedJobs.get(0);
	}

	public void markJobAsFinished(FileAttributes finishedJob) {
		unFinishedJobs.remove(finishedJob);
		persistJobs();
	}

	private void persistJobs() {
		try {
			objectMapper.writeValue(persistencePath.toFile(), unFinishedJobs);
		} catch (IOException e) {
			throw new BackupToolException("Exception while persisting jobs.", e);
		}
	}

	private void addPersistedJobs() {
		List<FileAttributes> persistedJobs = new LinkedList<FileAttributes>();
		try {
			List<FileAttributes> readValue = objectMapper.readValue(persistencePath.toFile(),
					new TypeReference<LinkedList<FileAttributes>>() {
					});
			persistedJobs.addAll(readValue);
		} catch (IOException e) {
			e.printStackTrace();
			// no file, no persisted jobs to add
		}
		unFinishedJobs.addAll(persistedJobs);
	}
}
