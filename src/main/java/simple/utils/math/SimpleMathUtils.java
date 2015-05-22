/*
 * Project : SimpleUtils
 * Author : bassem.zohdy
 * Email : bassem.zohdy@gmail.com
 */
package simple.utils.math;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleMathUtils.
 */
public class SimpleMathUtils {

	/**
	 * Round.
	 *
	 * @param value
	 *            the value
	 * @param scale
	 *            the scale
	 * @return the double
	 */
	public static double round(double value, int scale) {
		double power = Math.pow(10, scale);
		return Math.round(value * power) / power;
	}
}
