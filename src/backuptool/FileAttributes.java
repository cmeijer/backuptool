package backuptool;

public class FileAttributes {

	private String relativePath;

	// For json deserialization only
	@SuppressWarnings("unused")
	private FileAttributes() {
	}

	public FileAttributes(String relativePath) {
		this.relativePath = relativePath;
	}

	// For json deserialization only
	@SuppressWarnings("unused")
	private void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public String getRelativePath() {
		return relativePath;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof FileAttributes == false) {
			return false;
		}

		FileAttributes otherFileAttributes = (FileAttributes) other;

		return getRelativePath().equals(otherFileAttributes.getRelativePath());
	}

	@Override
	public int hashCode() {
		return relativePath.hashCode();
	}
}
