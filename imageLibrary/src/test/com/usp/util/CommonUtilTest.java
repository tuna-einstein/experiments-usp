package test.com.usp.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.usp.util.CommonUtil;

public class CommonUtilTest {

	@Test
	public void test() {
		byte[] input = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, (byte) 200};
		double[][] result = CommonUtil.get2DArrayFrom1Darray(input, 3);
		assertEquals(4, result.length);
		assertEquals(3, result[0].length);
		byte[] output = CommonUtil.get1DArrayFrom2Darray(result);
		for (byte b : output) {
			System.out.print(b + " ");
		}
	}
	
	@Test
	public void test1() {
		double d = 123.45678;
		assertEquals(d, CommonUtil.toDouble(CommonUtil.toByteArray(d)), 0.0001);
	}
}
