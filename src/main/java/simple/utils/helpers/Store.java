/*
 * Project : SimpleUtils
 * Author : bassem.zohdy
 * Email : bassem.zohdy@gmail.com
 */
package simple.utils.helpers;

import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * The Interface Store.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public interface Store<K, V> {

	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public abstract Map<K, V> getMap();

	/**
	 * Gets the.
	 *
	 * @param id the id
	 * @return the v
	 */
	public default V get(K id) {
		return getMap().get(id);
	}

	/**
	 * Adds the.
	 *
	 * @param id the id
	 * @param v the v
	 */
	public default void add(K id, V v) {
		getMap().put(id, v);
	}

	/**
	 * Contains key.
	 *
	 * @param k the k
	 * @return true, if successful
	 */
	public default boolean containsKey(K k) {
		return getMap().containsKey(k);
	}

	/**
	 * Contains value.
	 *
	 * @param v the v
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
	public default Stream<K> keys() {
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
	public default Stream<Map.Entry<K, V>> entries() {
		return getMap().entrySet().stream();
	}
	
	/**
	 * Count.
	 *
	 * @return the int
	 */
	public default int count(){
		return getMap().size();
	}
	
	/**
	 * Clear.
	 */
	public default void clear() {
		getMap().clear();
	}

	/**
	 * Reset by supplier.
	 *
	 * @param supplier the supplier
	 */
	public default void resetBySupplier(Supplier<V> supplier){
		getMap().keySet().forEach(k -> getMap().put(k, supplier.get()));
	}
}
