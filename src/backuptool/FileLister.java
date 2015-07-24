package backuptool;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class FileLister {

	public List<FileAttributes> getFiles(Path root) {
		try {
			final LinkedList<FileAttributes> files = new LinkedList<FileAttributes>();
			Files.newDirectoryStream(root).forEach(p -> files.add(getAttributes(p, root)));
			return files;
		} catch (IOException e) {
			throw new BackupToolException(e);
		}
	}

	private FileAttributes getAttributes(Path path, Path root) {
		Path relative = root.relativize(path);
		return new FileAttributes(relative.toString());
	}

}