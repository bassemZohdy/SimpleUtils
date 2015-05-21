package simple.utils.function;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;

public class FutureFunction<T, R> implements Function<T, Future<R>> {

	private final Function<T, R> function;

	private FutureFunction(Function<T, R> function) {
		this.function = function;
	}

	public static <T, R> FutureFunction<T, R> of(Function<T, R> function) {
		return new FutureFunction<>(function);
	}

	@Override
	public Future<R> apply(T t) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<R> future = executor.submit(() -> function.apply(t));
		executor.shutdown();
		return future;
	}
}
