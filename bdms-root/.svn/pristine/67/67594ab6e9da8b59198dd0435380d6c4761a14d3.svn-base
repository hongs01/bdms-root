package com.bdms.hbase.PaintImage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class ImageApplication {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		paintImage();
	}
	
	protected static void paintImage() throws IOException {
		BufferedImage image = null;
		File file = new File("c:\\output\\01.png");
		if (file.exists()) {
			image = ImageIO.read(file);
		} else {
			image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
		}

		Graphics g = image.getGraphics();
		g.setColor(Color.red);
		//int[] x = { 200, 205, 210 };
		//int[] y = { 200, 205, 200 };
		//g.drawPolygon(x, y, 3);
		Random rand = new Random();
		for (int i = 0; i < 10000; i++) { 
			int x=rand.nextInt(600)+100;
			int y=rand.nextInt(700)+100;
			g.drawOval(x, y, 5, 5);
		}
		
		FileOutputStream out = new FileOutputStream("c:\\output\\01.png");
		ImageIO.write(image, "png", out);
		g.dispose();
		out.close();
	}

}
