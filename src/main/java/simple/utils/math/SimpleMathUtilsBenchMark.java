package simple.utils.math;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.math.util.MathUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @author bzohdy
 *
 */
public class SimpleMathUtilsBenchMark {

	/**
	 * @param args
	 * @throws RunnerException
	 */
	public static void main(String[] args) throws RunnerException {

		Options opt = new OptionsBuilder().warmupIterations(2)
				.measurementIterations(3).forks(2).build();

		new Runner(opt).run();

	}

	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	public void measureThroughputSimple() throws InterruptedException {
		Random r = new Random();
		int scale = r.nextInt(4 - 1) + 1;
		double value = r.nextDouble();
		SimpleMathUtils.round(value, scale);
	}

	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	public void measureThroughputCommons() throws InterruptedException {
		Random r = new Random();
		int scale = r.nextInt(4 - 1) + 1;
		double value = r.nextDouble();
		MathUtils.round(value, scale);
	}

}
