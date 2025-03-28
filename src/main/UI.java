package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

	GamePanel gp;
	Font itemFont;
	Font messageFont;
	Font winFont;
	BufferedImage keyImage;
	public boolean messageOn = false; // for messages to show
	public boolean interactableMessageOn = false; // for messages to show that you need to hit enter to get rid of
	public String message = "";
	int messageTimer = 0;
	public boolean gameWin = false;
	
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI(GamePanel gp) {
		this.gp = gp;
		itemFont = new Font("Arial", Font.PLAIN, 30);
		messageFont = new Font("Arial", Font.PLAIN, 20);
		winFont = new Font("Arial", Font.BOLD, 40);
//		objGoldKey key = new objGoldKey();  // for displaying object images
//		keyImage = key.image;
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
		messageTimer = 0;  // this ensures that, if a previous message had just shown up, then we reset the timer
	}
	
	
	public void drawItemMenu(Graphics2D g2) {
//		if (player has items) {
		g2.setFont(itemFont);
		g2.setColor(Color.WHITE);
		g2.drawImage(keyImage, V.tileSize / 3, V.tileSize / 3, V.tileSize, V.tileSize, null);
//		g2.drawString("x " + gp.player.goldKeyCount, 60, 50);
	}
	
	public void drawMessage(Graphics2D g2) {
		if (gameWin == true) { // probably used for PVE mode
			
			g2.setFont(messageFont);
			g2.setColor(Color.white);
			
			// find center of screen and display message
			String text;
			int textLength;
			int x;
			int y;
			
			text = "You wim! Play time: " + dFormat.format(playTime);
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			y = V.screenHeight / 2 - (V.tileSize*3);  // decrease y a bit to not hide the player
			x = V.screenWidth / 2 - textLength / 2;
			g2.drawString(text, x, y);
			
			g2.setFont(winFont);
			g2.setColor(Color.yellow);
			text = "Comngrat!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			y = V.screenHeight / 2 - (V.tileSize*5);  // decrease y a bit to not hide the player
			x = V.screenWidth / 2 - textLength / 2;
			g2.drawString(text, x, y);
			
			gp.gameThread = null; // this stops the thread
			
			
		} else {
			
			playTime +=(double)1/60; //adds one 60th of a second to the playtime
			
			if (messageTimer > 120) {    // since we are at 60 FPS, this will only display for 2 seconds
				messageTimer = 0;
				messageOn = false;
			}
			if (messageOn == true) {
				g2.setFont(messageFont);
				g2.setColor(Color.WHITE);
				g2.drawString(message,  325, 350);
				messageTimer++;
			}
			g2.setFont(messageFont);
			g2.setColor(Color.white);
			g2.drawString("Time: " + dFormat.format(playTime), 10, V.tileSize * 12 - 10);
		}
	}
	
	public void drawInteractableMessage(Graphics2D g2) {
		if (interactableMessageOn == true) {
			g2.setFont(messageFont);
			g2.setColor(Color.WHITE);
			g2.drawString(message,  325, 350);
			messageTimer++;
		}
	}
	
}
