package commands;

import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.inject.Inject;

import backuptool.Bookkeeper;
import backuptool.FileAttributes;
import backuptool.FileLister;

public class UpdateListCommand {
	final private FileLister fileLister;
	final private Bookkeeper<FileAttributes> bookkeeper;
	private Path root;

	@Inject
	public UpdateListCommand(FileLister fileLister, Bookkeeper<FileAttributes> bookkeeper) {
		this.fileLister = fileLister;
		this.bookkeeper = bookkeeper;
	}

	public void execute() {
		Stream<FileAttributes> files = fileLister.getFiles(root);
		bookkeeper.AddJobs(files.collect(Collectors.toList()));
	}
}
