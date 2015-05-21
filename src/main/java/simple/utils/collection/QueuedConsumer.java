package simple.utils.collection;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class QueuedConsumer<T> implements Consumer<T> {
	private BlockingQueue<Event<T>> queue = new LinkedBlockingQueue<>();
	private AtomicBoolean stop = new AtomicBoolean(false);
	private final Consumer<T> consumer;

	private QueuedConsumer(Consumer<T> consumer) {
		this.consumer = consumer;
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(() -> run());
		executor.shutdown();
	}

	@Override
	public void accept(T t) {
		try {
			queue.put(new Event<T>(t));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException(e);
		}
	}

	public static <T> QueuedConsumer<T> of(Consumer<T> consumer) {
		return new QueuedConsumer<>(consumer);
	}

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

	public void stop() {
		this.stop.set(true);
	}

	@SuppressWarnings("unchecked")
	public void finish() {
		try {
			this.queue.put(Event.KILL_EVENT);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException(e);
		}
	}

	private final static class Event<T> {
		@SuppressWarnings("rawtypes")
		public static final Event KILL_EVENT = new Event();
		private T t;

		private Event() {
		}

		private Event(T t) {
			this();
			this.t = t;
		}

		public T get() {
			return t;
		}
	}
}
