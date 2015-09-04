/*
 * Project : SimpleUtils
 * Author : bassem.zohdy
 * Email : bassem.zohdy@gmail.com
 */
package simple.utils.collection;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * The Class QueuedConsumer.
 *
 * @param <T>
 *            the generic type
 */
public class QueuedConsumer<T> implements Consumer<T> {
	
	/** The queue. */
	private BlockingQueue<T> queue = new LinkedBlockingQueue<>();
	
	/** The stop. */
	private AtomicBoolean stop = new AtomicBoolean(false);
	
	/** The consumer. */
	private final Consumer<T> consumer;
	
	/** The when to stop. */
	private final Predicate<T> whenToStop;
	
	/** The queue executor. */
	private final Executor queueExecutor;

	/**
	 * Instantiates a new queued consumer.
	 *
	 * @param consumer            the consumer
	 * @param whenToStop the when to stop
	 * @param executor the executor
	 */
	private QueuedConsumer(Consumer<T> consumer, Predicate<T> whenToStop,
			Executor executor) {
		this.consumer = consumer;
		if (whenToStop != null)
			this.whenToStop = whenToStop;
		else
			this.whenToStop = (t) -> false;
		if (executor != null)
			queueExecutor = executor;
		else
			queueExecutor = Executors.newSingleThreadExecutor();
		start();
	}
	
	/**
	 * Start.
	 */
	public void start() {
		queueExecutor.execute(() -> run());
	}

	/**
	 * Instantiates a new queued consumer.
	 *
	 * @param consumer the consumer
	 * @param whenToStop the when to stop
	 */
	private QueuedConsumer(Consumer<T> consumer, Predicate<T> whenToStop) {
		this(consumer, whenToStop, null);
	}

	/**
	 * Instantiates a new queued consumer.
	 *
	 * @param consumer the consumer
	 */
	private QueuedConsumer(Consumer<T> consumer) {
		this(consumer, null, null);
	}

	/* (non-Javadoc)
	 * @see java.util.function.Consumer#accept(java.lang.Object)
	 */
	@Override
	public void accept(T t) {
		try {
			queue.put(t);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Of.
	 *
	 * @param <T> the generic type
	 * @param consumer the consumer
	 * @param whenToStop the when to stop
	 * @param executor the executor
	 * @return the queued consumer
	 */
	public static <T> QueuedConsumer<T> of(Consumer<T> consumer,
			Predicate<T> whenToStop, Executor executor) {
		return new QueuedConsumer<>(consumer, whenToStop, executor);
	}

	/**
	 * Of.
	 *
	 * @param <T> the generic type
	 * @param consumer the consumer
	 * @param whenToStop the when to stop
	 * @return the queued consumer
	 */
	public static <T> QueuedConsumer<T> of(Consumer<T> consumer,
			Predicate<T> whenToStop) {
		return new QueuedConsumer<>(consumer, whenToStop);
	}

	/**
	 * Of.
	 *
	 * @param <T> the generic type
	 * @param consumer the consumer
	 * @return the queued consumer
	 */
	public static <T> QueuedConsumer<T> of(Consumer<T> consumer) {
		return new QueuedConsumer<>(consumer);
	}

	/**
	 * Of.
	 *
	 * @param <T> the generic type
	 * @param consumer the consumer
	 * @param executor the executor
	 * @return the queued consumer
	 */
	public static <T> QueuedConsumer<T> of(Consumer<T> consumer,
			ExecutorService executor) {
		return new QueuedConsumer<>(consumer, null, executor);
	}

	/**
	 * Run.
	 */
	private void run() {
		try {
			while (true) {
				T t = queue.take();
				if (stop.get())
					break;
				this.consumer.accept(t);
				if (whenToStop.test(t))
					break;
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException(e);
		}
	}

	/**
	 * Stop.
	 */
	public void stop() {
		this.stop.set(true);
	}
}
