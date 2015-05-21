package simple.utils.string;

import java.util.Iterator;

public class LineSplitterBySubstring implements Iterable<String> {
	private final String line;
	private final char delimiter;
 
	public LineSplitterBySubstring(String line, char delimiter) {
		this.line = line;
		this.delimiter = delimiter;
	}
 
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
