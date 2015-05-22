/*
 * Project : SimpleUtils
 * Author : bassem.zohdy
 * Email : bassem.zohdy@gmail.com
 */
package simple.utils.function;

import java.util.Optional;
import java.util.function.Consumer;

// TODO: Auto-generated Javadoc
/**
 * The Class OptionalConsumer.
 *
 * @param <T>
 *            the generic type
 */
public class OptionalConsumer<T> {
	
	/** The optional. */
	private Optional<T> optional;

	/**
	 * Instantiates a new optional consumer.
	 *
	 * @param optional
	 *            the optional
	 */
	private OptionalConsumer(Optional<T> optional) {
		this.optional = optional;
	}

	/**
	 * Of.
	 *
	 * @param <T>
	 *            the generic type
	 * @param optional
	 *            the optional
	 * @return the optional consumer
	 */
	public static <T> OptionalConsumer<T> of(Optional<T> optional) {
		return new OptionalConsumer<>(optional);
	}

	/**
	 * If present.
	 *
	 * @param c
	 *            the c
	 * @return the optional consumer
	 */
	public OptionalConsumer<T> ifPresent(Consumer<T> c) {
		optional.ifPresent(c);
		return this;
	}

	// did not like to use Runnable but I could not find any other alternative
	/**
	 * If not present.
	 *
	 * @param r
	 *            the r
	 * @return the optional consumer
	 */
	public OptionalConsumer<T> ifNotPresent(Runnable r) {
		if (!optional.isPresent())
			r.run();
		return this;
	}
}
