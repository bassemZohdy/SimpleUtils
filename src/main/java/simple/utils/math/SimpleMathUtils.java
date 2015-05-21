package simple.utils.math;

/**
 * @author bzohdy
 *
 */
public class SimpleMathUtils {

	public static double round(double value, int scale) {
		double power = Math.pow(10, scale);
		return Math.round(value * power) / power;
	}
}
