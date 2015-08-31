package backuptool;

import java.nio.file.Path;

public interface FileCopier {

	public void copy(FileAttributes file, Path sourceDirectory, Path targetDirectory);

}