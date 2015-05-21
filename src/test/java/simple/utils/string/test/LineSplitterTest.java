/**
 * 
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

/**
 * @author bassem
 *
 */
public class LineSplitterTest {

	private static final String DELIMITER = ",";
	private static final char DELIMITER_CHAR = ',';
	private static String sample = null;

	@BeforeClass
	public static void init() {
		Random r = new Random();
		RandomString rs = new RandomString();
		int words = r.nextInt(9 - 3) + 3;
		sample = rs.nextDelimitedLine(DELIMITER_CHAR, words);
	}

	@Test
	public void testLineSplitterByChar() {
		LineSplitterByChar splitter = new LineSplitterByChar(sample,
				DELIMITER_CHAR);
		List<String> strings = new ArrayList<String>();
		splitter.forEach(s -> strings.add(s));
		assertArrayEquals(sample.split(DELIMITER), strings.toArray());

	}

	@Test
	public void testLineSplitterBySubstring() {
		LineSplitterBySubstring splitter = new LineSplitterBySubstring(sample,
				DELIMITER_CHAR);
		List<String> strings = new ArrayList<String>();
		splitter.forEach(s -> strings.add(s));
		assertArrayEquals(sample.split(DELIMITER), strings.toArray());

	}

	@Test
	public void testLineSplitterBySubstringWithFuture() {
		LineSplitterBySubstringWithFuture splitter = new LineSplitterBySubstringWithFuture(
				sample, DELIMITER_CHAR);
		List<String> strings = new ArrayList<String>();
		splitter.forEach(s -> strings.add(s));
		assertArrayEquals(sample.split(DELIMITER), strings.toArray());

	}

}
