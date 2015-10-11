package backuptool;

import java.util.LinkedList;
import java.util.List;

public class Jobs {
	public List<FileAttributes> unFinished = new LinkedList<FileAttributes>();
	public List<FileAttributes> finished = new LinkedList<FileAttributes>();
}