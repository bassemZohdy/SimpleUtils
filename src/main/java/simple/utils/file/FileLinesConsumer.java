/*
 * Project : SimpleUtils
 * Author : bassem.zohdy
 * Email : bassem.zohdy@gmail.com
 */
package simple.utils.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.zip.GZIPInputStream;

// TODO: Auto-generated Javadoc
/**
 * The Class FileLinesConsumer.
 *
 * @param <T>
 *            the generic type
 */
public class FileLinesConsumer<T> {
	
	/** The filter. */
	private final Predicate<String> filter;
	
	/** The converter. */
	private final Function<String, T> converter;
	
	/** The consumer. */
	private final Consumer<T> consumer;

	/** The buffer size. */
	private int bufferSize = 124 * 8;

	/**
	 * Instantiates a new file lines consumer.
	 *
	 * @param filter
	 *            the filter
	 * @param converter
	 *            the converter
	 * @param consumer
	 *            the consumer
	 */
	private FileLinesConsumer(Predicate<String> filter,
			Function<String, T> converter, Consumer<T> consumer) {
		if (filter != null)
			this.filter = filter;
		else
			this.filter = (s) -> true;

		if (converter != null)
			this.converter = converter;
		else
			throw new IllegalStateException("Have to provide converter");

		if (consumer != null)
			this.consumer = consumer;
		else
			throw new IllegalStateException("Have to provide consumer");
	}

	/**
	 * Of.
	 *
	 * @param <T>
	 *            the generic type
	 * @param filter
	 *            the filter
	 * @param converter
	 *            the converter
	 * @param consumer
	 *            the consumer
	 * @return the file lines consumer
	 */
	public static <T> FileLinesConsumer<T> of(Predicate<String> filter,
			Function<String, T> converter, Consumer<T> consumer) {
		return new FileLinesConsumer<T>(filter, converter, consumer);
	}

	/**
	 * Load.
	 *
	 * @param file
	 *            the file
	 */
	public void load(File file) {
		try (InputStream inputStream = new FileInputStream(file);
				Reader reader = new InputStreamReader(inputStream);
				BufferedReader br = new BufferedReader(reader, bufferSize)) {
			br.lines().parallel() // parallel
					.filter(filter) // filter
					.map(converter) // converter
					.forEachOrdered(consumer); // consumer
		} catch (Exception e) {
		}
	}

	/**
	 * Load gz.
	 *
	 * @param file
	 *            the file
	 */
	public void loadGZ(File file) {
		try (InputStream inputStream = new GZIPInputStream(new FileInputStream(
				file));
				Reader reader = new InputStreamReader(inputStream);
				BufferedReader br = new BufferedReader(reader, bufferSize)) {
			br.lines().parallel() // parallel
					.filter(filter) // filter
					.map(converter) // converter
					.forEachOrdered(consumer); // consumer
		} catch (Exception e) {
		}
	}
}
