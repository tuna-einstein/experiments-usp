package com.usp.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class OneDArrayEquivalent {

	private byte[] bytes;
	private short min;
	private short max;

	public OneDArrayEquivalent(Double[] input) {
		setInfo(input);
	}

	public OneDArrayEquivalent(byte bytes[], short max, short min) {
		this.bytes = bytes;
		this.min = min;
		this.max = max;
	}

	public double getMin() {
		return min;
	}

	public short getMax() {
		return max;
	}

	public byte[] getBytes() {
		return bytes;
	}

	private void setInfo(Double[] input) {
		setMinMax(input);
		bytes = new byte[input.length];
		int k = 0;
		double factor = 255.0/(max - min);
		for (double d : input) {
			bytes[k] = (byte) ((d - min)*factor);
			k++;
		}
	}

	public Double[] getDoubleArray() {
		Double result[] = new Double[bytes.length];
		int k = 0;
		double factor = (max - min) / 255.0;
		for (byte b : bytes) {
			result[k] = (b & 0xFF)*factor + min;
			k++;
		}
		return result;
	}

	public void write(DataOutputStream out) throws IOException {
		out.writeShort(min);
		out.writeShort(max);
		out.writeShort(bytes.length);
		out.write(bytes);
	}
	
	public static OneDArrayEquivalent read(DataInputStream in) throws IOException {
		short min = in.readShort();
		short max = in.readShort();
		short length = in.readShort();
		byte[] bytes = new byte[length];
		in.read(bytes);
		OneDArrayEquivalent res = new OneDArrayEquivalent(bytes, max, min);
		return res;
	}

	private void setMinMax(Double[] input) {
		max = (short) Math.ceil(input[0]);
		min = (short) Math.floor(input[0]);
		for (double d : input) {
			if (max < d) {
				max = (short) Math.ceil(d);
			}
			if (min > d) {
				min = (short) Math.floor(d);
			}
		}
	}
}
