/*
 * Project : SimpleUtils
 * Author : bassem.zohdy
 * Email : bassem.zohdy@gmail.com
 */
package simple.utils.helpers;

import java.util.Map;
import java.util.stream.Stream;

/**
 * The Interface Factory.
 *
 * @param <V>
 *            the value type
 */
public interface Factory<V> {

	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public Map<String, V> getMap();

	/**
	 * Creates the.
	 *
	 * @param k
	 *            the k
	 * @return the v
	 */
	public V create(String k);

	/**
	 * Gets the.
	 *
	 * @param id
	 *            the id
	 * @return the v
	 */
	public default V get(String id) {
		if (!getMap().containsKey(id))
			getMap().put(id, create(id));
		return getMap().get(id);
	}

	/**
	 * Contains key.
	 *
	 * @param k
	 *            the k
	 * @return true, if successful
	 */
	public default boolean containsKey(String k) {
		return getMap().containsKey(k);
	}

	/**
	 * Contains value.
	 *
	 * @param v
	 *            the v
	 * @return true, if successful
	 */
	public default boolean containsValue(V v) {
		return getMap().containsValue(v);
	}

	/**
	 * Keys.
	 *
	 * @return the stream
	 */
	public default Stream<String> keys() {
		return getMap().keySet().stream();
	}

	/**
	 * Values.
	 *
	 * @return the stream
	 */
	public default Stream<V> values() {
		return getMap().values().stream();
	}

	/**
	 * Entries.
	 *
	 * @return the stream
	 */
	public default Stream<Map.Entry<String, V>> entries() {
		return getMap().entrySet().stream();
	}

	/**
	 * Count.
	 *
	 * @return the int
	 */
	public default int count() {
		return getMap().size();
	}

}
