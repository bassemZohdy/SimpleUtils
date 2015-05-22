/*
 * Project : SimpleUtils
 * Author : bassem.zohdy
 * Email : bassem.zohdy@gmail.com
 */
package simple.utils.function;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;

// TODO: Auto-generated Javadoc
/**
 * The Class FutureFunction.
 *
 * @param <T>
 *            the generic type
 * @param <R>
 *            the generic type
 */
public class FutureFunction<T, R> implements Function<T, Future<R>> {

	/** The function. */
	private final Function<T, R> function;

	/**
	 * Instantiates a new future function.
	 *
	 * @param function
	 *            the function
	 */
	private FutureFunction(Function<T, R> function) {
		this.function = function;
	}

	/**
	 * Of.
	 *
	 * @param <T>
	 *            the generic type
	 * @param <R>
	 *            the generic type
	 * @param function
	 *            the function
	 * @return the future function
	 */
	public static <T, R> FutureFunction<T, R> of(Function<T, R> function) {
		return new FutureFunction<>(function);
	}

	/* (non-Javadoc)
	 * @see java.util.function.Function#apply(java.lang.Object)
	 */
	@Override
	public Future<R> apply(T t) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<R> future = executor.submit(() -> function.apply(t));
		executor.shutdown();
		return future;
	}
}
