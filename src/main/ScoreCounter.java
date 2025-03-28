package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import entity.Player;

public class ScoreCounter {
	
	  public Font font;
	  public Font bigFont;
	  GamePanel gp;
	  public int width = 0;
	  
	  public ScoreCounter(GamePanel gp) {
		  this.gp = gp;
		  font = new Font("Dialog", Font.PLAIN, 30);
		  bigFont = new Font("Dialog", Font.PLAIN, 70);
	  }

	  public void draw(Graphics2D g) {
		  if (Player.finishScore != true) { // If player is not dead and finished with the score
			  if (Player.score > 10000) {
				  String score = "Score: " + Long.toString(Player.score);
				  g.setFont(font);
				  g.setColor(Color.MAGENTA);
				  width = g.getFontMetrics().stringWidth(score);
				  g.drawString(score, 764 - width, 30);
			  } else if (Player.score > 5000) {
				  String score = "Score: " + Long.toString(Player.score);
				  g.setFont(font);
				  g.setColor(Color.GREEN);
				  width = g.getFontMetrics().stringWidth(score);
				  g.drawString(score, 764 - width, 30);
			  } else if (Player.score > 3000) {
				  String score = "Score: " + Long.toString(Player.score);
				  g.setFont(font);
				  g.setColor(Color.CYAN);
				  width = g.getFontMetrics().stringWidth(score);
				  g.drawString(score, 764 - width, 30);
			  } else if (Player.score > 1000) {
				  String score = "Score: " + Long.toString(Player.score);
				  g.setFont(font);
				  g.setColor(Color.RED);
				  width = g.getFontMetrics().stringWidth(score);
				  g.drawString(score, 764 - width, 30);
			  } else if (Player.score > 0) {
				  String score = "Score: " + Long.toString(Player.score);
				  g.setFont(font);
				  g.setColor(Color.WHITE);
				  width = g.getFontMetrics().stringWidth(score);
				  g.drawString(score, 764 - width, 30);
			  } else if (Player.score <= 0) {
				  String score = "Score: 0";
				  g.setFont(font);
				  g.setColor(Color.WHITE);
				  width = g.getFontMetrics().stringWidth(score);
				  g.drawString(score, 764 - width, 30);
			  }
		  }
	  }
	  
	  public void showScore(Graphics graphics) {
		  String score = "Score: " + Long.toString(Player.score);
		  graphics.setFont(bigFont);
		  graphics.setColor(Color.RED);
		  width = graphics.getFontMetrics().stringWidth(score);
	  	graphics.drawString(score, 210, 310);
	  }
}