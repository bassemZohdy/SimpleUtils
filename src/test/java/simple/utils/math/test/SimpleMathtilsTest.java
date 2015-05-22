/*
 * Project : SimpleUtils
 * Author : bassem.zohdy
 * Email : bassem.zohdy@gmail.com
 */
package simple.utils.math.test;
import org.apache.commons.math.util.MathUtils;
import org.junit.Test;

import simple.utils.math.SimpleMathUtils;
import static org.junit.Assert.*;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleMathtilsTest.
 */
public class SimpleMathtilsTest  {

	/**
	 * Test ruond.
	 */
	@Test
	public void TestRuond(){
		double d = 5.6893;
		assertEquals(MathUtils.round(d, 1), SimpleMathUtils.round(d, 1),0);
		assertEquals(MathUtils.round(d, 2), SimpleMathUtils.round(d, 2),0);
		assertEquals(MathUtils.round(d, 3), SimpleMathUtils.round(d, 3),0);
		assertEquals(MathUtils.round(d, 4), SimpleMathUtils.round(d, 4),0);
	}
}
