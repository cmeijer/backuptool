package backuptool;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class Bookkeeper<T> {
	private Jobs jobs = new Jobs();
	private Path persistencePath;
	private ObjectMapper objectMapper;

	@Inject
	public Bookkeeper(@Named("persistencePath") String persistencePath, ObjectMapper objectMapper) {
		this.persistencePath = Paths.get(persistencePath);
		this.objectMapper = objectMapper;
		loadPersistedJobs();
	}

	public void AddJobs(Collection<FileAttributes> newJobs) {
		HashSet<FileAttributes> finishedSet = new HashSet<FileAttributes>(jobs.finished);
		List<FileAttributes> newUnfinishedJobs = newJobs.stream().filter(j -> finishedSet.contains(j) == false)
				.collect(Collectors.toList());
		jobs.unFinished.addAll(newUnfinishedJobs);
		persistJobs();
	}

	public FileAttributes getUnfinishedJob() {
		if (jobs.unFinished.isEmpty()) {
			throw new NoJobsException("No jobs left.");
		}

		return jobs.unFinished.get(0);
	}

	public void markJobAsFinished(FileAttributes finishedJob) {
		jobs.unFinished.remove(finishedJob);
		jobs.finished.add(finishedJob);
		persistJobs();
	}

	private void persistJobs() {
		try {
			objectMapper.writeValue(persistencePath.toFile(), jobs);
		} catch (IOException e) {
			throw new BackupToolException("Exception while persisting jobs.", e);
		}
	}

	private void loadPersistedJobs() {
		try {
			jobs = objectMapper.readValue(persistencePath.toFile(), new TypeReference<Jobs>() {
			});
		} catch (IOException e) {
			// no file, no persisted jobs to add
			jobs = new Jobs();
		}
	}
}
