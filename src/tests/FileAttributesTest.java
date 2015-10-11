package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import backuptool.FileAttributes;

public class FileAttributesTest {
	@Test
	public void equals_sameObject_true() {
		String relativePath = "/test/bla/some/path";
		FileAttributes fileAttributes = new FileAttributes(relativePath);
		assertTrue(fileAttributes.equals(fileAttributes));
	}

	@Test
	public void equals_equivalentObject_true() {
		String relativePath = "/test/bla/some/path";
		FileAttributes fileAttributes1 = new FileAttributes(relativePath);
		FileAttributes fileAttributes2 = new FileAttributes(relativePath);
		assertTrue(fileAttributes1.equals(fileAttributes2));
	}

	@Test
	public void equals_otherFileAttributes_false() {
		String relativePath1 = "/test/bla/some/path";
		String relativePath2 = "/test/some/other/path";
		FileAttributes fileAttributes1 = new FileAttributes(relativePath1);
		FileAttributes fileAttributes2 = new FileAttributes(relativePath2);
		assertFalse(fileAttributes1.equals(fileAttributes2));
	}

	@Test
	public void equals_string_false() {
		String relativePath = "/test/bla/some/path";
		String string = "/test/some/other/path";
		FileAttributes fileAttributes1 = new FileAttributes(relativePath);
		assertFalse(fileAttributes1.equals(string));
	}

	@Test
	public void hashCodeEqual_equivalentObject_true() {
		String relativePath = "/test/bla/some/path";
		FileAttributes fileAttributes1 = new FileAttributes(relativePath);
		FileAttributes fileAttributes2 = new FileAttributes(relativePath);
		assertTrue(fileAttributes1.hashCode() == fileAttributes2.hashCode());
	}

	@Test
	public void hashCodeEqual_otherFileAttributes_false() {
		String relativePath1 = "/test/bla/some/path";
		String relativePath2 = "/test/some/other/path";
		FileAttributes fileAttributes1 = new FileAttributes(relativePath1);
		FileAttributes fileAttributes2 = new FileAttributes(relativePath2);
		assertFalse(fileAttributes1.hashCode() == fileAttributes2.hashCode());
	}

}
