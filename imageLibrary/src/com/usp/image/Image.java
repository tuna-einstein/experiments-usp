package com.usp.image;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {
	private  BufferedImage bufferedImage;
	
	public Image(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}

	public Image(String imageFilePath) {
		try {
			this.bufferedImage = ImageIO.read(new File(imageFilePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			bufferedImage = null;
			e.printStackTrace();
		}
	}

	public byte[] getByteArray() {
		byte imageData[] = ((DataBufferByte) bufferedImage.getData().getDataBuffer()).getData();
		return imageData;
	}

	public int getWidth() {
		return bufferedImage.getWidth();
	}

	public int getHeight() {
		return bufferedImage.getHeight();
	}
	
	public int getType() {
		return bufferedImage.getType();
	}

	public static Image getImage(byte[] pixelData, int width, int height, int type) {
		int[] pixels = byteToInt(pixelData);

	    BufferedImage image = null;
	    if (type == BufferedImage.TYPE_BYTE_GRAY) {
	        image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
	    }
	    else if (type == BufferedImage.TYPE_INT_RGB){
	        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    }

	    WritableRaster raster = (WritableRaster) image.getData();
	    raster.setPixels(0, 0, width, height, pixels);
	    image.setData(raster);
	    return new Image(image);
	}

	public void writeImage(String filePath) {
		File outputfile = new File(filePath);
	    try {
			ImageIO.write(bufferedImage, "bmp", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static int[] byteToInt(byte[] data) {
	    int[] ints = new int[data.length];
	    for (int i = 0; i < data.length; i++) {
	        ints[i] = (int) data[i] & 0xff;
	    }
	    return ints;
	} 
}
