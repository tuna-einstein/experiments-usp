package com.usp.util;

import java.nio.ByteBuffer;

public class CommonUtil {

	public static double[][] get2DArrayFrom1Darray(byte[] input, int columnSize) {
		int length = input.length;
		int rows = (int) (Math.ceil(length * 1.0 /columnSize));
		double[][] result = new double[rows][columnSize];
		int k = 0;
		for (int i = 0; i < rows ; i++) {
			for (int j = 0; j < columnSize; j++) {
				if (k < length) {
					result[i][j] = (((input[k] & 0xFF))/255.0);
				} else {
					result[i][j] = 0;
				}

				k++;
			}
		}
		return result;
	}

	public static byte[] get1DArrayFrom2Darray(double[][] input) {
		int rows = input.length;
		int cols = input[0].length;
		byte[] result = new byte[rows*cols];
		int k = 0;
		for (int i = 0; i < rows ; i++) {
			for (int j = 0; j < cols; j++) {
				
				double d = Math.round(input[i][j] * 255);
				
				if (d < 0) {
					result[k] = 0;
				} else if (d > 255) {
					result[k] = (byte) 255;
				} else {
					result[k] = (byte) d;
				}
				k++;
			}
		}
		return result;
	}
	
	public static byte[] toByteArray(double value) {
	    byte[] bytes = new byte[8];
	    ByteBuffer.wrap(bytes).putDouble(value);
	    return bytes;
	}

	public static double toDouble(byte[] bytes) {
	    return ByteBuffer.wrap(bytes).getDouble();
	}
}