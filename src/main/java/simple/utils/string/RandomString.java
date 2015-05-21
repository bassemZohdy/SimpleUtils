/**
 * 
 */
package simple.utils.string;

import java.util.Random;

/**
 * @author bassem
 *
 */
public class RandomString {

	public String nextAlpha(int length) {
		if (length < 1)
			throw new IllegalArgumentException("length < 1: " + length);
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		for (int i = 0; i < length; i++) {
			sb.append((char) (r.nextInt('z' - 'a') + 'a'));
		}
		return sb.toString();
	}

	public String nextAlphaNum(int length) {
		if (length < 1)
			throw new IllegalArgumentException("length < 1: " + length);
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		for (int i = 0; i < length; i++) {
			if (r.nextDouble() < 0.8)
				sb.append((char) (r.nextInt('z' - 'a') + 'a'));
			else
				sb.append((char) (r.nextInt('9' - '0') + '0'));
		}
		return sb.toString();
	}

	public String nextDelimitedLine(char delimiter, int wordsLength) {
		if (wordsLength < 1)
			throw new IllegalArgumentException("wordsLength < 1: "
					+ wordsLength);
		if ((delimiter > 'a' && delimiter < 'z')
				|| (delimiter > '0' && delimiter < '9'))
			throw new IllegalArgumentException("invalid delimiter : "
					+ delimiter);
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		for (int i = 0; i < wordsLength; i++) {
			sb.append(delimiter);
			sb.append(nextAlphaNum((r.nextInt(9 - 3) + 3)));
		}
		return sb.toString().substring(1);
	}

}
