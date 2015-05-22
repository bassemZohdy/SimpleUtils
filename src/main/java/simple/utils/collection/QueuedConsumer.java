/*
 * Project : SimpleUtils
 * Author : bassem.zohdy
 * Email : bassem.zohdy@gmail.com
 */
package simple.utils.collection;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

// TODO: Auto-generated Javadoc
/**
 * The Class QueuedConsumer.
 *
 * @param <T>
 *            the generic type
 */
public class QueuedConsumer<T> implements Consumer<T> {
	
	/** The queue. */
	private BlockingQueue<Event<T>> queue = new LinkedBlockingQueue<>();
	
	/** The stop. */
	private AtomicBoolean stop = new AtomicBoolean(false);
	
	/** The consumer. */
	private final Consumer<T> consumer;

	/**
	 * Instantiates a new queued consumer.
	 *
	 * @param consumer
	 *            the consumer
	 */
	private QueuedConsumer(Consumer<T> consumer) {
		this.consumer = consumer;
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(() -> run());
		executor.shutdown();
	}

	/* (non-Javadoc)
	 * @see java.util.function.Consumer#accept(java.lang.Object)
	 */
	@Override
	public void accept(T t) {
		try {
			queue.put(new Event<T>(t));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException(e);
		}
	}

	/**
	 * Of.
	 *
	 * @param <T>
	 *            the generic type
	 * @param consumer
	 *            the consumer
	 * @return the queued consumer
	 */
	public static <T> QueuedConsumer<T> of(Consumer<T> consumer) {
		return new QueuedConsumer<>(consumer);
	}

	/**
	 * Run.
	 */
	private void run() {
		try {
			while (true) {
				Event<T> e = queue.take();
				if (stop.get())
					break;
				if (e.equals(Event.KILL_EVENT))
					break;
				this.consumer.accept(e.get());
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

	/**
	 * Finish.
	 */
	@SuppressWarnings("unchecked")
	public void finish() {
		try {
			this.queue.put(Event.KILL_EVENT);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException(e);
		}
	}

	/**
	 * The Class Event.
	 *
	 * @param <T>
	 *            the generic type
	 */
	private final static class Event<T> {
		
		/** The Constant KILL_EVENT. */
		@SuppressWarnings("rawtypes")
		public static final Event KILL_EVENT = new Event();
		
		/** The t. */
		private T t;

		/**
		 * Instantiates a new event.
		 */
		private Event() {
		}

		/**
		 * Instantiates a new event.
		 *
		 * @param t
		 *            the t
		 */
		private Event(T t) {
			this();
			this.t = t;
		}

		/**
		 * Gets the.
		 *
		 * @return the t
		 */
		public T get() {
			return t;
		}
	}
}
