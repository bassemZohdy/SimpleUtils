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

/**
 * @author bzohdy
 *
 */
@State(Scope.Benchmark)
public class SplitterBenchMark {

	/**
	 * 
	 */
	private static final String DELIMITER = ",";
	private static final char DELIMITER_CHAR = ',';
	private final List<String> samples;

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
