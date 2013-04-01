package com.usp.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TwoDArrayEquivalent {

	private byte[] bytes;
	private int rows; // same as total number of blocks
	private short cols; // same as number of hidden units
	private float max;
	private float min;

	public TwoDArrayEquivalent(double[][] array) {
		setInfo(array);
	}

	public TwoDArrayEquivalent(byte[] bytes, short cols, float max, float min) {
		this.bytes = bytes;
		this.cols = cols;
		this.rows = (int) Math.ceil(bytes.length * 1.0 / cols);
		this.max = max;
		this.min = min;
	}

	public TwoDArrayEquivalent(byte[] bytes, short cols) {
		this.bytes = bytes;
		this.cols = cols;
		this.rows = (int) Math.ceil(bytes.length * 1.0 / cols);
		this.max = 1;
		this.min = 0;
	}

	public void write(DataOutputStream out) throws IOException {
		out.writeShort(cols);
		out.writeInt(rows);
		out.writeShort((short) Math.ceil(max));
		out.writeShort((short) Math.floor(min));
		out.write(bytes);
	}

	public static TwoDArrayEquivalent read(DataInputStream in) throws IOException {
		short cols = in.readShort();
		int rows = in.readInt();
		short max = in.readShort();
		short min = in.readShort();
		byte[] bytes = new byte[rows * cols];
		in.read(bytes);
		TwoDArrayEquivalent res = new TwoDArrayEquivalent(bytes, cols, max, min);
		return res;
	}

	public double[][] getDoubleEquivalent() {
		double[][] result = new double[rows][cols];
		int k = 0 ;
		double factor = (max - min)/255.0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if ( k < bytes.length) {
					result[i][j] = (bytes[k] & 0xFF) * factor + min;
				} else {
					result[i][j] = 0;
				}

				k++;
			}
		}
		return result;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public float getMax() {
		return max;
	}

	public float getMin() {
		return min;
	}

	public byte[] getBytes() {
		return bytes;
	}

	private void setMinAndMax(double[][] array) {
		this.max = (float) array[0][0];
		this.min = max;

		for (double row[] : array) {
			for (double d : row) {
				if (max < d) {
					max = (float) d;
				}
				if (min > d) {
					min = (float) d;
				}
			}
		}
	}

private void setInfo(double[][] array) {
	setMinAndMax(array);
	rows = array.length;
	cols = (short) array[0].length;
	bytes = new byte[rows * cols];
	int k = 0;
	double factor = 255.0/(max - min);
	for (int i = 0; i < rows ; i ++) {
		for (int j = 0; j < cols; j++) {
			bytes[k] = (byte) ((array[i][j] - min) * factor);
			k++;
		}
	}

}
}
