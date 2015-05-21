package simple.utils.function;

import java.util.Optional;
import java.util.function.Consumer;

public class OptionalConsumer<T> {
	private Optional<T> optional;

	private OptionalConsumer(Optional<T> optional) {
		this.optional = optional;
	}

	public static <T> OptionalConsumer<T> of(Optional<T> optional) {
		return new OptionalConsumer<>(optional);
	}

	public OptionalConsumer<T> ifPresent(Consumer<T> c) {
		optional.ifPresent(c);
		return this;
	}

	// did not like to use Runnable but I could not find any other alternative
	public OptionalConsumer<T> ifNotPresent(Runnable r) {
		if (!optional.isPresent())
			r.run();
		return this;
	}
}
