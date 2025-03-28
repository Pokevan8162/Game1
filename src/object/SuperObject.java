package object;

import java.awt.image.BufferedImage;

import main.Toolbox;

public class SuperObject {

	public BufferedImage image;
	String name;
	public boolean collision = false;
	public int worldX, worldY;
	Toolbox toolBox = new Toolbox(); // used to scale images for performance
}
