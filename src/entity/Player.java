package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyboardInput;
import main.Toolbox;
import main.V;

public class Player extends Entity {

	GamePanel gp;
	public KeyboardInput ki;
	
	public int screenX;
	public int screenY;
	public String bounceDirection = "null";
	public static int bounceCount = 0;
	public int bounceDistance = 12;
	public int bounceDownDistance = 24;
	int topBounceCounter = 0;
	public boolean bounceDown = false;
	public static long score = -70;
	public static boolean finishScore = false;
	
	
	public Player (GamePanel gp, KeyboardInput ki) {
		this.gp = gp;
		this.ki = ki;
		
		hitbox = new Rectangle(16, 16, 16, 16); //sets the collision area of the player
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = V.playerX;
		worldY = V.playerY;
		screenX = V.screenX;
		screenY = V.screenY;
		speed = V.playerSpeed;
		direction = V.direction;
	}
	
	public void getPlayerImage() {
		state1 = setup("Ship_1");
		state2 = setup("Ship_2");
		state3 = setup("Player_Death_1");
	}
	
	public BufferedImage setup(String imageName) {
		Toolbox toolBox = new Toolbox();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
			image = toolBox.scaleImage(image);
		} catch (Exception e) {
			
		}
		return image;
	}
	
	public void update() {
		
		if (death != true) { // makes sure player doesnt move after death
		finishScore = false;
		
		if (ki.stopPlayer == false) {
			if (ki.upPress == true || ki.downPress == true || ki.leftPress == true || ki.rightPress == true || ki.idle == true) {
				if (ki.upPress == true) {
					direction = "up";
				} else if (ki.downPress == true) {
					direction = "down";
				} else if (ki.leftPress == true) {
					direction = "left";
				} else if (ki.rightPress == true) {
					direction = "right";
				} else if (ki.idle == true) {
					direction = "idle";
				}
			}
			
			//check for collision
			
			death = false;
			bounce = false;
			topBounce = false;
			bounceDown = false;
			rightBounce = false;
			leftBounce = false;
			collision = false;
			rightBounceCollision = false;
			leftBounceCollision = false;
			downBounceCollision = false;
			topCollision = false;
			gp.deathCheck.checkTile(this);
			gp.colCheck.checkTile(this);
			gp.bounceCheck.checkTile(this);
			gp.bounceColCheck.checkTile(this);
			
			if (downBounceCollision == false && topBounceCounter > 0) { //This is the if statement as mentioned below. It essentially forces a bounce downwards unless there is downward collision
				bounceDown = true;
			}
			
			if (screenY > 560) { // if player falls too low
				death = true;
			}

			if (death == true) {
				finishScore = true;
				ki.stopPlayer = true;
				spriteNum = 3;
				direction = "idle";
			}
			
			if (bounce == true || topBounce == true) {
				bounceCount = 6;
				switch(direction) {
				case "up":
					gp.playSE(1);
					bounceDirection = "down";
					break;
				case "left":
					gp.playSE(1);
					bounceDirection = "right";
					break;
				case "right":
					gp.playSE(1);
					bounceDirection = "left";
					break;
				case "idle":
					gp.playSE(1);
					bounceDirection = "idle";
					break;
				}
			}
			
			if (bounceCount != 0) {
				if (bounceDown == true) {
					worldY += bounceDownDistance;
					screenY += bounceDownDistance;
				}
				switch (bounceDirection) {
				case "down":
					if (downBounceCollision == false) {
						worldY += bounceDownDistance;
						screenY += bounceDownDistance;
						bounceCount--;
					} else {
						bounce = false;
						bounceCount = 0;
					}
					break;
				case "right":
					if (rightBounceCollision == false) {
						if (topBounce == true && rightBounce == true) {
							worldY += bounceDownDistance;
							screenY += bounceDownDistance;
							worldX += bounceDistance;
							screenX += bounceDistance;
							bounceCount--;
							topBounceCounter = 3;
						} if (topBounce == true && rightBounce == false) {
							worldY += bounceDownDistance;
							screenY += bounceDownDistance;
							worldX -= bounceDistance;
							screenX -= bounceDistance;
							bounceCount--;
							topBounceCounter = 3;
						} if (topBounce == false && rightBounce == false) {
							if (topBounceCounter > 0) {
								worldY += bounceDownDistance;
								screenY += bounceDownDistance;
								worldX -= bounceDistance;
								screenX -= bounceDistance;
								bounceCount--;
							} else {
								worldX += bounceDistance;
								screenX += bounceDistance;
								bounceCount--;
							}
						} if (topBounce == false && rightBounce == true) {
							worldX += bounceDistance;
							screenX += bounceDistance;
							bounceCount--;
						}
					} else {
						bounce = false;
						bounceCount = 0;
					}
					topBounceCounter--;
					break;
				case "left":
					if (leftBounceCollision == false) {
						if (topBounce == true && leftBounce == true) {
							worldY += bounceDownDistance;
							screenY += bounceDownDistance;
							worldX -= bounceDistance;
							screenX -= bounceDistance;
							bounceCount--;
							topBounceCounter = 3;  //Without this and the if statement defined above that utilizes this variable, the player would not bounce down but only down bounceDownDistance ONCE instead of the normal 6 times.
						} if (topBounce == true && leftBounce == false) {
							worldY += bounceDownDistance;
							screenY += bounceDownDistance;
							worldX += bounceDistance;
							screenX += bounceDistance;
							bounceCount--;
							topBounceCounter = 3;
						} if (topBounce == false && leftBounce == false) {
							if (topBounceCounter > 0) {
								worldY += bounceDownDistance;
								screenY += bounceDownDistance;
								worldX += bounceDistance;
								screenX += bounceDistance;
								bounceCount--;
							} else {
								worldX -= bounceDistance;
								screenX -= bounceDistance;
								bounceCount--;
							}
						} if (topBounce == false && leftBounce == true) {
							worldX -= bounceDistance;
							screenX -= bounceDistance;
							bounceCount--;
						}
					} else {
						bounce = false;
						bounceCount = 0;
					}
					topBounceCounter--;
					break;
				case "idle":
					if (downBounceCollision == false) {
						worldY += bounceDownDistance;
						screenY += bounceDownDistance;
						bounceCount--;
					} else {
						bounce = false;
						bounceCount = 0;
					}
					break;
				}
			}
			
			if (topCollision == true) { // if trying to move left or right while stuck under an object,
				switch(direction) {
				case "left":
					if (collision == false) {
						worldX -= speed;
						screenX -= speed;
					}
					screenY += speed;
					break;
				case "right":
					if (collision == false) {
						worldX += speed;
						screenX += speed;
					}
					screenY += speed;
					break;
				case "idle":
					worldY -= 6;
				}
			} else if (collision == false) {
				switch(direction) {
				case "up":
					worldY -= speed;
					worldY -= 6; // 6 being the scroll speed
					screenY -= speed;
					break;
				case "down":
					screenY += speed;
					break;
				case "left":
					worldX -= speed;
					worldY -= 6;
					screenX -= speed;
					break;
				case "right":
					worldX += speed;
					worldY -= 6;
					screenX += speed;
					break;
				case "idle":
					worldY -= 6;
					break;
				}
				
			} else {
				if (direction == "up" || direction == "idle") {
					worldY += 6;
					screenY += 6;
				}
				worldY -= 6;
			}
			
		}
		
		if (screenY < 0) {
			screenY = 0;
			worldY += 6;
		}
		
		spriteCounter++;
		if(spriteCounter > 12 && spriteNum != 3) {
			if (spriteNum == 1) {
				spriteNum = 2;
			} else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		
		score += 1;
		} else {
			score = 0;
		}
	}
	
	public void draw(Graphics g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if (spriteNum == 1) {
				image = state1;
			}
			if (spriteNum == 2) {
				image = state2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = state1;
			}
			if (spriteNum == 2) {
				image = state2;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = state1;
			}
			if (spriteNum == 2) {
				image = state2;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = state1;
			}
			if (spriteNum == 2) {
				image = state2;
			}
			break;
		case "idle":
			if (spriteNum == 1) {
				image = state1;
			}
			if (spriteNum == 2) {
				image = state2;
			}
			if (spriteNum == 3) {
				image = state3;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, null);
//		g2.setColor(Color.RED);
//		g2.drawRect(screenX + hitbox.x, screenY + hitbox.y, hitbox.width, hitbox.height);    // Draws player collision hitbox
	}
}
