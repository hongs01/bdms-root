package com.bdms.spark;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImportPic {

	private static final String OUTPATH = "C:\\";

	private static final Integer IMAGEWIDTH = 1000;

	private static final Integer IMAGEHEIGHT = 1000;

	private BufferedImage bufferedImage;

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}

	public ImportPic() {
		bufferedImage = new BufferedImage(IMAGEWIDTH, IMAGEHEIGHT,
				BufferedImage.TYPE_INT_ARGB);
	}

	public ImportPic(int imgagewidth, int imageheight) {
		bufferedImage = new BufferedImage(imgagewidth, imageheight,
				BufferedImage.TYPE_INT_ARGB);
	}

	public ImportPic(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}

	public BufferedImage ovalPaint(Integer x, Integer y) {
		Graphics graphics = bufferedImage.getGraphics();
		graphics.setColor(Color.red);
		graphics.drawOval(x, y, 10, 10);
		graphics.dispose();
		return bufferedImage;
	}

	public void outPic() throws IOException {
		FileOutputStream out = new FileOutputStream(OUTPATH + "1.png");
		ImageIO.write(bufferedImage, "png", out);
	}

}
