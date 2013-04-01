package test.com.usp.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

import com.usp.image.Image;

public class ImageTest {

	private Image image;
 
    @Before
    public void setUp() throws IOException {
    	URL url = Test.class.getClassLoader().getResource("data/buffet.bmp");
        System.out.println(url.getPath());
    	BufferedImage bufferedImage = ImageIO.read(new File(url.getPath()));
    	image = new Image(bufferedImage);
    }
 
    @After
    public void tearDown() {
       
    }

	@Test
	public void testTypeWidthHeight() {
		assertEquals(BufferedImage.TYPE_BYTE_GRAY, image.getType());
		assertEquals(image.getWidth() * image.getHeight(), image.getByteArray().length);
	}

	@Test
	public void testBufferedImage() {
		Image newImage = Image.getImage(image.getByteArray(),
				image.getWidth(), image.getHeight(), image.getType());
		assertTrue(Arrays.equals(newImage.getByteArray(), image.getByteArray()));
	}
}
