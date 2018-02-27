package com.bdms.spark.paintimage;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface PaintImage{

	BufferedImage initBufferedImage();

	BufferedImage drawOvalT(float fx, float fy, BufferedImage bufferedImage);

	BufferedImage drawOvalTT(int iix, int iiy, BufferedImage bufferedImage);
	
	void paintImage() throws IOException;
}
