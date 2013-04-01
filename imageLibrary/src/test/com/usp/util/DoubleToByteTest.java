package test.com.usp.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.usp.util.OneDArrayEquivalent;

public class DoubleToByteTest {

	@Test
	public void test() {
		Double[] input = {5.00, 2.345678, -5.00, 5.00, 0.00, 0.111};
		OneDArrayEquivalent doubleToByte = new OneDArrayEquivalent(input);
		for (byte b : doubleToByte.getBytes()) {
			System.out.print((b & 0xFF)+ " ");
		}
		System.out.println("");
		Double[] output = doubleToByte.getDoubleArray();
		for (double d : output) {
			System.out.print(d + " ");
		}
		
	}

}
