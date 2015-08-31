package tests;

import static org.junit.Assert.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import backuptool.MainClass;

public class MainClassTests {
	@Test
	public void sometest() {
		Path path = Paths.get("/home/chris/development/testje");
		new MainClass().writeFile(path);
		assertTrue(Files.exists(path));
	}
}
