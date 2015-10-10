package commands;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import backuptool.Bookkeeper;
import backuptool.FileAttributes;
import backuptool.FileLister;

public class UpdateListCommand implements Command {
	final private FileLister fileLister;
	final private Bookkeeper<FileAttributes> bookkeeper;
	private Path root;

	@Inject
	public UpdateListCommand(@Named("sourceRoot") String sourceRoot, FileLister fileLister,
			Bookkeeper<FileAttributes> bookkeeper) {
		root = Paths.get(sourceRoot);
		this.fileLister = fileLister;
		this.bookkeeper = bookkeeper;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see commands.Command#execute()
	 */
	@Override
	public void execute() {
		System.out.println("Backup tool: Updating file list.");
		Stream<FileAttributes> files = fileLister.getFiles(root);
		bookkeeper.AddJobs(files.collect(Collectors.toList()));
		System.out.println("Backup tool: Finished updating file list.");
	}
}
