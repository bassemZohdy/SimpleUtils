/*
 * Project : SimpleUtils
 * Author : bassem.zohdy
 * Email : bassem.zohdy@gmail.com
 */
package simple.utils.string;

import java.util.Iterator;
import java.util.NoSuchElementException;
 
// TODO: Auto-generated Javadoc
/**
 * The Class LineSplitterByChar.
 */
public class LineSplitterByChar implements Iterable<String> {
	
	/** The line. */
	private final String line;
	
	/** The delimiter. */
	private final char delimiter;
 
	/**
	 * Instantiates a new line splitter by char.
	 *
	 * @param line
	 *            the line
	 * @param delimiter
	 *            the delimiter
	 */
	public LineSplitterByChar(String line, char delimiter) {
		this.line = line;
		this.delimiter = delimiter;
	}
 
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<String> iterator() {
		return new Iterator<String>() {
			private int index = 0;
			private final StringBuilder sb = new StringBuilder();
 
			@Override
			public String next() {
				if (!hasNext())
					throw new NoSuchElementException();
				sb.setLength(0);
				while (index < line.length() && line.charAt(index) != delimiter) {
					sb.append(line.charAt(index));
					index++;
				}
				index++;
				return sb.toString();
			}
 
			@Override
			public boolean hasNext() {
				return (index < line.length());
			}
		};
	}
}
