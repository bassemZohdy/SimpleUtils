/*
 * Project : SimpleUtils
 * Author : bassem.zohdy
 * Email : bassem.zohdy@gmail.com
 */
package simple.utils.string.test;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import simple.utils.string.LineSplitterByChar;
import simple.utils.string.LineSplitterBySubstring;
import simple.utils.string.LineSplitterBySubstringWithFuture;
import simple.utils.string.RandomString;

// TODO: Auto-generated Javadoc
/**
 * The Class LineSplitterTest.
 */
public class LineSplitterTest {

	/** The Constant DELIMITER. */
	private static final String DELIMITER = ",";
	
	/** The Constant DELIMITER_CHAR. */
	private static final char DELIMITER_CHAR = ',';
	
	/** The sample. */
	private static String sample = null;

	/**
	 * Inits the.
	 */
	@BeforeClass
	public static void init() {
		Random r = new Random();
		RandomString rs = new RandomString();
		int words = r.nextInt(9 - 3) + 3;
		sample = rs.nextDelimitedLine(DELIMITER_CHAR, words);
	}

	/**
	 * Test line splitter by char.
	 */
	@Test
	public void testLineSplitterByChar() {
		LineSplitterByChar splitter = new LineSplitterByChar(sample,
				DELIMITER_CHAR);
		List<String> strings = new ArrayList<String>();
		splitter.forEach(s -> strings.add(s));
		assertArrayEquals(sample.split(DELIMITER), strings.toArray());

	}

	/**
	 * Test line splitter by substring.
	 */
	@Test
	public void testLineSplitterBySubstring() {
		LineSplitterBySubstring splitter = new LineSplitterBySubstring(sample,
				DELIMITER_CHAR);
		List<String> strings = new ArrayList<String>();
		splitter.forEach(s -> strings.add(s));
		assertArrayEquals(sample.split(DELIMITER), strings.toArray());

	}

	/**
	 * Test line splitter by substring with future.
	 */
	@Test
	public void testLineSplitterBySubstringWithFuture() {
		LineSplitterBySubstringWithFuture splitter = new LineSplitterBySubstringWithFuture(
				sample, DELIMITER_CHAR);
		List<String> strings = new ArrayList<String>();
		splitter.forEach(s -> strings.add(s));
		assertArrayEquals(sample.split(DELIMITER), strings.toArray());

	}

}
