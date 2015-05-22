/*
 * Project : SimpleUtils
 * Author : bassem.zohdy
 * Email : bassem.zohdy@gmail.com
 */
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

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleMathUtilsBenchMark.
 */
public class SimpleMathUtilsBenchMark {

	/**
	 * Measure average time simple.
	 *
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	public void measureAverageTimeSimple() throws InterruptedException {
		Random r = new Random();
		int scale = r.nextInt(4 - 1) + 1;
		double value = r.nextDouble();
		SimpleMathUtils.round(value, scale);
	}

	/**
	 * Measure average time commons.
	 *
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	public void measureAverageTimeCommons() throws InterruptedException {
		Random r = new Random();
		int scale = r.nextInt(4 - 1) + 1;
		double value = r.nextDouble();
		MathUtils.round(value, scale);
	}

}
