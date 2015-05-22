/*
 * Project : SimpleUtils
 * Author : bassem.zohdy
 * Email : bassem.zohdy@gmail.com
 */
package simple.utils.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

// TODO: Auto-generated Javadoc
/**
 * The Class SplitterBenchMark.
 */
@State(Scope.Benchmark)
public class SplitterBenchMark {

	/** The Constant DELIMITER. */
	private static final String DELIMITER = ",";
	
	/** The Constant DELIMITER_CHAR. */
	private static final char DELIMITER_CHAR = ',';
	
	/** The samples. */
	private final List<String> samples;

	/**
	 * Instantiates a new splitter bench mark.
	 */
	public SplitterBenchMark() {
		// generate data
		samples = new ArrayList<String>();
		Random r = new Random();
		RandomString rs = new RandomString();
		for (int i = 0; i < 30; i++) {
			int words = r.nextInt(9 - 6) + 6;
			String sample = rs.nextDelimitedLine(DELIMITER_CHAR, words);
			samples.add(sample);
		}
	}

	/**
	 * Measure average time split.
	 *
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	public void measureAverageTimeSplit() throws InterruptedException {
		samples.forEach(sample -> {
			String[] results = sample.split(DELIMITER);
			for (String s : results) {
				int i = s.length(); // dummy operation to be sure it is getting
									// data.
			}
		});
	}

	/**
	 * Measure average time line splitter by char.
	 *
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	public void measureAverageTimeLineSplitterByChar()
			throws InterruptedException {
		samples.forEach(sample -> {
			LineSplitterByChar splitter = new LineSplitterByChar(sample,
					DELIMITER_CHAR);
			splitter.forEach(s -> {
				int i = s.length(); // dummy operation to be sure it is getting
				// data.
			});
		});
	}

	/**
	 * Measure average time splitter by sub string.
	 *
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	public void measureAverageTimeSplitterBySubString()
			throws InterruptedException {
		samples.forEach(sample -> {
			LineSplitterBySubstring splitter = new LineSplitterBySubstring(
					sample, DELIMITER_CHAR);
			splitter.forEach(s -> {
				int i = s.length(); // dummy operation to be sure it is getting
				// data.
			});
		});
	}

	/**
	 * Measure average time splitter by sub string with future.
	 *
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	public void measureAverageTimeSplitterBySubStringWithFuture()
			throws InterruptedException {
		samples.forEach(sample -> {
			LineSplitterBySubstringWithFuture splitter = new LineSplitterBySubstringWithFuture(
					sample, DELIMITER_CHAR);
			splitter.forEach(s -> {
				int i = s.length(); // dummy operation to be sure it is getting
				// data.
			});
		});
	}

}
