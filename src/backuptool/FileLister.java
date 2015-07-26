package backuptool;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.stream.Stream;

public class FileLister {

	public Stream<FileAttributes> getFiles(Path root) {
		try {
			final LinkedList<Path> paths = getPaths(root);
			return paths.stream().map(p -> getAttributes(p, root));
		} catch (IOException e) {
			throw new BackupToolException(e);
		}
	}

	private LinkedList<Path> getPaths(Path root) throws IOException {
		final LinkedList<Path> paths = new LinkedList<Path>();
		for (Path path : Files.newDirectoryStream(root)) {
			if (Files.isDirectory(path)) {
				paths.addAll(getPaths(path));
			} else {
				paths.add(path);
			}
		}
		return paths;
	}

	private FileAttributes getAttributes(Path path, Path root) {
		Path relative = root.relativize(path);
		return new FileAttributes(relative.toString());
	}

}