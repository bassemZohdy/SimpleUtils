/*
 * Project : SimpleUtils
 * Author : bassem.zohdy
 * Email : bassem.zohdy@gmail.com
 */
package simple.utils.function;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

/**
 * The Class RetrySupplyAsync.
 *
 * @param <T> the generic type
 */
public class RetrySupplyAsync<T> implements Supplier<CompletableFuture<T>> {
	
	/** The supplier. */
	private final Supplier<T> supplier;
	
	/** The tries. */
	private final int tries;
	
	/** The default value. */
	private final T defaultValue;
	
	/** The sleep. */
	private final long sleep;
	
	/** The throwable class. */
	private final Class<? extends Throwable> throwableClass;
	
	/** The executor. */
	private final Executor executor;
	// Constructors
	
	/**
	 * Instantiates a new retry supply async.
	 *
	 * @param supplier the supplier
	 * @param tries the tries
	 * @param defaultValue the default value
	 * @param sleep the sleep
	 * @param throwableClass the throwable class
	 * @param executor the executor
	 */
	private RetrySupplyAsync(Supplier<T> supplier, int tries, T defaultValue,
			long sleep, Class<? extends Throwable> throwableClass,
			Executor executor) {
		this.supplier = supplier;
		this.tries = tries;
		this.defaultValue = defaultValue;
		this.sleep = sleep;
		this.throwableClass = throwableClass;
		this.executor = executor;
	}

	/**
	 * Instantiates a new retry supply async.
	 *
	 * @param supplier the supplier
	 * @param tries the tries
	 * @param defaultValue the default value
	 * @param sleep the sleep
	 * @param throwableClass the throwable class
	 */
	private RetrySupplyAsync(Supplier<T> supplier, int tries, T defaultValue,
			long sleep, Class<? extends Throwable> throwableClass) {
		this(supplier, tries, defaultValue, sleep, throwableClass, null);
	}

	/**
	 * Instantiates a new retry supply async.
	 *
	 * @param supplier the supplier
	 * @param tries the tries
	 * @param defaultValue the default value
	 * @param sleep the sleep
	 * @param executor the executor
	 */
	private RetrySupplyAsync(Supplier<T> supplier, int tries, T defaultValue,
			long sleep, Executor executor) {
		this(supplier, tries, defaultValue, sleep, Throwable.class, executor);
	}

	/**
	 * Instantiates a new retry supply async.
	 *
	 * @param supplier the supplier
	 * @param tries the tries
	 * @param defaultValue the default value
	 * @param sleep the sleep
	 */
	private RetrySupplyAsync(Supplier<T> supplier, int tries, T defaultValue,
			long sleep) {
		this(supplier, tries, defaultValue, sleep, Throwable.class, null);
	}

	/**
	 * Instantiates a new retry supply async.
	 *
	 * @param supplier the supplier
	 * @param tries the tries
	 * @param defaultValue the default value
	 */
	private RetrySupplyAsync(Supplier<T> supplier, int tries, T defaultValue) {
		this(supplier, tries, defaultValue, 1000, Throwable.class, null);
	}

	// static methods
	/**
	 * Of.
	 *
	 * @param <T> the generic type
	 * @param supplier the supplier
	 * @param tries the tries
	 * @param defaultValue the default value
	 * @return the retry supply async
	 */
	public static <T> RetrySupplyAsync<T> of(Supplier<T> supplier, int tries,
			T defaultValue) {
		return new RetrySupplyAsync<T>(supplier, tries, defaultValue);
	}

	/**
	 * Of.
	 *
	 * @param <T> the generic type
	 * @param supplier the supplier
	 * @param tries the tries
	 * @param defaultValue the default value
	 * @param sleep the sleep
	 * @return the retry supply async
	 */
	public static <T> RetrySupplyAsync<T> of(Supplier<T> supplier, int tries,
			T defaultValue, long sleep) {
		return new RetrySupplyAsync<T>(supplier, tries, defaultValue, sleep);
	}

	/**
	 * Of.
	 *
	 * @param <T> the generic type
	 * @param supplier the supplier
	 * @param tries the tries
	 * @param defaultValue the default value
	 * @param sleep the sleep
	 * @param executor the executor
	 * @return the retry supply async
	 */
	public static <T> RetrySupplyAsync<T> of(Supplier<T> supplier, int tries,
			T defaultValue, long sleep, Executor executor) {
		return new RetrySupplyAsync<T>(supplier, tries, defaultValue, sleep,
				executor);
	}

	/**
	 * Of.
	 *
	 * @param <T> the generic type
	 * @param supplier the supplier
	 * @param tries the tries
	 * @param defaultValue the default value
	 * @param sleep the sleep
	 * @param throwableClass the throwable class
	 * @return the retry supply async
	 */
	public static <T> RetrySupplyAsync<T> of(Supplier<T> supplier, int tries,
			T defaultValue, long sleep,
			Class<? extends Throwable> throwableClass) {
		return new RetrySupplyAsync<T>(supplier, tries, defaultValue, sleep,
				throwableClass);
	}

	/**
	 * Of.
	 *
	 * @param <T> the generic type
	 * @param supplier the supplier
	 * @param tries the tries
	 * @param defaultValue the default value
	 * @param sleep the sleep
	 * @param throwableClass the throwable class
	 * @param executor the executor
	 * @return the retry supply async
	 */
	public static <T> RetrySupplyAsync<T> of(Supplier<T> supplier, int tries,
			T defaultValue, long sleep,
			Class<? extends Throwable> throwableClass, Executor executor) {
		return new RetrySupplyAsync<T>(supplier, tries, defaultValue, sleep,
				throwableClass, executor);
	}

	/* (non-Javadoc)
	 * @see java.util.function.Supplier#get()
	 */
	@Override
	public CompletableFuture<T> get() {
		if (executor == null)
			return async();
		else
			return async(executor);
	}

	/**
	 * Async.
	 *
	 * @return the completable future
	 */
	private CompletableFuture<T> async() {
		return CompletableFuture.supplyAsync(supplier).handleAsync(
				(m, t) -> {
					if (t == null)
						return m;
					else {
						try {
							if (tries <= 1 || !throwableClass.isInstance(t))
								return defaultValue;
							Thread.sleep(sleep);
							return RetrySupplyAsync
									.of(supplier, tries - 1, defaultValue,
											sleep, throwableClass).get().get();
						} catch (Throwable th) {
							return defaultValue;
						}
					}
				});
	}

	/**
	 * Async.
	 *
	 * @param executor the executor
	 * @return the completable future
	 */
	private CompletableFuture<T> async(Executor executor) {
		return CompletableFuture.supplyAsync(supplier, executor).handleAsync(
				(m, t) -> {
					if (t == null)
						return m;
					else {
						try {
							if (tries <= 1 || !throwableClass.isInstance(t))
								return defaultValue;
							Thread.sleep(sleep);
							return RetrySupplyAsync
									.of(supplier, tries - 1, defaultValue,
											sleep, throwableClass, executor)
									.get().get();
						} catch (Throwable th) {
							return defaultValue;
						}
					}
				}, executor);
	}

}
