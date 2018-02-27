package com.bdms.spark.paintimage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

import javax.imageio.ImageIO;

public class PaintImageImpl implements PaintImage,Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public BufferedImage initBufferedImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BufferedImage drawOvalT(float fx, float fy,
			BufferedImage bufferedImage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BufferedImage drawOvalTT(int iix, int iiy,
			BufferedImage bufferedImage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void paintImage() throws IOException {
		
		BufferedImage image = null;
		File file = new File("c:\\02.png");
		if (file.exists()) {
			image = ImageIO.read(file);
		} else {
			image = new BufferedImage(14000, 4000, BufferedImage.TYPE_INT_ARGB);
		}

		Graphics g = image.getGraphics();
		g.setColor(Color.red);
		//int[] x = { 200, 205, 210 };
		//int[] y = { 200, 205, 200 };
		//g.drawPolygon(x, y, 3);
		Random rand = new Random();
		for (int i = 0; i < 20000000; i++) { 
			
			g.drawOval(rand.nextInt(14000),rand.nextInt(4000), 5, 5);
		}
		
		FileOutputStream out = new FileOutputStream("c:\\02.png");
		ImageIO.write(image, "png", out);
		g.dispose();
		out.close();

	}

}
