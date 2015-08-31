package backuptool;

import java.nio.file.Files;
import java.nio.file.Path;

import com.google.inject.Inject;

public class FileSyncer {

	private FileCopier fileCopier;

	@Inject
	public FileSyncer(FileCopier fileCopier) {
		this.fileCopier = fileCopier;
	}

	public void sync(FileAttributes file, Path sourceDirectory, Path targetDirectory) {
		if (Files.exists(targetDirectory.resolve(file.getRelativePath()))) {
			return;
		}

		fileCopier.copy(file, sourceDirectory, targetDirectory);

	}
}
