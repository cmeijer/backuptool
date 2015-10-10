package commands;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import backuptool.Bookkeeper;
import backuptool.FileAttributes;
import backuptool.FileCopier;
import backuptool.NoJobsException;

public class CopyUnitCommand {
	final private FileCopier fileCopier;
	final private Bookkeeper<FileAttributes> bookkeeper;
	private Path sourceRoot;
	private Path targetRoot;

	@Inject
	public CopyUnitCommand(@Named("sourceRoot") String sourceRoot, @Named("targetRoot") String targetRoot,
			FileCopier fileCopier, Bookkeeper<FileAttributes> bookkeeper) {
		this.sourceRoot = Paths.get(sourceRoot);
		this.targetRoot = Paths.get(targetRoot);
		this.fileCopier = fileCopier;
		this.bookkeeper = bookkeeper;
	}

	public void execute() {
		try {
			FileAttributes file = bookkeeper.getUnfinishedJob();
			fileCopier.copy(file, sourceRoot, targetRoot);
			bookkeeper.markJobAsFinished(file);
		} catch (NoJobsException e) {
			// Nothing to be done.
		}
	}
}
