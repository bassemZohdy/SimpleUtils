/*
 * Project : SimpleUtils
 * Author : bassem.zohdy
 * Email : bassem.zohdy@gmail.com
 */
package simple.utils.string;

import java.util.Iterator;

// TODO: Auto-generated Javadoc
/**
 * The Class LineSplitterBySubstring.
 */
public class LineSplitterBySubstring implements Iterable<String> {
	
	/** The line. */
	private final String line;
	
	/** The delimiter. */
	private final char delimiter;
 
	/**
	 * Instantiates a new line splitter by substring.
	 *
	 * @param line
	 *            the line
	 * @param delimiter
	 *            the delimiter
	 */
	public LineSplitterBySubstring(String line, char delimiter) {
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
			private int to = 0;
 
			@Override
			public String next() {
				if (!(to == line.length()))
					to = line.indexOf(delimiter, index);
				String s = line.substring(index, to);
				index = to + 1;
				return s;
			}
 
			@Override
			public boolean hasNext() {
				boolean b = line.length() == to;
				if (to == line.lastIndexOf(delimiter))
					to = line.length();
				return !b;
			}
		};
	}
}
