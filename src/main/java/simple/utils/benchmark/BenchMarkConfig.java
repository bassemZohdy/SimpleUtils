/**
 * 
 */
package simple.utils.benchmark;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @author bassem
 *
 */
public class BenchMarkConfig {
	/**
	 * @param args
	 * @throws RunnerException
	 */
	public static void main(String[] args) throws RunnerException {

		Options opt = new OptionsBuilder()
				.include("simple.utils.string.SplitterBenchMark.*")
				.warmupIterations(1).measurementIterations(4).forks(2).build();

		new Runner(opt).run();

	}
}
