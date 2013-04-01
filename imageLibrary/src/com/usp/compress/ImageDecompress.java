package com.usp.compress;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.encog.engine.network.activation.ActivationLinear;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.mathutil.matrices.Matrix;
import org.encog.neural.data.NeuralData;
import org.encog.neural.data.basic.BasicNeuralData;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.synapse.WeightedSynapse;

import com.usp.image.Image;
import com.usp.util.OneDArrayEquivalent;
import com.usp.util.TwoDArrayEquivalent;

public class ImageDecompress {

	private OneDArrayEquivalent weights;
	private TwoDArrayEquivalent input;
	private short outputUnitsCount;

	public ImageDecompress(OneDArrayEquivalent weights,
			TwoDArrayEquivalent input,
			short outputUnitsCount) {
		this.weights = weights;
		this.input = input;
		this.outputUnitsCount = outputUnitsCount;
	}
	
	public void write(String fileName, int imageWidth, int imageHeight) throws IOException {
		DataOutputStream out = new DataOutputStream(new FileOutputStream(new File(fileName)));
		out.writeInt(imageWidth);
		out.writeInt(imageHeight);
		out.writeShort(outputUnitsCount);
		weights.write(out);
		input.write(out);
		out.flush();
		out.close();
	}

	public static Image read(String fileName) throws IOException {
		DataInputStream in = new DataInputStream(new FileInputStream(new File(fileName)));
		int imageWidth = in.readInt();
		int imageHeight = in.readInt();
		short outputUnitsCount = in.readShort();
		OneDArrayEquivalent weights = OneDArrayEquivalent.read(in);
		TwoDArrayEquivalent input = TwoDArrayEquivalent.read(in);
		ImageDecompress id = new ImageDecompress(weights, input, outputUnitsCount);
		Image img = id.getImage(imageWidth, imageHeight);
		return img;
	}

	public Image getImage(int imageWidth, int imageHeight) {
		double[][] finalResult = new double[input.getRows()][input.getCols()];
		
		Double[] networkWeights = weights.getDoubleArray();
	
		
		Matrix m = new Matrix(input.getCols(), outputUnitsCount);
		m.fromPackedArray(networkWeights, 0);
		
		WeightedSynapse newSynapse = new WeightedSynapse(new BasicLayer(new ActivationSigmoid(), true, input.getCols()),
			new BasicLayer(new ActivationLinear(), false, outputUnitsCount));
		newSynapse.setMatrix(m);
		double[][] intermediateResult = input.getDoubleEquivalent();
		
		int k = 0;
		for (double d[] : intermediateResult) {
			NeuralData intermediate = newSynapse.compute(new BasicNeuralData(d));
			
			NeuralData output = newSynapse.getToLayer().compute(intermediate);
			
			finalResult[k] = output.getData();
			k++;
			//finalResult[k] = network.compute(pair.getInput()).getData();
		}
		
		//byte[] pixelData = CommonUtil.get1DArrayFrom2Darray(finalResult);
		byte[] pixelData = new TwoDArrayEquivalent(finalResult).getBytes();
		Image output = Image.getImage(pixelData, imageWidth, imageHeight, BufferedImage.TYPE_BYTE_GRAY);
		return output;
	}
}
