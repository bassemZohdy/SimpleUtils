package simple.utils.string;

import java.util.Iterator;
import java.util.NoSuchElementException;
 
public class LineSplitterByChar implements Iterable<String> {
	private final String line;
	private final char delimiter;
 
	public LineSplitterByChar(String line, char delimiter) {
		this.line = line;
		this.delimiter = delimiter;
	}
 
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
