/*
 * Project : SimpleUtils
 * Author : bassem.zohdy
 * Email : bassem.zohdy@gmail.com
 */
package simple.utils.benchmark;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class BenchMarkConfig.
 */
public class BenchMarkConfig {
	
	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws RunnerException
	 *             the runner exception
	 */
	public static void main(String[] args) throws RunnerException {

		Options opt = new OptionsBuilder()
				.include("simple.utils.string.SplitterBenchMark.*")
				.warmupIterations(1).measurementIterations(4).forks(2).build();

		new Runner(opt).run();

	}
}
