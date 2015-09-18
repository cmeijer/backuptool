package backuptool;

public class FileAttributes {

	private String relativePath;

	// For json deserialization only
	private FileAttributes() {
	}

	public FileAttributes(String relativePath) {
		this.relativePath = relativePath;
	}

	// For json deserialization only
	private void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public String getRelativePath() {
		return relativePath;
	}

}
