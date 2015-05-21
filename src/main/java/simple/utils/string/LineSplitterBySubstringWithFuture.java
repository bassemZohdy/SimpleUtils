package simple.utils.string;

import java.util.Iterator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class LineSplitterBySubstringWithFuture implements Iterable<String> {
	private final String line;
	private final char delimiter;

	public LineSplitterBySubstringWithFuture(String line, char delimiter) {
		this.line = line;
		this.delimiter = delimiter;
	}

	@Override
	public Iterator<String> iterator() {
		return new Iterator<String>() {
			private int index = 0;
			private int to = 0;
			private Future<String> futureValue;

			@Override
			public String next() {
				if (this.futureValue == null) {
					return get();
				}
				String value = null;
				try {
					value = this.futureValue.get();
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
				this.futureValue = CompletableFuture.supplyAsync(this::get);
				return value;
			}

			private String get() {
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
