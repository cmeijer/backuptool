package backuptool;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class IoFileCopier implements FileCopier {

	@Override
	public void copy(FileAttributes file, Path sourceDirectory, Path targetDirectory) {
		try {
			Path sourcePath = sourceDirectory.resolve(file.getRelativePath());
			Path targetPath = targetDirectory.resolve(file.getRelativePath());
			Files.copy(sourcePath, targetPath);
		} catch (IOException e) {
			throw new BackupToolException(e);
		}
	}
}
