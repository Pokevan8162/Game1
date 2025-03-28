package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.Clip;
import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.MapManager;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	KeyboardInput ki = new KeyboardInput();
	Thread gameThread;
	public Player player = new Player(this, ki);
	TileManager tm = new TileManager(this);
	Sound se = new Sound();
	Sound theme = new Sound();
	public DeathChecker deathCheck = new DeathChecker(this);
	public CollisionChecker colCheck = new CollisionChecker(this);
	public BounceChecker bounceCheck = new BounceChecker(this);
	public BounceCollisionChecker bounceColCheck = new BounceCollisionChecker(this);
	public SuperObject obj[] = new SuperObject[10];
	public AssetController assetCon = new AssetController(this);
	public int scrollSpeed = 6;
	int mapChangeTimer = 0; // creates the map loop
	public ScoreCounter scoreCounter = new ScoreCounter(this);
	int fps;
	int frame = 0;
	int avgDrawTime = 0;
	int drawTime;

	public GamePanel() {

		this.setPreferredSize(new Dimension(V.screenWidth, V.screenHeight)); // set original size of the window
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // improves game rendering performance
		this.addKeyListener(ki);
		this.setFocusable(true); // this allows GamePanel to be "focused" to recieve key input (like clicking on a tab so it knows to be typed on)

	}
	
	public void setupGame() {
		playMusic();  // 0 = index 0 of the sound array
	}

	public void startGameThread() {
		
		gameThread = new Thread(this); // this = GamePanel class
		gameThread.start(); // automatically calls the run method
	}

	@Override
	public void run() {
		
		double drawInterval = 1000000000/V.FPS; // 0.0167 seconds if FPS = 60
		double nextDrawTime = System.nanoTime() + drawInterval;
		long currTime;
		long lastTime = System.nanoTime();
		// long currTime = System.currentTimeMillis(); // This is less precise
		long timer = 0;
		int drawCount = 0; //these two variables display FPS
		
		while (gameThread != null) {
			if (player.death == true) {
				playerDeath();
			}
			scrollSpeed += 6;
			
			currTime = System.nanoTime();
			timer += (currTime - lastTime);
			lastTime = currTime;
			
			// 1. Update information (character position, etc)
			// 2. Draw the screen with the updated information
			// Happens as many FPS there are
			update();
			repaint();
			drawCount++;
			
			if(timer >= 1000000000) {
				fps = drawCount;
				drawCount = 0;
				timer = 0;
				mapChangeTimer++;
			}
			if (mapChangeTimer == 5) {
				try {
					tm.changeMap();
				} catch (IOException e) {
					e.printStackTrace();
				}
				mapChangeTimer = 0;
			}
			
			// This system calculates how much time is left after a frame is processed. If there is time left after processing,
			// then wait until the next frame SHOULD happen. If the frame processing takes more than the interval, then continue processing
			// asap. This is the sleep method. The second method is the delta or accumulator method, found at: 
			// https://www.youtube.com/watch?v=VpH33Uw-_0E&list=RDCMUCS94AD0gxLakurK-6jnqV1w&start_radio=1&rv=VpH33Uw-_0E&t=4 28:25
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime(); // This is used to let the thread sleep while nothing happens
				remainingTime = remainingTime/1000000; // This makes remainingTime a millisecond
					
				if (remainingTime < 0) {
					remainingTime = 0;
				}
				Thread.sleep((long) remainingTime);
				nextDrawTime += drawInterval;
			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void update() {
		player.update();
	}
	
	public void playerDeath() {
		scoreCounter.showScore(getGraphics());
		stopMusic();
		mapChangeTimer = 0;
		playSE(2);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		resetPlayer();
	}
	
	public void resetPlayer() {
		player.score = -65;
		player.bounceCount = 0;
		MapManager.deleteMaps();
		tm.maps = null;
		tm = new TileManager(this);  // resets maps array in tile manager to start new
		player.worldX = V.playerX;
		player.worldY = V.playerY;
		player.screenX = V.screenX;
		player.screenY = V.screenY;
		scrollSpeed = 0;
		tm.mapIndex = 0;
		MapManager.mapIndex = 0;
		player.ki.stopPlayer = false;
		playMusic();
		player.spriteNum = 1;
		player.death = false;
	}

	public void paintComponent(Graphics g) {

		frame++;
		long drawStart = 0;
		if (ki.showFPS == true) {
			drawStart = System.nanoTime();
		}
		
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g; // Changes Graphics g to Graphics2D class. 2D has a bit more functions
		tm.draw(g2);  // Make sure this is written before player so player is above it
		player.draw(g2);
		scoreCounter.draw(g2);
		
		if (ki.showFPS == true) {
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(Color.white);
			g2.setFont(getFont());
			g2.drawString("Draw time: " + passed, 0, 20);
			g2.drawString("FPS: " + fps, 0, 40);
			g2.drawString("Avg draw time: " + avgDrawTime, 0, 60);
			
			if (frame >= 60) {
				avgDrawTime = drawTime/60;
				frame = 0;
				drawTime = 0;
			} else {
				drawTime += passed;
				System.out.println(frame);
			}
		} else if (frame == 60) { // if player does not have showFPS on, these values still must be reset or results will be skewed if they reactivate showFPS (frame will be above 60)
			frame = 0;
			drawTime = 0;
			avgDrawTime = 0;
		}

		g2.dispose();
	}
	
	public void playMusic() {
		theme.setFile(0);
		theme.play();
		theme.loop();
	}
	
	public void stopMusic() {
		theme.stop();
	}
	
	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}
	
	public void stopSE() {
		se.stop();
	}
	
}
