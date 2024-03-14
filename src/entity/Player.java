package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyboardInput;
import main.V;

public class Player extends Entity {

	GamePanel gp;
	KeyboardInput ki;
	
	public final int screenX;
	public final int screenY;
	
	public Player (GamePanel gp, KeyboardInput ki) {
		this.gp = gp;
		this.ki = ki;
		
		screenX = V.screenWidth/2 - (V.tileSize/2);
		screenY = V.screenHeight/2 - (V.tileSize/2);
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = V.playerX;
		worldY = V.playerY;
		speed = V.playerSpeed;
		direction = V.direction;
	}
	
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		if (ki.upPress == true || ki.downPress == true || ki.leftPress == true || ki.rightPress == true) {
			if (ki.upPress == true) {
				direction = "up";
				worldY -= speed;
			} else if (ki.downPress == true) {
				direction = "down";
				worldY += speed;
			} else if (ki.leftPress == true) {
				direction = "left";
				worldX -= speed;
			} else if (ki.rightPress == true) {
				direction = "right";
				worldX += speed;
			}
			
			spriteCounter++;
			if(spriteCounter > 12) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
		
		else {
			spriteNum = 1;
		}
		
	}
	
	public void draw(Graphics g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, V.tileSize, V.tileSize, null);
	}
}
