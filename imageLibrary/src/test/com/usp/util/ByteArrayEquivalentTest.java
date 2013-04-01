package test.com.usp.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.usp.util.TwoDArrayEquivalent;

public class ByteArrayEquivalentTest {
	@Test
	public void test() {
		double[][] input = {
				{12.0, 8.789, -12.0, 2},
				{13.0, -13.789, 2, 3},
				{8.0, 9.789, 7.56, 4},
		};
		TwoDArrayEquivalent byteArray = new TwoDArrayEquivalent(input);
		assertEquals(13.0, byteArray.getMax(), 0.000001);
		assertEquals(-13.789, byteArray.getMin(), 0.000001);
		assertEquals(3, byteArray.getRows());
		assertEquals(4, byteArray.getCols());
		
		byteArray = new TwoDArrayEquivalent(byteArray.getBytes(),
				(short) byteArray.getCols(),
				byteArray.getMax(),
				byteArray.getMin());
		double[][] array = byteArray.getDoubleEquivalent();
		for (int i = 0; i < byteArray.getRows(); i++) {
			for (int j = 0; j < byteArray.getCols(); j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println("\n");
		}
	}
}
