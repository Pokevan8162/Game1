package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Toolbox {

	public BufferedImage scaleImage(BufferedImage original) {  //handles scaling of images so GamePanel does not have to handle it every draw call
		BufferedImage scaledImage = new BufferedImage(V.tileSize, V.tileSize, original.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(original, 0, 0, V.tileSize, V.tileSize, null);
		g2.dispose();
		
		return scaledImage;
	}
}
