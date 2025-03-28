package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage state1, state2, state3, down1, down2, left1, left2, right1, right2;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public Rectangle hitbox; //determines the collision/death area of an entity
	public boolean death = false;
	public boolean collision = false;
	public boolean rightBounceCollision = false;
	public boolean leftBounceCollision = false;
	public boolean downBounceCollision = false;
	public boolean bounce = false;
	public boolean rightBounce = false;
	public boolean leftBounce = false;
	public boolean topBounce = false;
	public boolean topCollision = false;

}