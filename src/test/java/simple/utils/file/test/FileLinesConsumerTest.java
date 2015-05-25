/*
 * Project : SimpleUtils
 * Author : bassem.zohdy
 * Email : bassem.zohdy@gmail.com
 */
package simple.utils.file.test;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;

import org.junit.Test;

import simple.utils.file.FileLinesConsumer;

// TODO: Auto-generated Javadoc
/**
 * The Class FileLinesConsumerTest.
 */
public class FileLinesConsumerTest {
	
	/** The Constant DATA. */
	private final static String DATA = "#LINE1#LINE2";

	/**
	 * Test read file.
	 */
	@Test
	public void testReadFile() {
		final StringBuilder sb = new StringBuilder();
		FileLinesConsumer<String> fileLinesConsumer = FileLinesConsumer.of(
				(s) -> s.contains("#"), // Filter
				(s) -> s.toUpperCase(), // Converter
				(s) -> sb.append(s)); // Consumer
		fileLinesConsumer.load(Paths.get("src/test/resources/test.txt")
				.toFile());
		assertEquals(DATA, sb.toString());
		fileLinesConsumer.load(Paths.get("src/test/resources/test.txt")
				.toFile());
		assertEquals(DATA + DATA, sb.toString());
	}

	/**
	 * Test read file gz.
	 */
	@Test
	public void testReadFileGZ() {
		final StringBuilder sb = new StringBuilder();
		FileLinesConsumer<String> fileLinesConsumer = FileLinesConsumer.of(
				(s) -> s.contains("#"), (s) -> s.toUpperCase(),
				(s) -> sb.append(s));
		fileLinesConsumer.loadGZ(Paths.get("src/test/resources/test.txt.gz")
				.toFile());
		assertEquals(DATA, sb.toString());
	}

}
