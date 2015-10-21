package simple.utils.string;

import java.util.Iterator;
import java.util.function.BiFunction;

/**
 * Created by bzohdy on 10/21/15.
 */
public class LineSplitterBiFunction implements BiFunction<String, Character, Iterator<String>> {

    @Override
    public Iterator<String> apply(String s, Character d) {
        return new Iterator<String>() {
            private final int length = s.length();
            private int index = 0;
            private int to = s.indexOf(d);

            @Override
            public boolean hasNext() {
                if (index > to && to != length) {
                    to = s.indexOf(d, index);
                    to = to > 0 ? to : length;
                }
                return index < length;
            }

            @Override
            public String next() {
                if (to == -1) {
                    to = length;
                    index = length;
                    return s;
                }
                final String substring = s.substring(index, to);
                index = to + 1;
                hasNext();
                return substring;
            }
        };
    }
}
